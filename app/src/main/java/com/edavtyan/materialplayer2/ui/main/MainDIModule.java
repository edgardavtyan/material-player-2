package com.edavtyan.materialplayer2.ui.main;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer2.ui.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainDIModule {
	private final MainActivity activity;

	public MainDIModule(MainActivity activity) {
		this.activity = activity;
	}

	@Provides
	@ActivityScope
	public AppCompatActivity provideActivity() {
		return activity;
	}

	@Provides
	@ActivityScope
	public TabsAdapter provideTabsAdapter(
			FragmentManager fragmentManager) {
		return new TabsAdapter(fragmentManager);
	}

	@Provides
	@ActivityScope
	public FragmentManager provideFragmentManager(AppCompatActivity activity) {
		return activity.getSupportFragmentManager();
	}

	@Provides
	@ActivityScope
	public TabsPartial provideTabsPartial() {
		return new TabsPartial(activity);
	}
}
