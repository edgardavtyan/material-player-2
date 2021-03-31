package com.edavtyan.materialplayer2.player.effects.surround;

public interface Surround {
	boolean isSupported();
	int getMaxStrength();
	int getStrength();
	void setStrength(int strength);
	void saveSettings();
}
