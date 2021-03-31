package com.edavtyan.materialplayer2.ui.search.artist;

import com.edavtyan.materialplayer2.ui.lists.artist_list.ArtistListPresenter;
import com.edavtyan.materialplayer2.ui.search.base.SearchPresenter;
import com.edavtyan.materialplayer2.ui.search.base.SearchPresenterImpl;

public class SearchArtistPresenter extends ArtistListPresenter implements SearchPresenter {
	private final SearchPresenterImpl searchPresenterImpl;

	public SearchArtistPresenter(SearchArtistModel model, SearchArtistFragment view) {
		super(model, view);
		this.searchPresenterImpl = new SearchPresenterImpl(model, view);
	}

	public void onSearchChange(String query) {
		searchPresenterImpl.onSearchChange(query);
	}
}
