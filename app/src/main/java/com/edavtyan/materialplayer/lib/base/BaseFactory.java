package com.edavtyan.materialplayer.lib.base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.MenuInflater;

import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.utils.ThemeUtils;

public class BaseFactory {
	private final Context context;
	private Navigator navigator;
	private ThemeUtils themeUtils;
	private AdvancedSharedPrefs prefs;
	private SharedPreferences basePrefs;

	public BaseFactory(Context context) {
		this.context = context;
	}

	public Context getContext() {
		return context;
	}

	public Navigator getNavigator() {
		if (navigator == null) navigator = new Navigator(context);
		return navigator;
	}

	public ThemeUtils getThemeUtils() {
		if (themeUtils == null)
			themeUtils = new ThemeUtils(getPrefs());
		return themeUtils;
	}

	public AdvancedSharedPrefs getPrefs() {
		if (prefs == null)
			prefs = new AdvancedSharedPrefs(getBasePrefs());
		return prefs;
	}

	public SharedPreferences getBasePrefs() {
		if (basePrefs == null)
			basePrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
		return basePrefs;
	}

	public MenuInflater createMenuInflater(Activity activity) {
		return new MenuInflater(activity);
	}
}
