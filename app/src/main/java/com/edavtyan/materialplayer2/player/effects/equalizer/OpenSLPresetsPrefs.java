package com.edavtyan.materialplayer2.player.effects.equalizer;

import com.edavtyan.materialplayer2.lib.prefs.AdvancedGsonSharedPrefs;

public class OpenSLPresetsPrefs extends PresetsPrefs {
	public OpenSLPresetsPrefs(AdvancedGsonSharedPrefs prefs) {
		super(prefs, "pref_opensl_index", "pref_opensl_custom", "pref_opensl_type");
	}
}
