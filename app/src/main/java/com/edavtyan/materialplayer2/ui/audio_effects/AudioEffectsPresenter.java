package com.edavtyan.materialplayer2.ui.audio_effects;

import com.edavtyan.materialplayer2.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer2.player.effects.equalizer.Equalizer;
import com.edavtyan.materialplayer2.service.PlayerService;
import com.edavtyan.materialplayer2.ui.audio_effects.presets.PresetNameAlreadyExists;
import com.edavtyan.materialplayer2.ui.audio_effects.views.EqualizerBandView;

public class AudioEffectsPresenter implements ModelServiceModule.OnServiceConnectedListener {

	private final AudioEffectsModel model;
	private final AudioEffectsActivity view;

	private String overwritingPresetName;

	public AudioEffectsPresenter(AudioEffectsModel model, AudioEffectsActivity view) {
		this.model = model;
		this.view = view;
	}

	public void onCreate() {
		model.init();
		model.setOnServiceConnectedListener(this);
	}

	public void onDestroy() {
		model.close();
	}

	public void onEqualizerEnabledChanged(boolean enabled) {
		if (!model.isConnected()) {
			return;
		}

		model.getEqualizer().setEnabled(enabled);
		model.getEqualizer().saveSettings();
	}

	public void onEqualizerBandChanged(EqualizerBandView band) {
		model.getEqualizer().setBandGain(band.getIndex(), band.getGain());
		view.setEqualizerPresetAsCustomNew();
	}

	public void onEqualizerBandStopTracking() {
		model.getEqualizer().saveSettings();
	}

	public void onPresetSelected(int relativePosition, Equalizer.PresetType presetType) {
		switch (presetType) {
		case CUSTOM_NEW:
			view.setDeletePresetButtonEnabled(false);
			return;
		case CUSTOM:
			view.setDeletePresetButtonEnabled(true);
			model.getEqualizer().useCustomPreset(relativePosition);
			break;
		case BUILT_IN:
			view.setDeletePresetButtonEnabled(false);
			model.getEqualizer().useBuiltInPreset(relativePosition);
			break;
		}

		view.setEqualizerBands(
				model.getEqualizer().getBandsCount(),
				model.getEqualizer().getGainLimit(),
				model.getEqualizer().getFrequencies(),
				model.getEqualizer().getGains());
	}

	public void onNewPresetDialogOkButtonClicked(String name) {
		try {
			model.getEqualizer().savePreset(name);
			view.setEqualizerPresets(
					model.getEqualizer().getBuiltInPresetNames(),
					model.getEqualizer().getCustomPresetNames());
			view.selectLastCustomPreset();
			view.closeNewPresetCreationDialog();
		} catch (PresetNameAlreadyExists presetNameAlreadyExists) {
			overwritingPresetName = name;
			view.showPresetAlreadyExists();
		}
	}

	public void onNewPresetDialogCancelButtonClicked() {
		view.closeNewPresetCreationDialog();
	}

	public void onOverwriteDialogConfirmed() {
		model.getEqualizer().saveAndOverwritePreset(overwritingPresetName);
		view.setEqualizerPresets(
				model.getEqualizer().getBuiltInPresetNames(),
				model.getEqualizer().getCustomPresetNames());
		view.selectLastCustomPreset();
		view.closeNewPresetCreationDialog();
	}

	public void onDeletePreset(int position) {
		model.getEqualizer().deletePreset(position);
		view.setEqualizerPresets(
				model.getEqualizer().getBuiltInPresetNames(),
				model.getEqualizer().getCustomPresetNames());
	}

	public void onCreateNewPresetButtonClicked() {
		view.showNewPresetCreationDialog(model.getEqualizer().getCurrentPresetName());
	}

	public void onBassBoostStrengthChanged(int strength) {
		model.getBassBoost().setStrength(strength);
	}

	public void onBassBoostStrengthStopChanging() {
		model.getBassBoost().saveSettings();
	}

	public void onSurroundStrengthChanged(int strength) {
		model.getSurround().setStrength(strength);
	}

	public void onSurroundStrengthStopChanging() {
		model.getSurround().saveSettings();
	}

	public void onServiceConnected(PlayerService service) {
		if (!model.isSupported()) {
			view.setEqualizerNotSupported();
			return;
		}

		view.setEqualizerEnabled(model.getEqualizer().isEnabled());
		view.setEqualizerBands(
				model.getEqualizer().getBandsCount(),
				model.getEqualizer().getGainLimit(),
				model.getEqualizer().getFrequencies(),
				model.getEqualizer().getGains());
		view.setEqualizerPresets(
				model.getEqualizer().getBuiltInPresetNames(),
				model.getEqualizer().getCustomPresetNames());
		view.setCurrentEqualizerPreset(
				model.getEqualizer().getCurrentPresetIndex(),
				model.getEqualizer().getCurrentPresetType());
		view.setDeletePresetButtonEnabled(model.getEqualizer().isUsingSavedCustomPreset());

		view.initBassBoost(
				model.getBassBoost().getMaxStrength(),
				model.getBassBoost().getStrength());

		view.initSurround(
				model.getSurround().getMaxStrength(),
				model.getSurround().getStrength());
	}
}
