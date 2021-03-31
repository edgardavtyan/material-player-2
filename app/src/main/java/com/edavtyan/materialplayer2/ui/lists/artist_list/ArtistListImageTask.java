package com.edavtyan.materialplayer2.ui.lists.artist_list;

import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer2.db.types.Track;
import com.edavtyan.materialplayer2.lib.album_art.AlbumArtProvider;

public class ArtistListImageTask extends AsyncTask<Track, Void, String> {
	private final AlbumArtProvider albumArtProvider;
	private final Callback callback;

	interface Callback {
		void onArtLoaded(@Nullable String artFilename);
	}

	public ArtistListImageTask(AlbumArtProvider albumArtProvider, Callback callback) {
		this.albumArtProvider = albumArtProvider;
		this.callback = callback;
	}

	@Override
	@Nullable
	protected String doInBackground(Track... track) {
		try {
			String albumTitle = track[0].getAlbumTitle();
			if (!albumArtProvider.isCached(albumTitle)) {
				albumArtProvider.load(track[0]);
			}

			return albumArtProvider.getFilename(albumTitle);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	protected void onPostExecute(@Nullable String artFilename) {
		callback.onArtLoaded(artFilename);
	}
}
