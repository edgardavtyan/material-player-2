package com.edavtyan.materialplayer2.ui.lists.artist_list;

import com.edavtyan.materialplayer2.db.types.Track;
import com.edavtyan.materialplayer2.ui.lists.lib.ListView;
import com.edavtyan.materialplayer2.lib.transition.SourceSharedViews;

import java.util.List;

public interface ArtistListView extends ListView {
	void gotoArtistDetail(String title, SourceSharedViews sharedViews);
	void showPlaylistSelectionDialog(List<Track> tracks);
}
