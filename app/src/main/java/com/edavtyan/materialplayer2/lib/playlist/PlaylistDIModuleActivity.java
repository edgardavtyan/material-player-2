package com.edavtyan.materialplayer2.lib.playlist;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer2.lib.playlist.dialogs.PlaylistNewDialog;
import com.edavtyan.materialplayer2.lib.playlist.dialogs.PlaylistSelectDialog;
import com.edavtyan.materialplayer2.lib.playlist.models.PlaylistManager;
import com.edavtyan.materialplayer2.lib.playlist.models.PlaylistPresenter;
import com.edavtyan.materialplayer2.ui.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PlaylistDIModuleActivity {
	@Provides
	@ActivityScope
	public PlaylistSelectDialog providePlaylistSelectDialog(AppCompatActivity activity) {
		return new PlaylistSelectDialog(activity);
	}

	@Provides
	@ActivityScope
	public PlaylistNewDialog providePlaylistNewDialog(AppCompatActivity activity) {
		return new PlaylistNewDialog(activity);
	}

	@Provides
	@ActivityScope
	public PlaylistPresenter providePlaylistDialogPresenter(
			PlaylistSelectDialog addDialog,
			PlaylistNewDialog newDialog,
			PlaylistManager manager) {
		return new PlaylistPresenter(addDialog, newDialog, manager);
	}
}
