package com.edavtyan.materialplayer.lib.theme;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import dagger.Module;
import dagger.Provides;

@Module
public class ThemeableFragmentDIModule {
	@Provides
	@FragmentScope
	public ScreenThemeModule provideScreenThemeModule(
			Fragment fragment, Context context, AdvancedSharedPrefs prefs) {
		return new ScreenThemeModule((ThemeableScreen) fragment, context, prefs);
	}
}