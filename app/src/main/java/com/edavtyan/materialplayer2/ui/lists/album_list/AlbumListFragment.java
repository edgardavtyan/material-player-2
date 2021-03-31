package com.edavtyan.materialplayer2.ui.lists.album_list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer2.App;
import com.edavtyan.materialplayer2.db.types.Track;
import com.edavtyan.materialplayer2.lib.playlist.models.PlaylistPresenter;
import com.edavtyan.materialplayer2.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer2.lib.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer2.lib.transition.SourceSharedViews;
import com.edavtyan.materialplayer2.ui.Navigator;
import com.edavtyan.materialplayer2.ui.lists.lib.ListFragment;

import java.util.List;

import javax.inject.Inject;

public class AlbumListFragment extends ListFragment implements AlbumListView {

	@Inject Navigator navigator;
	@Inject AlbumListPresenter presenter;
	@Inject AlbumListAdapter adapter;
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
	public void gotoAlbumDetail(int albumId, SourceSharedViews sharedViews) {
		transitionsManager.pushSourceViews(sharedViews);
		navigator.gotoAlbumDetail(getActivity(), albumId, sharedViews.build());
	}

	@Override
	public void showPlaylistSelectionDialog(List<Track> tracks) {
		playlistPresenter.onAddToPlaylistClick(tracks);
	}

	@Override
	public void notifyItemChanged(int position) {
		adapter.notifyItemChanged(position);
	}

	protected AlbumListDIComponent getComponent() {
		return DaggerAlbumListDIComponent
				.builder()
				.appDIComponent(((App) getContext().getApplicationContext()).getAppComponent())
				.albumListDIModule(new AlbumListDIModule(getActivity(), this))
				.build();
	}
}
