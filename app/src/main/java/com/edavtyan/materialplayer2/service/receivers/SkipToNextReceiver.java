package com.edavtyan.materialplayer2.service.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.edavtyan.materialplayer2.player.Player;

public class SkipToNextReceiver extends BroadcastReceiver {
	private final Player player;

	public SkipToNextReceiver(Player player) {
		this.player = player;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		player.skipToNext();
	}
}
