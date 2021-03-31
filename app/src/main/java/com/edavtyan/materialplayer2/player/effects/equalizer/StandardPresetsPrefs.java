package com.edavtyan.materialplayer2.player.effects.equalizer;

import com.edavtyan.materialplayer2.lib.prefs.AdvancedGsonSharedPrefs;

public class StandardPresetsPrefs extends PresetsPrefs {
	public StandardPresetsPrefs(AdvancedGsonSharedPrefs prefs) {
		super(prefs, "pref_std_index", "pref_std_custom", "pref_std_type");
	}
}
