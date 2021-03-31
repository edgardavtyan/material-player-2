package com.edavtyan.materialplayer2.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;

import com.ed.libsutils.utils.BitmapResizer;
import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.ui.main.MainActivity;
import com.edavtyan.materialplayer2.lib.views.AdvancedRemoteViews;
import com.edavtyan.materialplayer2.lib.testable.TestableNotificationManager;
import com.ed.libsutils.utils.DpConverter;
import com.edavtyan.materialplayer2.utils.PendingIntents;

@TargetApi(24)
public class PlayerNotificationNougat extends PlayerNotification {
	public static final int SCALED_ART_SIZE_DP = 100;

	public PlayerNotificationNougat(
			Context context,
			AdvancedRemoteViews normalRemoteViews,
			AdvancedRemoteViews bigRemoteViews,
			TestableNotificationManager manager,
			Notification.Builder builder,
			PendingIntents pendingIntents) {
		super(normalRemoteViews, bigRemoteViews, manager);
		setNotification(builder
				.setSmallIcon(R.drawable.ic_status)
				.setContentIntent(pendingIntents.getActivity(MainActivity.class))
				.setCustomContentView(normalRemoteViews)
				.setCustomBigContentView(bigRemoteViews)
				.setStyle(new Notification.DecoratedCustomViewStyle())
				.setColor(ContextCompat.getColor(context, R.color.primary))
				.build());
	}

	@Override
	public void setArt(Bitmap art) {
		if (art == null) {
			super.setArt(null);
			return;
		}

		int scaledArtSize = DpConverter.dpToPixel(SCALED_ART_SIZE_DP);
		Bitmap scaledArt = BitmapResizer.resize(art, scaledArtSize);
		super.setArt(scaledArt);
	}
}
