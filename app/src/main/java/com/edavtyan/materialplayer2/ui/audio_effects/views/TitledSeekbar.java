package com.edavtyan.materialplayer2.ui.audio_effects.views;

import android.content.Context;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.edavtyan.materialplayer2.databinding.PartialTitledSeekbarBinding;

import lombok.Setter;

public class TitledSeekbar
		extends LinearLayout
		implements AppCompatSeekBar.OnSeekBarChangeListener {

	private final PartialTitledSeekbarBinding binding;

	private @Setter OnProgressChangedListener onProgressChangedListener;

	public interface OnProgressChangedListener {
		void onProgressChange(int seekbarId, int progress);
		void onStopTrackingTouch(int seekbarId);
	}

	public TitledSeekbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		TitledSeekbarAttributes attributes = new TitledSeekbarAttributes(context, attrs);

		binding = PartialTitledSeekbarBinding.inflate(LayoutInflater.from(context), this, true);
		binding.seekbar.setMax(attributes.getMax());
		binding.seekbar.setProgress(attributes.getProgress());
		binding.seekbar.setOnSeekBarChangeListener(this);
		binding.title.setText(attributes.getText());
		binding.title.setTextSize(TypedValue.COMPLEX_UNIT_PX, attributes.getTextSize());
		binding.title.getLayoutParams().width = attributes.getTextWidth();
	}

	public void setProgress(int progress) {
		binding.seekbar.setProgress(progress);
	}

	public void setMax(int max) {
		binding.seekbar.setMax(max);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		if (onProgressChangedListener != null && fromUser) {
			onProgressChangedListener.onProgressChange(getId(), progress);
		}
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		if (onProgressChangedListener != null) {
			onProgressChangedListener.onStopTrackingTouch(getId());
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}
}
