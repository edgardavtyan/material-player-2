package com.edavtyan.materialplayer2.ui.search.base;

import com.ed.libsutils.utils.Strings;

public class SearchPresenterImpl implements SearchPresenter {
	private final SearchModel model;
	private final SearchView view;

	public SearchPresenterImpl(SearchModel model, SearchView view) {
		this.model = model;
		this.view = view;
	}

	public void onSearchChange(String query) {
		if (Strings.nullOrEmpty(query)) {
			view.showEmptyQuery();
			return;
		}

		model.setQuery(query);
		model.update();

		if (model.getSearchResultCount() == 0) {
			view.showEmptyResult();
			return;
		}

		view.notifyDataSetChanged();
	}
}
