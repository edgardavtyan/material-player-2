package com.edavtyan.materialplayer2.utils;

import android.content.Context;

import com.edavtyan.materialplayer2.ui.Navigator;
import com.edavtyan.materialplayer2.lib.testable.TestableBitmapFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilsDIModule {
	@Provides
	@Singleton
	public Navigator provideNavigator(Context context) {
		return new Navigator(context);
	}

	@Provides
	@Singleton
	public WebClient provideWebClient() {
		return new WebClient();
	}

	@Provides
	@Singleton
	public TestableBitmapFactory provideBitmapFactory() {
		return new TestableBitmapFactory();
	}

	@Provides
	@Singleton
	public PendingIntents providePendingIntents(Context context) {
		return new PendingIntents(context);
	}
}
