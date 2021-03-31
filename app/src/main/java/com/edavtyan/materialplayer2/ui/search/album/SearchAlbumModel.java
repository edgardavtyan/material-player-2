package com.edavtyan.materialplayer2.ui.search.album;

import com.edavtyan.materialplayer2.db.types.Album;
import com.edavtyan.materialplayer2.db.MediaDB;
import com.edavtyan.materialplayer2.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer2.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer2.ui.lists.album_list.AlbumListModel;
import com.edavtyan.materialplayer2.ui.search.base.SearchModel;

import java.util.List;

import lombok.Setter;

public class SearchAlbumModel extends AlbumListModel implements SearchModel {
	private final MediaDB mediaDB;

	private @Setter String query;

	public SearchAlbumModel(
			ModelServiceModule serviceModule,
			MediaDB mediaDB,
			AlbumArtProvider albumArtProvider) {
		super(serviceModule, mediaDB, albumArtProvider);
		this.mediaDB = mediaDB;
	}

	@Override
	protected List<Album> queryAlbums() {
		return mediaDB.searchAlbums(query);
	}

	@Override
	public int getSearchResultCount() {
		return getAlbumsCount();
	}
}
