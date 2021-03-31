package com.edavtyan.materialplayer2.ui.lists.artist_list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer2.App;
import com.edavtyan.materialplayer2.db.types.Track;
import com.edavtyan.materialplayer2.lib.playlist.models.PlaylistPresenter;
import com.edavtyan.materialplayer2.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer2.lib.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer2.ui.Navigator;
import com.edavtyan.materialplayer2.ui.lists.lib.ListFragment;

import java.util.List;

import javax.inject.Inject;

public class ArtistListFragment extends ListFragment implements ArtistListView {

	@Inject Navigator navigator;
	@Inject ArtistListPresenter presenter;
	@Inject ArtistListAdapter adapter;
	@Inject SharedTransitionsManager transitionsManager;
	@Inject PlaylistPresenter playlistPresenter;
	@Inject ScreenThemeModule screenThemeModule;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getComponent().inject(this);
		initListView(presenter, adapter);
		addModule(screenThemeModule);
	}

	@Override
	public void gotoArtistDetail(String title) {
		navigator.gotoArtistDetailCompact(getActivity(), title);

	}

	@Override
	public void showPlaylistSelectionDialog(List<Track> tracks) {
		playlistPresenter.onAddToPlaylistClick(tracks);
	}

	protected ArtistListDIComponent getComponent() {
		return DaggerArtistListDIComponent
				.builder()
				.appDIComponent(((App) getContext().getApplicationContext()).getAppComponent())
				.artistListDIModule(new ArtistListDIModule(getActivity(), this))
				.build();
	}
}
