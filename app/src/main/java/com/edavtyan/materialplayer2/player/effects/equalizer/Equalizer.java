package com.edavtyan.materialplayer2.player.effects.equalizer;

import com.edavtyan.materialplayer2.ui.audio_effects.presets.PresetNameAlreadyExists;

import java.util.List;

public interface Equalizer {
	enum PresetType {
		BUILT_IN, CUSTOM, CUSTOM_NEW
	}

	int getGainLimit();
	int getBandsCount();
	int[] getFrequencies();
	int[] getGains();
	void setBandGain(int band, int gain);
	void saveSettings();
	boolean isEnabled();
	void setEnabled(boolean isEnabled);
	List<String> getBuiltInPresetNames();
	boolean isSupported();
	List<String> getCustomPresetNames();
	PresetType getCurrentPresetType();
	int getCurrentPresetIndex();
	String getCurrentPresetName();
	boolean isUsingSavedCustomPreset();
	void useBuiltInPreset(int presetIndex);
	void useCustomPreset(int presetIndex);
	void savePreset(String name) throws PresetNameAlreadyExists;
	void saveAndOverwritePreset(String name);
	void deletePreset(int position);
}
