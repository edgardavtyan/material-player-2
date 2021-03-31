package com.edavtyan.materialplayer2.player.effects.bassboost;

public interface BassBoost {
	boolean isSupported();
	int getMaxStrength();
	int getStrength();
	void setStrength(int strength);
	void saveSettings();
}
