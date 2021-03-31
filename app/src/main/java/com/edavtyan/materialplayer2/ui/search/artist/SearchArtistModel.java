package com.edavtyan.materialplayer2.ui.search.artist;

import com.edavtyan.materialplayer2.db.types.Artist;
import com.edavtyan.materialplayer2.db.MediaDB;
import com.edavtyan.materialplayer2.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer2.ui.lists.artist_list.ArtistListImageLoader;
import com.edavtyan.materialplayer2.ui.lists.artist_list.ArtistListModel;
import com.edavtyan.materialplayer2.ui.search.base.SearchModel;

import java.util.List;

public class SearchArtistModel extends ArtistListModel implements SearchModel {
	private final MediaDB mediaDB;

	private String query;

	public SearchArtistModel(
			ModelServiceModule serviceModule,
			MediaDB mediaDB,
			ArtistListImageLoader imageLoader) {
		super(serviceModule, mediaDB, imageLoader);
		this.mediaDB = mediaDB;
	}

	@Override
	protected List<Artist> queryArtists() {
		return mediaDB.searchArtists(query);
	}

	@Override
	public void setQuery(String query) {
		this.query = query;
	}

	@Override
	public int getSearchResultCount() {
		return getArtistCount();
	}
}
