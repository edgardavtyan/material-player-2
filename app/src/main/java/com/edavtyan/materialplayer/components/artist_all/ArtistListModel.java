package com.edavtyan.materialplayer.components.artist_all;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.db.ArtistDB;

import java.util.List;

public class ArtistListModel implements ArtistListMvp.Model {
	private final ArtistDB db;
	private List<Artist> artists;

	public ArtistListModel(ArtistDB db) {
		this.db = db;
	}

	@Override
	public void update() {
		artists = db.getAllArtists();
	}

	@Override
	public int getArtistCount() {
		return artists.size();
	}

	@Override
	public Artist getArtistAtIndex(int position) {
		return artists.get(position);
	}
}