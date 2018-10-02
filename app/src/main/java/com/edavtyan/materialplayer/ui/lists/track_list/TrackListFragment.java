package com.edavtyan.materialplayer.ui.lists.track_list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer.ui.Navigator;
import com.edavtyan.materialplayer.ui.lists.lib.ListFragment;
import com.edavtyan.materialplayer.ui.lists.playlist_list.PlaylistAddDialog;
import com.edavtyan.materialplayer.ui.lists.playlist_list.PlaylistDialogPresenter;
import com.edavtyan.materialplayer.ui.lists.playlist_list.PlaylistNewDialog;

import javax.inject.Inject;

public class TrackListFragment extends ListFragment implements TrackListView {

	@Inject Navigator navigator;
	@Inject TrackListPresenter presenter;
	@Inject TrackListAdapter adapter;
	@Inject SharedTransitionsManager transitionsManager;
	@Inject PlaylistAddDialog playlistAddDialog;
	@Inject PlaylistNewDialog playlistNewDialog;
	@Inject ScreenThemeModule screenThemeModule;
	@Inject PlaylistDialogPresenter playlistPresenter;

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
	public void onThemeChanged(ThemeColors colors) {
		super.onThemeChanged(colors);
		playlistAddDialog.setTint(colors.getColorPrimary());
		playlistNewDialog.setTint(colors.getColorPrimary());
	}

	@Override
	public void showAddToPlaylistDialog(Track track) {
		playlistPresenter.onAddToPlaylistClick(track);
	}
}
