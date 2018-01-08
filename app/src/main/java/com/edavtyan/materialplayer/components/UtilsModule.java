package com.edavtyan.materialplayer.components;

import android.content.Context;

import com.edavtyan.materialplayer.components.detail.lib.CompactDetailPref;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.utils.PendingIntents;
import com.edavtyan.materialplayer.utils.WebClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {
	@Provides
	@Singleton
	public Navigator provideNavigator(Context context, CompactDetailPref compactDetailPref) {
		return new Navigator(context, compactDetailPref);
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
