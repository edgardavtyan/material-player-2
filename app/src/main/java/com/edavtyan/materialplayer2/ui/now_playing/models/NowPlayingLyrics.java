package com.edavtyan.materialplayer2.ui.now_playing.models;

import android.annotation.SuppressLint;
import android.view.View;

import com.edavtyan.materialplayer2.databinding.ActivityNowplayingBinding;
import com.edavtyan.materialplayer2.lib.lyrics.LyricsApi;

public class NowPlayingLyrics {
	private final ActivityNowplayingBinding binding;
	private boolean isEnabled = true;

	@SuppressLint("ClickableViewAccessibility")
	public NowPlayingLyrics(ActivityNowplayingBinding binding) {
		this.binding = binding;
		binding.lyricsScroller.setOnTouchListener((v, e) -> !isEnabled);
	}

	public void setLyrics(String lyrics) {
		if (lyrics.equals(LyricsApi.LYRICS_INSTRUMENTAL)) {
			binding.lyricsInstrumental.setVisibility(View.VISIBLE);
			binding.lyricsText.setVisibility(View.INVISIBLE);
		} else {
			binding.lyricsInstrumental.setVisibility(View.INVISIBLE);
			binding.lyricsText.setText(lyrics);
			binding.lyricsText.setVisibility(View.VISIBLE);
		}
	}

	public void hide() {
		binding.lyricsWrapper.animate().alpha(0f);
		isEnabled = false;
	}

	public void show() {
		binding.lyricsWrapper.animate().alpha(1f);
		isEnabled = true;
	}

	public void displayNotFoundError() {
		binding.lyricsScroller.setVisibility(View.INVISIBLE);
		binding.errorLyricsConnection.setVisibility(View.INVISIBLE);
		binding.lyricsInstrumental.setVisibility(View.INVISIBLE);
		binding.errorLyricsNotFound.setVisibility(View.VISIBLE);
	}

	public void displayConnectionError() {
		binding.lyricsScroller.setVisibility(View.INVISIBLE);
		binding.errorLyricsNotFound.setVisibility(View.INVISIBLE);
		binding.lyricsInstrumental.setVisibility(View.INVISIBLE);
		binding.errorLyricsConnection.setVisibility(View.VISIBLE);
	}
}
