package com.edavtyan.materialplayer2.ui.now_playing.models;

import android.view.View;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.databinding.ActivityNowplayingBinding;
import com.edavtyan.materialplayer2.player.RepeatMode;
import com.edavtyan.materialplayer2.player.ShuffleMode;
import com.edavtyan.materialplayer2.ui.now_playing.NowPlayingPresenter;

public class NowPlayingControls implements View.OnClickListener {
	public static final int CONTROL_ENABLED_ALPHA = 255;
	public static final int CONTROL_DISABLED_ALPHA = 60;

	private final NowPlayingPresenter presenter;
	private final ActivityNowplayingBinding binding;

	public NowPlayingControls(
			ActivityNowplayingBinding binding,
			NowPlayingPresenter presenter) {
		this.binding = binding;
		this.presenter = presenter;

		binding.shuffle.setOnClickListener(this);
		binding.repeat.setOnClickListener(this);
		binding.rewind.setOnClickListener(this);
		binding.playPause.setOnClickListener(this);
		binding.fastForward.setOnClickListener(this);
	}

	public void setShuffleMode(ShuffleMode shuffleMode) {
		switch (shuffleMode) {
		case ENABLED:
			binding.shuffle.setImageAlpha(CONTROL_ENABLED_ALPHA);
			break;
		case DISABLED:
			binding.shuffle.setImageAlpha(CONTROL_DISABLED_ALPHA);
			break;
		}
	}

	public void setRepeatMode(RepeatMode repeatMode) {
		switch (repeatMode) {
		case REPEAT_ALL:
			binding.repeat.setImageResource(R.drawable.ic_repeat);
			binding.repeat.setImageAlpha(CONTROL_ENABLED_ALPHA);
			break;
		case REPEAT_ONE:
			binding.repeat.setImageResource(R.drawable.ic_repeat_one);
			binding.repeat.setImageAlpha(CONTROL_ENABLED_ALPHA);
			break;
		case DISABLED:
			binding.repeat.setImageResource(R.drawable.ic_repeat);
			binding.repeat.setImageAlpha(CONTROL_DISABLED_ALPHA);
			break;
		}
	}

	public void setIsPlaying(boolean isPlaying) {
		binding.playPause.setImageResource(isPlaying ? R.drawable.ic_pause : R.drawable.ic_play);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.shuffle:
			presenter.onShuffleClick();
			break;
		case R.id.rewind:
			presenter.onRewindClick();
			break;
		case R.id.play_pause:
			presenter.onPlayPauseClick();
			break;
		case R.id.fast_forward:
			presenter.onFastForwardClick();
			break;
		case R.id.repeat:
			presenter.onRepeatClick();
			break;
		}
	}
}
