package com.edavtyan.materialplayer2.player.effects.equalizer;

import com.edavtyan.materialplayer2.lib.prefs.AdvancedSharedPrefs;

public class StandardEqualizerPrefs {
	private static final String PREF_GAINS = "equalizer_standard_gains";

	private final AdvancedSharedPrefs prefs;

	public StandardEqualizerPrefs(AdvancedSharedPrefs prefs) {
		this.prefs = prefs;
	}

	public int[] getGains(int bandsCount) {
		return prefs.getIntArray(PREF_GAINS, bandsCount);
	}

	public void saveGains(int[] gains) {
		prefs.edit().putIntArray(PREF_GAINS, gains).apply();
	}
}
