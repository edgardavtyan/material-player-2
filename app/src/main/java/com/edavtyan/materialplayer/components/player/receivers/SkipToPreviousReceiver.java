package com.edavtyan.materialplayer.components.player.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.edavtyan.materialplayer.components.player.Player;

public class SkipToPreviousReceiver extends BroadcastReceiver {
	private final Player player;

	public SkipToPreviousReceiver(Player player) {
		this.player = player;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		player.skipToPrevious();
	}
}
