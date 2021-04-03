package com.edavtyan.materialplayer2.ui.detail.artist_detail;

import com.edavtyan.materialplayer2.db.MediaDB;
import com.edavtyan.materialplayer2.db.db.AlbumDB;
import com.edavtyan.materialplayer2.db.types.Album;
import com.edavtyan.materialplayer2.db.types.Artist;
import com.edavtyan.materialplayer2.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer2.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer2.ui.lists.album_list.AlbumListModel;

import java.util.List;

public class ArtistDetailModel extends AlbumListModel {
	private final MediaDB mediaDB;
	private final ArtistDetailPrefs prefs;
	private final String artistTitle;

	public ArtistDetailModel(
			ModelServiceModule serviceModule,
			MediaDB mediaDB,
			AlbumArtProvider albumArtProvider,
			ArtistDetailPrefs prefs,
			String artistTitle) {
		super(serviceModule, mediaDB, albumArtProvider);
		this.mediaDB = mediaDB;
		this.prefs = prefs;
		this.artistTitle = artistTitle;
	}

	@Override
	protected List<Album> queryAlbums() {
		return mediaDB.getAlbumsWithArtistTitle(artistTitle, prefs.getSort());
	}

	public Artist getArtist() {
		return mediaDB.getArtistWithTitle(artistTitle);
	}

	public void sortByName() {
		albums = mediaDB.getAlbumsWithArtistTitle(artistTitle, AlbumDB.KEY_TITLE);
		prefs.saveSort(AlbumDB.KEY_TITLE);
	}

	public void sortByDate() {
		albums = mediaDB.getAlbumsWithArtistTitle(artistTitle, AlbumDB.KEY_DATE);
		prefs.saveSort(AlbumDB.KEY_DATE);
	}
}
