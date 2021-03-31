package com.edavtyan.materialplayer2.ui.search.album;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer2.App;
import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.ui.lists.album_list.AlbumListDIModule;
import com.edavtyan.materialplayer2.ui.lists.album_list.AlbumListFragment;
import com.edavtyan.materialplayer2.ui.search.base.SearchView;
import com.edavtyan.materialplayer2.ui.search.base.SearchViewImpl;

import javax.inject.Inject;

public class SearchAlbumFragment extends AlbumListFragment implements SearchView {

	@Inject SearchViewImpl searchViewImpl;
	@Inject SearchAlbumPresenter presenter;
	@Inject SearchAlbumAdapter adapter;

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

	public void showEmptyQuery() {
		searchViewImpl.showEmptyQuery();
	}

	public void showEmptyResult() {
		searchViewImpl.showEmptyResult();
	}

	protected SearchAlbumDIComponent getComponent2() {
		return DaggerSearchAlbumDIComponent
				.builder()
				.appDIComponent(((App)getContext().getApplicationContext()).getAppComponent())
				.searchAlbumDIModule(new SearchAlbumDIModule(this))
				.albumListDIModule(new AlbumListDIModule(getActivity(), this))
				.build();
	}
}
