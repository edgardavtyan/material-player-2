package com.edavtyan.materialplayer2.ui.main;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer2.databinding.ActivityMainBinding;
import com.edavtyan.materialplayer2.ui.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainDIModule {
	private final MainActivity activity;
	private final ActivityMainBinding binding;

	public MainDIModule(MainActivity activity, ActivityMainBinding binding) {
		this.activity = activity;
		this.binding = binding;
	}

	@Provides
	@ActivityScope
	public AppCompatActivity provideActivity() {
		return activity;
	}

	@Provides
	@ActivityScope
	public ActivityMainBinding provideBinding() {
		return binding;
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
		return new TabsPartial(binding);
	}
}
