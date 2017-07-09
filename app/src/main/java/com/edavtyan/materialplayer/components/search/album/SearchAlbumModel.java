package com.edavtyan.materialplayer.components.search.album;

import android.content.Context;

import com.edavtyan.materialplayer.components.album_all.AlbumListModel;
import com.edavtyan.materialplayer.components.search.base.SearchModel;
import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.mvp.list.CompactListPref;

import java.util.List;

import lombok.Setter;

public class SearchAlbumModel extends AlbumListModel implements SearchModel {
	private final AlbumDB albumDB;

	private @Setter String query;

	public SearchAlbumModel(
			Context context,
			AlbumDB albumDB,
			TrackDB trackDB,
			CompactListPref compactListPref) {
		super(context, albumDB, trackDB, compactListPref);
		this.albumDB = albumDB;
	}

	@Override
	protected List<Album> queryAlbums() {
		return albumDB.searchAlbums(query);
	}

	@Override
	public int getSearchResultCount() {
		return getAlbumsCount();
	}
}
