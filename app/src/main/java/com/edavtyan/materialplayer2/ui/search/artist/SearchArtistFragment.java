package com.edavtyan.materialplayer2.ui.search.artist;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer2.App;
import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.ui.lists.artist_list.ArtistListDIModule;
import com.edavtyan.materialplayer2.ui.lists.artist_list.ArtistListFragment;
import com.edavtyan.materialplayer2.ui.search.base.SearchView;
import com.edavtyan.materialplayer2.ui.search.base.SearchViewImpl;

import javax.inject.Inject;

public class SearchArtistFragment extends ArtistListFragment implements SearchView {

	@Inject SearchViewImpl searchViewImpl;
	@Inject SearchArtistPresenter presenter;
	@Inject SearchArtistAdapter adapter;

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_list_search;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getComponent2().inject(this);
		initListView(presenter, adapter);
	}

	@Override
	public void onStart() {
		super.onStart();
		searchViewImpl.init();
	}

	@Override
	public void onStop() {
		super.onStop();
		searchViewImpl.destroy();
	}

	@Override
	public void showEmptyQuery() {
		searchViewImpl.showEmptyQuery();
	}

	@Override
	public void showEmptyResult() {
		searchViewImpl.showEmptyResult();
	}

	protected SearchArtistDIComponent getComponent2() {
		return DaggerSearchArtistDIComponent
				.builder()
				.appDIComponent(((App) getContext().getApplicationContext()).getAppComponent())
				.searchArtistDIModule(new SearchArtistDIModule(this))
				.artistListDIModule(new ArtistListDIModule(getActivity(), this))
				.build();
	}
}
