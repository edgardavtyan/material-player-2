package com.edavtyan.materialplayer.lib.theme;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.modular.universal_view.UniversalViewModule;

public class ThemeSwitchModule
		extends UniversalViewModule
		implements SharedPreferences.OnSharedPreferenceChangeListener {

	private final AdvancedSharedPrefs prefs;
	private final ThemeModularScreen modularScreen;
	private final Context context;
	private final String themePrefKey;
	private final int defaultColor;

	public ThemeSwitchModule(ThemeModularScreen modularScreen, Context context, AdvancedSharedPrefs prefs) {
		this.modularScreen = modularScreen;
		this.context = context;
		this.prefs = prefs;
		themePrefKey = context.getString(R.string.pref_colors_key);
		defaultColor = ContextCompat.getColor(context, R.color.pref_colors_default);
		prefs.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key.equals(themePrefKey)) {
			callOnThemeChanged();
		}
	}

	@Override
	public void onStart() {
		callOnThemeChanged();
	}

	private void callOnThemeChanged() {
		ThemeColors colors = new ThemeColors(context, prefs.getInt(themePrefKey, defaultColor));
		modularScreen.onThemeChanged(colors);
	}
}
