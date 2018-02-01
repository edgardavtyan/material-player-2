package com.edavtyan.materialplayer.screens.lists.artist_list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.CurrentSharedViews;
import com.edavtyan.materialplayer.screens.Navigator;
import com.edavtyan.materialplayer.screens.detail.lib.DetailSharedViews;
import com.edavtyan.materialplayer.screens.lists.lib.ListFragment;

import javax.inject.Inject;

public class ArtistListFragment extends ListFragment implements ArtistListView {

	@Inject Navigator navigator;
	@Inject ArtistListPresenter presenter;
	@Inject ArtistListAdapter adapter;
	@Inject CurrentSharedViews currentSharedViews;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getComponent().inject(this);
		initListView(presenter, adapter);
	}

	@Override
	public void gotoArtistDetailNormal(String title) {
		navigator.gotoArtistDetailNormal(title);
	}

	@Override
	public void gotoArtistDetailCompact(String title, DetailSharedViews sharedViews) {
		currentSharedViews.push(sharedViews);
		navigator.gotoArtistDetailCompact(getActivity(), title, sharedViews.build());

	}

	protected ArtistListComponent getComponent() {
		return DaggerArtistListComponent
				.builder()
				.appComponent(((App) getContext().getApplicationContext()).getAppComponent())
				.artistListFactory(new ArtistListFactory(getActivity(), this))
				.build();
	}
}
