package com.edavtyan.materialplayer2.ui.search.base;

public interface SearchModel {
	void setQuery(String query);
	void update();
	int getSearchResultCount();
}
