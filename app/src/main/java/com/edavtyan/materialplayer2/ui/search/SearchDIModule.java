package com.edavtyan.materialplayer2.ui.search;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer2.ui.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchDIModule {
	private final AppCompatActivity activity;

	public SearchDIModule(AppCompatActivity activity) {
		this.activity = activity;
	}

	@Provides
	@ActivityScope
	public AppCompatActivity provideAppCompatActivity() {
		return activity;
	}
}
