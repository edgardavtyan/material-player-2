package com.edavtyan.materialplayer2.lib.theme;

import android.app.Activity;

import com.edavtyan.materialplayer2.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer2.ui.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ThemeableFragmentDIModule {
	@Provides
	@FragmentScope
	public ScreenThemeModule provideScreenThemeModule(
			Activity activity, AdvancedSharedPrefs prefs) {
		return new ScreenThemeModule(activity, prefs);
	}

	@Provides
	@FragmentScope
	public ThemeColors provideThemeColors(Activity activity) {
		return new ThemeColors(activity);
	}
}
