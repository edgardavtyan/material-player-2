package com.edavtyan.materialplayer2.ui.audio_effects;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CompoundButton;

import com.edavtyan.materialplayer2.App;
import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.base.BaseActivity;
import com.edavtyan.materialplayer2.databinding.ActivityEffectsBinding;
import com.edavtyan.materialplayer2.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer2.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer2.modular.activity.modules.ActivityToolbarModule;
import com.edavtyan.materialplayer2.player.effects.equalizer.Equalizer;
import com.edavtyan.materialplayer2.ui.audio_effects.presets.NewPresetDialog;
import com.edavtyan.materialplayer2.ui.audio_effects.presets.PresetOverwriteDialog;
import com.edavtyan.materialplayer2.ui.audio_effects.presets.PresetsSpinnerView;
import com.edavtyan.materialplayer2.ui.audio_effects.views.EqualizerBandView;
import com.edavtyan.materialplayer2.ui.audio_effects.views.EqualizerView;
import com.edavtyan.materialplayer2.ui.audio_effects.views.TitledSeekbar;

import java.util.List;

import javax.inject.Inject;

public class AudioEffectsActivity
		extends BaseActivity
		implements CompoundButton.OnCheckedChangeListener,
				   TitledSeekbar.OnProgressChangedListener,
				   EqualizerView.OnBandChangedListener {

	@Inject ScreenThemeModule themeModule;
	@Inject ActivityBaseMenuModule baseMenuModule;
	@Inject ActivityToolbarModule toolbarModule;
	@Inject AudioEffectsPresenter presenter;
	@Inject NewPresetDialog newPresetDialog;
	@Inject PresetOverwriteDialog presetOverwriteDialog;
	@Inject PresetsSpinnerView presetsSpinner;

	private ActivityEffectsBinding binding;

	private final View.OnClickListener onNewPresetClicked = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			presenter.onCreateNewPresetButtonClicked();
		}
	};

	private final View.OnClickListener onDeletePresetClicked = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			presetsSpinner.deleteCurrentPreset();
		}
	};

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = ActivityEffectsBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		binding.equalizerSwitch.setOnCheckedChangeListener(this);
		binding.equalizer.setOnBandChangedListener(this);
		binding.bassBoost.setOnProgressChangedListener(this);
		binding.surround.setOnProgressChangedListener(this);
		binding.presetNew.setOnClickListener(onNewPresetClicked);
		binding.presetRemove.setOnClickListener(onDeletePresetClicked);

		getComponent().inject(this);
		addModule(baseMenuModule);
		addModule(toolbarModule);
		addModule(themeModule);

		presenter.onCreate();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		presenter.onDestroy();
	}

	public void setEqualizerEnabled(boolean enabled) {
		binding.equalizerSwitch.setChecked(enabled);
		binding.equalizerSwitch.jumpDrawablesToCurrentState();
	}

	public void setEqualizerBands(int count, int gainLimit, int[] frequencies, int[] gains) {
		binding.equalizer.setBands(count, frequencies, gains, gainLimit);
	}

	public void setEqualizerPresets(List<String> builtInPresets, List<String> customPresets) {
		presetsSpinner.setPresets(builtInPresets, customPresets);
	}

	public void setEqualizerPresetAsCustomNew() {
		presetsSpinner.setCurrentPresetAsCustomNew();
	}

	public void selectLastCustomPreset() {
		presetsSpinner.selectLastCustomPreset();
	}

	public void showNewPresetCreationDialog(String presetName) {
		newPresetDialog.show(presetName);
	}

	public void closeNewPresetCreationDialog() {
		newPresetDialog.close();
	}

	public void showPresetAlreadyExists() {
		presetOverwriteDialog.show();
	}

	public void setDeletePresetButtonEnabled(boolean enabled) {
		if (enabled) {
			binding.presetRemove.setAlpha(1.0f);
			binding.presetRemove.setEnabled(true);
		} else {
			binding.presetRemove.setAlpha(0.5f);
			binding.presetRemove.setEnabled(false);
		}
	}

	public void initBassBoost(int max, int strength) {
		binding.bassBoost.setMax(max);
		binding.bassBoost.setProgress(strength);
	}

	public void initSurround(int max, int strength) {
		binding.surround.setMax(max);
		binding.surround.setProgress(strength);
	}

	public void setCurrentEqualizerPreset(int presetIndex, Equalizer.PresetType presetType) {
		presetsSpinner.selectPresetAt(presetIndex, presetType);
	}

	public void setEqualizerNotSupported() {
		binding.errorNotSupported.setVisibility(View.VISIBLE);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		presenter.onEqualizerEnabledChanged(isChecked);
	}

	@Override
	public void onBandChanged(EqualizerBandView band) {
		presenter.onEqualizerBandChanged(band);
	}

	@Override
	public void onBandStopTracking() {
		presenter.onEqualizerBandStopTracking();
	}

	@Override
	public void onProgressChange(int seekbarId, int progress) {
		switch (seekbarId) {
		case R.id.bass_boost:
			presenter.onBassBoostStrengthChanged(progress);
			break;
		case R.id.surround:
			presenter.onSurroundStrengthChanged(progress);
			break;
		}
	}

	@Override
	public void onStopTrackingTouch(int seekbarId) {
		switch (seekbarId) {
		case R.id.bass_boost:
			presenter.onBassBoostStrengthStopChanging();
			break;
		case R.id.surround:
			presenter.onSurroundStrengthStopChanging();
			break;
		}
	}

	protected AudioEffectsDIComponent getComponent() {
		return DaggerAudioEffectsDIComponent
				.builder()
				.appDIComponent(((App) getApplication()).getAppComponent())
				.audioEffectsDIModule(new AudioEffectsDIModule(this))
				.build();
	}
}
