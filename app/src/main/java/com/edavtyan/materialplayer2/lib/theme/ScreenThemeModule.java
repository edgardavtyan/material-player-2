package com.edavtyan.materialplayer2.lib.theme;

import android.app.Activity;
import android.content.SharedPreferences;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer2.modular.universal_view.UniversalViewModule;

public class ScreenThemeModule
		extends UniversalViewModule
		implements SharedPreferences.OnSharedPreferenceChangeListener {

	private final Activity activity;
	private final String colorsPrefKey;
	private final String themePrefKey;

	public ScreenThemeModule(Activity activity, AdvancedSharedPrefs prefs) {
		this.activity = activity;
		colorsPrefKey = this.activity.getString(R.string.pref_colors_key);
		themePrefKey = this.activity.getString(R.string.pref_colorsMain_key);
		prefs.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key.equals(colorsPrefKey) || key.equals(themePrefKey)) {
			activity.recreate();
		}
	}
}
