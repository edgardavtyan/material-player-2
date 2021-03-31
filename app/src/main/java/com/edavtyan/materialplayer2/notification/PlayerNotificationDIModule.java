package com.edavtyan.materialplayer2.notification;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer2.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer2.service.PlayerServiceScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PlayerNotificationDIModule {
	@Provides
	@PlayerServiceScope
	public PlayerNotification providePlayerNotification(
			PlayerNotificationCompat notificationCompat,
			@Nullable PlayerNotificationNougat notificationNougat) {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
			return notificationCompat;
		} else {
			assert notificationNougat != null;
			return notificationNougat;
		}
	}

	@Provides
	@PlayerServiceScope
	public PlayerNotificationPresenter providePresenter(
			PlayerNotificationModel model, PlayerNotification notification) {
		return new PlayerNotificationPresenter(model, notification);
	}

	@Provides
	@PlayerServiceScope
	public PlayerNotificationModel provideModel(
			Context context, AlbumArtProvider albumArtProvider) {
		return new PlayerNotificationModel(context, albumArtProvider);
	}
}
