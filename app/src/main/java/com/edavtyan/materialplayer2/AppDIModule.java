package com.edavtyan.materialplayer2;

import android.content.Context;

import com.edavtyan.materialplayer2.ui.SdkFactory;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppDIModule {
	private final App app;

	public AppDIModule(App app) {
		this.app = app;
	}

	@Provides
	@Singleton
	public Context provideContext() {
		return app;
	}

	@Provides
	@Singleton
	public Gson provideGson() {
		return new Gson();
	}

	@Provides
	public SdkFactory provideSdkFactory() {
		return new SdkFactory();
	}
}
