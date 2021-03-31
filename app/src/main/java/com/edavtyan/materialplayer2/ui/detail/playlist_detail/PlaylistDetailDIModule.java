package com.edavtyan.materialplayer2.ui.detail.playlist_detail;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer2.ui.ActivityScope;
import com.edavtyan.materialplayer2.lib.playlist.models.PlaylistManager;

import dagger.Module;
import dagger.Provides;

@Module
public class PlaylistDetailDIModule {
	private final PlaylistDetailActivity activity;
	private final String playlistName;

	public PlaylistDetailDIModule(PlaylistDetailActivity activity, String playlistName) {
		this.activity = activity;
		this.playlistName = playlistName;
	}

	@Provides
	@ActivityScope
	public AppCompatActivity provideAppCompatActivity() {
		return activity;
	}

	@Provides
	@ActivityScope
	public PlaylistDetailAdapter providePlaylistDetailAdapter(PlaylistDetailPresenter presenter) {
		return new PlaylistDetailAdapter(activity, presenter);
	}

	@Provides
	@ActivityScope
	public PlaylistDetailPresenter providePlaylistDetailPresenter(PlaylistManager manager) {
		return new PlaylistDetailPresenter(manager, activity, playlistName);
	}
}
