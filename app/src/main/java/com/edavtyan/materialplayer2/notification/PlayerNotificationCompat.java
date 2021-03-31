package com.edavtyan.materialplayer2.notification;

import android.graphics.Bitmap;
import android.support.v4.app.NotificationCompat;

import com.ed.libsutils.utils.BitmapResizer;
import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.ui.main.MainActivity;
import com.edavtyan.materialplayer2.lib.views.AdvancedRemoteViews;
import com.edavtyan.materialplayer2.lib.testable.TestableNotificationManager;
import com.ed.libsutils.utils.DpConverter;
import com.edavtyan.materialplayer2.utils.PendingIntents;

public class PlayerNotificationCompat extends PlayerNotification {
	private static final int SCALED_ART_SIZE_DP = 120;

	public PlayerNotificationCompat(
			AdvancedRemoteViews normalRemoteViews,
			AdvancedRemoteViews bigRemoteViews,
			TestableNotificationManager manager,
			NotificationCompat.Builder builder,
			PendingIntents pendingIntents) {
		super(normalRemoteViews, bigRemoteViews, manager);
		setNotification(builder
				.setSmallIcon(R.drawable.ic_status)
				.setContentIntent(pendingIntents.getActivity(MainActivity.class))
				.setContent(normalRemoteViews)
				.setCustomBigContentView(bigRemoteViews)
				.setPriority(NotificationCompat.PRIORITY_MAX)
				.build());
	}

	@Override
	public void setArt(Bitmap art) {
		if (art == null) {
			super.setArt(null);
		} else {
			int scaledArtSize = DpConverter.dpToPixel(SCALED_ART_SIZE_DP);
			Bitmap scaledArt = BitmapResizer.resize(art, scaledArtSize);
			super.setArt(scaledArt);
		}
	}
}
