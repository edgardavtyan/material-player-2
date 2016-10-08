package com.edavtyan.materialplayer.components.player2;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import java.util.List;

import lombok.Setter;

public class Player
		implements PlayerMvp.Player,
				   PlayerMvp.AudioEngine.OnPreparedListener {

	private final PlayerMvp.AudioEngine audioEngine;
	private final PlayerMvp.Queue queue;

	private @Setter OnNewTrackListener onNewTrackListener;

	public Player(
			PlayerMvp.AudioEngine audioEngine,
			PlayerMvp.Queue queue,
			AdvancedSharedPrefs prefs) {
		this.audioEngine = audioEngine;
		this.audioEngine.setOnPreparedListener(this);

		RepeatMode repeatMode = prefs.getEnum(PREF_REPEAT_MODE, DEFAULT_REPEAT_MODE);
		ShuffleMode shuffleMode = prefs.getEnum(PREF_SHUFFLE_MODE, DEFAULT_SHUFFLE_MODE);
		this.queue = queue;
		this.queue.setRepeatMode(repeatMode);
		this.queue.setShuffleMode(shuffleMode);
	}

	@Override public void addTrack(Track track) {
		queue.addTrack(track);
	}

	@Override public void addManyTracks(List<Track> tracks) {
		queue.addManyTracks(tracks);
	}

	@Override public void removeTrackAt(int position) {
		queue.removeTrackAt(position);
	}

	@Override public void playNewTracks(List<Track> tracks, int position) {
		queue.clear();
		queue.addManyTracks(tracks);
		queue.setPosition(position);
		audioEngine.playTrack(queue.getCurrentTrack().getPath());
	}

	@Override public void playTrackAt(int position) {
		queue.setPosition(position);
		audioEngine.playTrack(queue.getCurrentTrack().getPath());
	}

	@Override public Track getTrackAt(int position) {
		return queue.getTrackAt(position);
	}

	@Override public Track getCurrentTrack() {
		return queue.getCurrentTrack();
	}

	@Override public int getTracksCount() {
		return queue.getTracksCount();
	}

	@Override public boolean hasData() {
		return queue.hasData();
	}

	@Override public void play() {
		audioEngine.play();
	}

	@Override public void pause() {
		audioEngine.pause();
	}

	@Override public void playPause() {
		audioEngine.playPause();
	}

	@Override public void playNext() {
		if (queue.isEnded()) return;

		queue.moveToNext();
		audioEngine.playTrack(queue.getCurrentTrack().getPath());
	}

	@Override public void rewind() {
		if (audioEngine.getPosition() >= 5000) {
			audioEngine.setPosition(0);
		} else {
			queue.moveToPrev();
			audioEngine.playTrack(queue.getCurrentTrack().getPath());
		}
	}

	@Override public long getPosition() {
		return audioEngine.getPosition();
	}

	@Override public void setPosition(int position) {
		audioEngine.setPosition(position);
	}

	@Override public ShuffleMode getShuffleMode() {
		return queue.getShuffleMode();
	}

	@Override public RepeatMode getRepeatMode() {
		return queue.getRepeatMode();
	}

	@Override public boolean isPlaying() {
		return audioEngine.isPlaying();
	}

	@Override public void toggleShuffleMode() {
		queue.toggleShuffleMode();
	}

	@Override public void toggleRepeatMode() {
		queue.toggleRepeatMode();
	}

	@Override public void onPrepared() {
		if (onNewTrackListener != null) onNewTrackListener.onNewTrack();
	}
}
