package com.edavtyan.materialplayer2.lib.playlist;

import android.app.Activity;

import com.edavtyan.materialplayer2.lib.playlist.dialogs.PlaylistNewDialog;
import com.edavtyan.materialplayer2.lib.playlist.dialogs.PlaylistSelectDialog;
import com.edavtyan.materialplayer2.lib.playlist.models.PlaylistManager;
import com.edavtyan.materialplayer2.lib.playlist.models.PlaylistPresenter;
import com.edavtyan.materialplayer2.ui.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PlaylistDIModuleFragment {
	@Provides
	@FragmentScope
	public PlaylistSelectDialog providePlaylistSelectDialog(Activity activity) {
		return new PlaylistSelectDialog(activity);
	}

	@Provides
	@FragmentScope
	public PlaylistNewDialog providePlaylistNewDialog(Activity activity) {
		return new PlaylistNewDialog(activity);
	}

	@Provides
	@FragmentScope
	public PlaylistPresenter providePlaylistDialogPresenter(
			PlaylistSelectDialog addDialog,
			PlaylistNewDialog newDialog,
			PlaylistManager manager) {
		return new PlaylistPresenter(addDialog, newDialog, manager);
	}
}
