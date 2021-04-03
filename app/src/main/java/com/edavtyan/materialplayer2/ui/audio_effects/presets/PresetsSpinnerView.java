package com.edavtyan.materialplayer2.ui.audio_effects.presets;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.player.effects.equalizer.Equalizer;
import com.edavtyan.materialplayer2.ui.audio_effects.AudioEffectsPresenter;

import java.util.List;

import butterknife.ButterKnife;

public class PresetsSpinnerView {
	private static final int CUSTOM_NEW_PRESET_POSITION = 0;

	private final Spinner presetsSpinner;

	private final PresetsAdapter presetsAdapter;
	private final AudioEffectsPresenter presenter;

	private boolean presetsSpinnerFirstLaunch = true;
	private int customPresetsSize;

	@SuppressWarnings("FieldCanBeLocal")
	private final AdapterView.OnItemSelectedListener onPresetSelectedListener
			= new AdapterView.OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			if (presetsSpinnerFirstLaunch) {
				presetsSpinnerFirstLaunch = false;
				return;
			}

			presenter.onPresetSelected(getSelectedPresetRelativePosition(), getSelectedPresetType());
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
		}
	};

	public PresetsSpinnerView(Activity activity, AudioEffectsPresenter presenter) {
		ButterKnife.bind(this, activity);
		presetsSpinner = activity.findViewById(R.id.presets_spinner);

		this.presenter = presenter;

		presetsAdapter = new PresetsAdapter(activity);
		presetsSpinner.setAdapter(presetsAdapter);
		presetsSpinner.setOnItemSelectedListener(onPresetSelectedListener);
	}

	public void setPresets(List<String> builtInPresets, List<String> customPresets) {
		presetsAdapter.setPresets(builtInPresets, customPresets);
		customPresetsSize = customPresets.size();
	}

	public void setCurrentPresetAsCustomNew() {
		presetsSpinner.setSelection(CUSTOM_NEW_PRESET_POSITION);
	}

	public void selectPresetAt(int presetIndex, Equalizer.PresetType presetType) {
		switch (presetType) {
		case CUSTOM_NEW:
			presetsSpinner.setSelection(0);
			break;
		case CUSTOM:
			presetsSpinner.setSelection(presetIndex + 1);
			break;
		case BUILT_IN:
			presetsSpinner.setSelection(presetIndex + 1 + customPresetsSize);
			break;
		}
	}

	public void selectLastCustomPreset() {
		presetsSpinner.setSelection(customPresetsSize);
	}

	public void deleteCurrentPreset() {
		// Offset selection by 1 since first element in spinner is always 'Custom'
		presenter.onDeletePreset(presetsSpinner.getSelectedItemPosition() - 1);
		presetsAdapter.notifyDataSetChanged();
	}

	public Equalizer.PresetType getSelectedPresetType() {
		int position = presetsSpinner.getSelectedItemPosition();
		if (position == 0) {
			return Equalizer.PresetType.CUSTOM_NEW;
		} else if (0 <= position && position <= customPresetsSize) {
			return Equalizer.PresetType.CUSTOM;
		} else {
			return Equalizer.PresetType.BUILT_IN;
		}
	}

	public int getSelectedPresetRelativePosition() {
		int position = presetsSpinner.getSelectedItemPosition();
		if (position == 0) {
			return -1;
		} else if (0 <= position && position <= customPresetsSize) {
			return position - 1;
		} else {
			return position - 1 - customPresetsSize;
		}
	}
}
