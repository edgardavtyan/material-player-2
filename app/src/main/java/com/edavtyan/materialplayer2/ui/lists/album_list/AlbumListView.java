package com.edavtyan.materialplayer2.ui.lists.album_list;

import com.edavtyan.materialplayer2.db.types.Track;
import com.edavtyan.materialplayer2.ui.lists.lib.ListView;
import com.edavtyan.materialplayer2.lib.transition.SourceSharedViews;

import java.util.List;

public interface AlbumListView extends ListView {
	void gotoAlbumDetail(int albumId, SourceSharedViews sharedViews);
	void showPlaylistSelectionDialog(List<Track> tracks);
	void notifyItemChanged(int position);
}
