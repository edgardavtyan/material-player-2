package com.edavtyan.materialplayer2.ui.audio_effects.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.databinding.PartialEqualizerBandBinding;

import lombok.Getter;
import lombok.Setter;

public class EqualizerBandView
		extends FrameLayout
		implements DoubleSeekbar.OnProgressChangedListener,
				   DoubleSeekbar.OnStopTrackingTouchListener {

	private final PartialEqualizerBandBinding binding;

	private int frequency;
	private @Getter @Setter int index;
	private @Setter OnBandChangedListener onBandChangedListener;

	public interface OnBandChangedListener {
		void onBandStopTracking();
		void onBandChanged(EqualizerBandView band);
	}

	public EqualizerBandView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		binding = PartialEqualizerBandBinding.inflate(LayoutInflater.from(context), this, true);
		binding.band.setOnProgressChangedListener(this);
		binding.band.setOnStopTrackingTouchListener(this);
	}

	public void setGainLimit(int gain) {
		binding.band.setMax(gain);
	}

	public int getGain() {
		return binding.band.getProgress();
	}

	public void setGain(int gain) {
		binding.band.setProgress(gain);
		binding.gain.setText(getGainStr(gain));
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;

		int frequencyFormat;
		double frequencyConverted;

		boolean isKHz = frequency >= 1000;
		if (isKHz) {
			frequencyConverted = frequency / 1000f;

			boolean isWholeKHz = frequency % 1000 == 0;
			frequencyFormat = isWholeKHz
					? R.string.equalizer_frequency_khz_whole
					: R.string.equalizer_frequency_khz;
		} else {
			frequencyConverted = frequency;
			frequencyFormat = R.string.equalizer_frequency_hz;
		}

		binding.frequency.setText(getResources().getString(frequencyFormat, frequencyConverted));
	}

	@Override
	public void onStopTrackingTouch() {
		if (onBandChangedListener != null) {
			onBandChangedListener.onBandStopTracking();
		}
	}

	@Override
	public void onProgressChanged(int progress) {
		binding.gain.setText(getGainStr(progress));
		if (onBandChangedListener != null) {
			onBandChangedListener.onBandChanged(this);
		}
	}

	private String getGainStr(int gain) {
		int gainStringFormatId = gain > 0
				? R.string.equalizer_format_gain_positive
				: R.string.equalizer_format_gain;

		return getResources().getString(gainStringFormatId, gain);
	}
}
