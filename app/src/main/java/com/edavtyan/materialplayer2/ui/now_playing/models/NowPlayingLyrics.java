package com.edavtyan.materialplayer2.ui.now_playing.models;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.lib.lyrics.LyricsApi;
import com.edavtyan.materialplayer2.ui.now_playing.NowPlayingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;

public class NowPlayingLyrics {
	@BindView(R.id.lyrics_wrapper) @Getter FrameLayout wrapperView;
	@BindView(R.id.lyrics_text) TextView textView;
	@BindView(R.id.lyrics_instrumental) TextView instrumentalView;
	@BindView(R.id.lyrics_scroller) ScrollView scrollerView;
	@BindView(R.id.error_lyricsConnection) TextView connectionErrorView;
	@BindView(R.id.error_lyricsNotFound) TextView notFoundErrorView;

	private boolean isEnabled = true;

	@SuppressLint("ClickableViewAccessibility")
	public NowPlayingLyrics(NowPlayingActivity activity) {
		ButterKnife.bind(this, activity);
		scrollerView.setOnTouchListener((v, e) -> !isEnabled);
	}

	public void setLyrics(String lyrics) {
		if (lyrics.equals(LyricsApi.LYRICS_INSTRUMENTAL)) {
			instrumentalView.setVisibility(View.VISIBLE);
			textView.setVisibility(View.INVISIBLE);
		} else {
			instrumentalView.setVisibility(View.INVISIBLE);
			textView.setText(lyrics);
			textView.setVisibility(View.VISIBLE);
		}
	}

	public void hide() {
		wrapperView.animate().alpha(0f);
		isEnabled = false;
	}

	public void show() {
		wrapperView.animate().alpha(1f);
		isEnabled = true;
	}

	public void displayNotFoundError() {
		scrollerView.setVisibility(View.INVISIBLE);
		connectionErrorView.setVisibility(View.INVISIBLE);
		instrumentalView.setVisibility(View.INVISIBLE);
		notFoundErrorView.setVisibility(View.VISIBLE);
	}

	public void displayConnectionError() {
		scrollerView.setVisibility(View.INVISIBLE);
		notFoundErrorView.setVisibility(View.INVISIBLE);
		instrumentalView.setVisibility(View.INVISIBLE);
		connectionErrorView.setVisibility(View.VISIBLE);
	}
}
