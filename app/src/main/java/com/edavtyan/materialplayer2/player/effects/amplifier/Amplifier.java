package com.edavtyan.materialplayer2.player.effects.amplifier;

public interface Amplifier {
	int getGain();
	void setGain(double gain);
	int getMaxGain();
	void saveSettings();
}
