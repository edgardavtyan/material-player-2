package com.edavtyan.materialplayer2.lib.music_api;

import android.content.Context;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.utils.WebClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MusicApiDIModule {
	@Provides
	@Singleton
	public MusicApi provideApi(
			Context context,
			WebClient webClient,
			MusicApiFileStorage fileStorage) {
		return new AudioDBApi(
				webClient,
				fileStorage,
				context.getString(R.string.audiodb_key));
	}

	@Provides
	@Singleton
	public MusicApiFileStorage provideArtistInfoFileStorage(Context context) {
		return new MusicApiFileStorage(context);
	}
}
