package com.edavtyan.materialplayer2.ui.now_playing.models;

import android.widget.SeekBar;

import com.ed.libsutils.utils.DurationUtils;
import com.edavtyan.materialplayer2.databinding.ActivityNowplayingBinding;
import com.edavtyan.materialplayer2.ui.now_playing.NowPlayingPresenter;

public class NowPlayingSeekbar
		implements SeekBar.OnSeekBarChangeListener {

	private final NowPlayingPresenter presenter;
	private final ActivityNowplayingBinding binding;

	public NowPlayingSeekbar(ActivityNowplayingBinding binding, NowPlayingPresenter presenter) {
		this.binding = binding;
		this.presenter = presenter;
		binding.seekbar.setOnSeekBarChangeListener(this);
	}

	public void setPosition(int timeMS) {
		binding.seekbar.setProgress(timeMS);
	}

	public void setPositionText(int timeMS) {
		binding.timeCurrent.setText(DurationUtils.toStringUntilHours(timeMS));
	}

	public void setDuration(int durationMS) {
		binding.seekbar.setMax(durationMS);
	}

	public void setDurationText(int durationMS) {
		binding.timeTotal.setText(DurationUtils.toStringUntilHours(durationMS));
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		if (fromUser) presenter.onSeekChanged(progress);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		presenter.onSeekStop(seekBar.getProgress());
	}
}
