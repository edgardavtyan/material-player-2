package com.edavtyan.materialplayer2.notification;

import android.app.Notification;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.service.PlayerServiceScope;
import com.edavtyan.materialplayer2.lib.views.AdvancedRemoteViews;
import com.edavtyan.materialplayer2.lib.testable.TestableNotificationManager;
import com.edavtyan.materialplayer2.utils.PendingIntents;

import dagger.Module;
import dagger.Provides;

@Module
public class PlayerNotificationNougatDIModule {
	@Provides
	@PlayerServiceScope
	@Nullable
	public PlayerNotificationNougat provideNotification(
			Context context,
			TestableNotificationManager manager,
			Notification.Builder builder,
			PendingIntents pendingIntents) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			return new PlayerNotificationNougat(
					context,
					new AdvancedRemoteViews(context, R.layout.notification_nougat),
					new AdvancedRemoteViews(context, R.layout.notification_nougat_big),
					manager, builder, pendingIntents);
		} else {
			return null;
		}
	}

	@Provides
	@PlayerServiceScope
	public TestableNotificationManager provideManager(Context context) {
		return new TestableNotificationManager(NotificationManagerCompat.from(context));
	}

	@Provides
	@PlayerServiceScope
	public Notification.Builder provideBuilder(Context context) {
		return new Notification.Builder(context);
	}
}
