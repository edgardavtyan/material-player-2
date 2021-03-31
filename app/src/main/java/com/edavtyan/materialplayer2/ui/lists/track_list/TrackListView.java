package com.edavtyan.materialplayer2.ui.lists.track_list;

import com.edavtyan.materialplayer2.db.types.Track;
import com.edavtyan.materialplayer2.lib.theme.ThemeableScreen;
import com.edavtyan.materialplayer2.ui.lists.lib.ListView;

public interface TrackListView extends ListView, ThemeableScreen {
	void showPlaylistSelectionDialog(Track track);
}
