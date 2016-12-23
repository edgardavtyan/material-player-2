package com.edavtyan.materialplayer.components.audioeffects.models;

public interface Amplifier {
	int getMaxStrength();
	int getStrength();
	void setStrength(int strength);
	void saveSettings();
}
