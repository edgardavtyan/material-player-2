package com.edavtyan.materialplayer2.lib.album_art;

import android.content.Context;
import android.media.MediaMetadataRetriever;

import com.edavtyan.materialplayer2.lib.testable.TestableBitmapFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AlbumArtDIModule {
	@Provides
	@Singleton
	public AlbumArtProvider provideAlbumArtProvider(Context context) {
		return new AlbumArtProvider(
				new AlbumArtFileStorage(context),
				new AlbumArtMemoryCache(),
				new AlbumArtReader(new MediaMetadataRetriever()),
				new TestableBitmapFactory());
	}
}
