package com.edavtyan.materialplayer.components.now_playing;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.edavtyan.materialplayer.components.player2.PlayerService;
import com.edavtyan.materialplayer.components.player2.RepeatMode;
import com.edavtyan.materialplayer.components.player2.ShuffleMode;
import com.edavtyan.materialplayer.utils.ArtProvider;

import java.io.File;

import lombok.Setter;

public class NowPlayingModel implements NowPlayingMvp.Model {
	private final Context context;

	private PlayerService service;

	@Setter OnModelBoundListener onModelBoundListener;

	public NowPlayingModel(Context context) {
		this.context = context;
	}

	@Override
	public void bind() {
		Intent intent = new Intent(context, PlayerService.class);
		context.bindService(intent, this, Context.BIND_AUTO_CREATE);
	}

	@Override
	public void unbind() {
		context.unbindService(this);
	}

	@Override
	public RepeatMode getRepeatMode() {
		return service.getPlayer().getRepeatMode();
	}

	@Override
	public boolean isPlaying() {
		return service.getPlayer().isPlaying();
	}

	@Override
	public ShuffleMode getShuffleMode() {
		return service.getPlayer().getShuffleMode();
	}

	@Override
	public void toggleRepeatMode() {
		service.getPlayer().toggleRepeatMode();
	}

	@Override
	public void toggleShuffleMode() {
		service.getPlayer().toggleShuffleMode();
	}

	@Override
	public void playPause() {
		service.getPlayer().playPause();
	}

	@Override
	public String getTitle() {
		return service.getPlayer().getCurrentTrack().getTitle();
	}

	@Override
	public String getArtist() {
		return service.getPlayer().getCurrentTrack().getArtistTitle();
	}

	@Override
	public String getAlbum() {
		return service.getPlayer().getCurrentTrack().getAlbumTitle();
	}

	@Override
	public int getDuration() {
		return (int) service.getPlayer().getCurrentTrack().getDuration();
	}

	@Override
	public int getPosition() {
		return (int) service.getPlayer().getPosition();
	}

	@Override
	public File getArt() {
		return ArtProvider.fromTrack(service.getPlayer().getCurrentTrack());
	}

	@Override
	public void seek(int positionMillis) {
		service.getPlayer().setPosition(positionMillis);
	}

	@Override
	public void rewind() {
		service.getPlayer().rewind();
	}

	@Override
	public void fastForward() {
		service.getPlayer().playNext();
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder binder) {
		service = ((PlayerService.PlayerBinder) binder).getService();
		if (onModelBoundListener != null) {
			onModelBoundListener.onModelBound();
		}
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
	}
}
