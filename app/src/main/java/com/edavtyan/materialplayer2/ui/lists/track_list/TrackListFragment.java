package com.edavtyan.materialplayer2.ui.lists.track_list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer2.App;
import com.edavtyan.materialplayer2.db.types.Track;
import com.edavtyan.materialplayer2.lib.playlist.models.PlaylistPresenter;
import com.edavtyan.materialplayer2.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer2.lib.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer2.ui.Navigator;
import com.edavtyan.materialplayer2.ui.lists.lib.ListFragment;

import javax.inject.Inject;

public class TrackListFragment extends ListFragment implements TrackListView {

	@Inject Navigator navigator;
	@Inject TrackListPresenter presenter;
	@Inject TrackListAdapter adapter;
	@Inject SharedTransitionsManager transitionsManager;
	@Inject ScreenThemeModule screenThemeModule;
	@Inject PlaylistPresenter playlistPresenter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getComponent().inject(this);
		initListView(presenter, adapter);
		addModule(screenThemeModule);
	}

	protected TrackListDIComponent getComponent() {
		return DaggerTrackListDIComponent
				.builder()
				.appDIComponent(((App) getContext().getApplicationContext()).getAppComponent())
				.trackListDIModule(new TrackListDIModule(getActivity(), this))
				.build();
	}

	@Override
	public void showPlaylistSelectionDialog(Track track) {
		playlistPresenter.onAddToPlaylistClick(track);
	}
}
