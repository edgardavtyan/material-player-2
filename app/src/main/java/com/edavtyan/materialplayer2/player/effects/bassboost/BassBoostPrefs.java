package com.edavtyan.materialplayer2.player.effects.bassboost;

import com.edavtyan.materialplayer2.lib.prefs.AdvancedSharedPrefs;

public class BassBoostPrefs {
	private static final String PREF_STRENGTH = "bassBoost_strength";

	private final AdvancedSharedPrefs prefs;

	public BassBoostPrefs(AdvancedSharedPrefs prefs) {
		this.prefs = prefs;
	}

	public int getStrength() {
		return prefs.getInt(PREF_STRENGTH, 0);
	}

	public void save(int strength) {
		prefs.edit().putInt(PREF_STRENGTH, strength).apply();
	}
}
