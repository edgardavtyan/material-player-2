package com.edavtyan.materialplayer.components.audio_effects;

import com.edavtyan.materialplayer.components.audio_effects.amplifier.Amplifier;
import com.edavtyan.materialplayer.components.audio_effects.bassboost.BassBoost;
import com.edavtyan.materialplayer.components.audio_effects.equalizer.Equalizer;
import com.edavtyan.materialplayer.components.audio_effects.surround.Surround;
import com.edavtyan.materialplayer.components.audio_effects.views.EqualizerBandView;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import java.util.List;

@SuppressWarnings("unused")
public interface AudioEffectsMvp {
	interface Model {
		void setOnServiceConnectedListener(ModelServiceModule.OnServiceConnectedListener listener);
		void init();
		void close();
		boolean isConnected();
		Equalizer getEqualizer();
		BassBoost getBassBoost();
		Surround getSurround();
		Amplifier getAmplifier();
	}

	interface View {
		void setEqualizerEnabled(boolean enabled);
		void setEqualizerBands(int count, int gainLimit, int[] frequencies, int[] gains);
		void setEqualizerPresets(List<String> builtInPresets, List<String> customPresets);
		void setEqualizerPresetAsCustomNew();
		void selectLastCustomPreset();
		void showNewPresetCreationDialog();
		void closeNewPresetCreationDialog();
		void showPresetAlreadyExists();
		void setDeletePresetButtonEnabled(boolean enabled);
		void initBassBoost(int max, int strength);
		void initSurround(int max, int strength);
		void initAmplifier(int max, int gain);
		void setCurrentEqualizerPreset(int presetIndex, Equalizer.PresetType currentPresetType);
	}

	interface Presenter extends ModelServiceModule.OnServiceConnectedListener {
		void onCreate();
		void onDestroy();
		void onEqualizerEnabledChanged(boolean enabled);
		void onEqualizerBandChanged(EqualizerBandView band);
		void onEqualizerBandStopTracking();
		void onPresetSelected(int position, Equalizer.PresetType presetType);
		void onNewPresetDialogOkButtonClicked(String name);
		void onNewPresetDialogCancelButtonClicked();
		void onDeletePreset(int position);
		void onCreateNewPresetButtonClicked();
		void onBassBoostStrengthChanged(int strength);
		void onBassBoostStrengthStopChanging();
		void onSurroundStrengthChanged(int progress);
		void onSurroundStrengthStopChanging();
		void onAmplifierStrengthChanged(int gain);
		void onAmplifierStrengthStopChanging();
	}
}