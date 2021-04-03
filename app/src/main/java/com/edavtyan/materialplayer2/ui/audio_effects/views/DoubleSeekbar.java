package com.edavtyan.materialplayer2.ui.audio_effects.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import com.edavtyan.materialplayer2.databinding.ViewDoubleSeekbarBinding;

import lombok.Setter;

public class DoubleSeekbar extends FrameLayout implements SeekBar.OnSeekBarChangeListener {
	private final ViewDoubleSeekbarBinding binding;

	private @Setter OnStopTrackingTouchListener onStopTrackingTouchListener;
	private @Setter OnProgressChangedListener onProgressChangedListener;

	interface OnStopTrackingTouchListener {
		void onStopTrackingTouch();
	}

	interface OnProgressChangedListener {
		void onProgressChanged(int progress);
	}

	public DoubleSeekbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		binding = ViewDoubleSeekbarBinding.inflate(LayoutInflater.from(context), this);
		binding.seekbar.setOnSeekBarChangeListener(this);
	}

	public int getMax() {
		return binding.seekbar.getMax() / 2;
	}

	public void setMax(int max) {
		binding.seekbar.setMax(max * 2);
	}

	public int getProgress() {
		return binding.seekbar.getProgress() - getMax();
	}

	public void setProgress(int progress) {
		binding.seekbar.setProgress(progress + getMax());
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		if (onProgressChangedListener != null) {
			onProgressChangedListener.onProgressChanged(getProgress());
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		if (onStopTrackingTouchListener != null) {
			onStopTrackingTouchListener.onStopTrackingTouch();
		}
	}
}
