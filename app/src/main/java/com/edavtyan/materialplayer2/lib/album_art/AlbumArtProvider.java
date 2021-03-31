package com.edavtyan.materialplayer2.lib.album_art;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer2.db.types.Track;
import com.edavtyan.materialplayer2.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer2.utils.StringUtils;

public class AlbumArtProvider {
	private final AlbumArtFileStorage dataStorage;
	private final AlbumArtMemoryCache memoryCache;
	private final AlbumArtReader artReader;
	private final TestableBitmapFactory bitmapFactory;

	public AlbumArtProvider(
			AlbumArtFileStorage dataStorage,
			AlbumArtMemoryCache memoryCache,
			AlbumArtReader artReader,
			TestableBitmapFactory bitmapFactory) {
		this.dataStorage = dataStorage;
		this.memoryCache = memoryCache;
		this.artReader = artReader;
		this.bitmapFactory = bitmapFactory;
	}

	@Nullable
	public Bitmap load(Track track) {
		String albumTitle = StringUtils.encodeFilename(track.getAlbumTitle());

		if (memoryCache.contains(albumTitle)) {
			return memoryCache.get(albumTitle);
		}

		if (dataStorage.exists(albumTitle)) {
			Bitmap image = dataStorage.load(albumTitle);
			memoryCache.put(albumTitle, image);
			return image;
		}

		byte[] imageBytes = artReader.getAlbumArtBytes(track.getPath());

		if (imageBytes == null) {
			return null;
		}

		Bitmap image = bitmapFactory.fromByteArray(imageBytes);
		dataStorage.saveBytes(albumTitle, imageBytes);
		memoryCache.put(albumTitle, image);
		return image;
	}

	public String getFilename(String albumTitle) {
		return dataStorage.getFullPath(StringUtils.encodeFilename(albumTitle));
	}

	public boolean isCached(String albumTitle) {
		if (albumTitle == null) {
			return false;
		}

		return dataStorage.exists(StringUtils.encodeFilename(albumTitle));
	}
}
