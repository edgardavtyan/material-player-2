package com.edavtyan.materialplayer2.player;

import com.edavtyan.materialplayer2.db.types.Track;

public interface PlayerPlugin {
	void onPlayerPluginConnected(Player player);
	void onNewTrack(Track track);
}
