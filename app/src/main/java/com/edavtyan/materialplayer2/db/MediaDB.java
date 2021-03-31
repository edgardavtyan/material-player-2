package com.edavtyan.materialplayer2.db;

import com.edavtyan.materialplayer2.db.db.AlbumDB;
import com.edavtyan.materialplayer2.db.db.ArtistDB;
import com.edavtyan.materialplayer2.db.db.TrackDB;

import lombok.experimental.Delegate;

public class MediaDB {
	@Delegate private final ArtistDB artistDB;
	@Delegate private final AlbumDB albumDB;
	@Delegate private final TrackDB trackDB;

	public MediaDB(ArtistDB artistDB, AlbumDB albumDB, TrackDB trackDB) {
		this.artistDB = artistDB;
		this.albumDB = albumDB;
		this.trackDB = trackDB;
	}
}
