package com.edavtyan.materialplayer2.ui.now_playing_bar;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer2.db.types.Track;
import com.edavtyan.materialplayer2.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer2.player.Player;
import com.edavtyan.materialplayer2.service.PlayerService;

import lombok.Setter;

public class NowPlayingBarModel
		implements ServiceConnection,
				   Player.OnNewTrackListener, Player.OnPlayPauseListener {

	private final Context context;
	private final AlbumArtProvider albumArtProvider;

	private PlayerService service;

	private @Setter @Nullable OnNewTrackListener onNewTrackListener;
	private @Setter @Nullable OnServiceConnectedListener onServiceConnectedListener;
	private @Setter @Nullable OnPlayPauseListener onPlayPauseListener;

	public interface OnNewTrackListener {
		void onNewTrack();
	}

	public interface OnPlayPauseListener {
		void onPlayPause();
	}

	public interface OnServiceConnectedListener {
		void onServiceConnected();
	}

	public NowPlayingBarModel(Context context, AlbumArtProvider albumArtProvider) {
		this.context = context;
		this.albumArtProvider = albumArtProvider;
	}

	public void bind() {
		Intent intent = new Intent(context, PlayerService.class);
		context.bindService(intent, this, Context.BIND_AUTO_CREATE);
	}

	public void unbind() {
		if (service == null) return;
		service.getPlayer().removeOnNewTrackListener(this);
		context.unbindService(this);
	}

	public void togglePlayPause() {
		service.getPlayer().playPause();
	}

	public Track getNowPlayingTrack() {
		return service.getPlayer().getCurrentTrack();
	}

	@Nullable
	public Bitmap getNowPlayingTrackArt() {
		return albumArtProvider.load(getNowPlayingTrack());
	}

	public boolean isPlaying() {
		return service.getPlayer().isPlaying();
	}

	public boolean hasData() {
		return service.getPlayer().hasData();
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder binder) {
		service = ((PlayerService.PlayerBinder) binder).getService();
		service.getPlayer().setOnNewTrackListener(this);
		service.getPlayer().setOnPlayPauseListener(this);

		if (onServiceConnectedListener != null) {
			onServiceConnectedListener.onServiceConnected();
		}
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
	}

	@Override
	public void onNewTrack() {
		if (onNewTrackListener != null) {
			onNewTrackListener.onNewTrack();
		}
	}

	@Override
	public void onPlayPause() {
		if (onPlayPauseListener != null) {
			onPlayPauseListener.onPlayPause();
		}
	}
}
