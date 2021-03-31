package com.edavtyan.materialplayer2.ui.search.tracks;

import com.edavtyan.materialplayer2.ui.lists.track_list.TrackListPresenter;
import com.edavtyan.materialplayer2.ui.search.base.SearchPresenter;
import com.edavtyan.materialplayer2.ui.search.base.SearchPresenterImpl;

public class SearchTrackPresenter extends TrackListPresenter implements SearchPresenter {
	private final SearchPresenterImpl searchPresenterImpl;

	public SearchTrackPresenter(SearchTrackModel model, SearchTrackFragment view) {
		super(view, model);
		this.searchPresenterImpl = new SearchPresenterImpl(model, view);
	}

	public void onSearchChange(String query) {
		searchPresenterImpl.onSearchChange(query);
	}
}
