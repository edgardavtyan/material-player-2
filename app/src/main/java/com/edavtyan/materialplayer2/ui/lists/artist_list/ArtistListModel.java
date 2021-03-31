package com.edavtyan.materialplayer2.ui.lists.artist_list;

import com.edavtyan.materialplayer2.db.MediaDB;
import com.edavtyan.materialplayer2.db.types.Album;
import com.edavtyan.materialplayer2.db.types.Artist;
import com.edavtyan.materialplayer2.db.types.Track;
import com.edavtyan.materialplayer2.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer2.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer2.ui.lists.lib.ListModel;

import java.util.HashMap;
import java.util.List;

public class ArtistListModel extends ListModel {

	private final MediaDB mediaDB;
	private final AlbumArtProvider imageLoader;
	private final HashMap<Integer, Track> tracksForAlbumArt;
	private List<Artist> artists;

	public ArtistListModel(
			ModelServiceModule serviceModule,
			MediaDB mediaDB,
			AlbumArtProvider imageLoader) {
		super(serviceModule);
		this.mediaDB = mediaDB;
		this.imageLoader = imageLoader;
		this.tracksForAlbumArt = new HashMap<>();
	}

	public void update() {
		artists = queryArtists();

		for (int i = 0; i < artists.size(); i++) {
			String artistTitle = artists.get(i).getTitle();
			Album firstAlbum = mediaDB.getAlbumsWithArtistTitle(artistTitle).get(0);
			Track track = mediaDB.getTracksWithAlbumId(firstAlbum.getId()).get(0);
			tracksForAlbumArt.put(i, track);
		}
	}

	public void addToPlaylist(int artistId) {
		getService().getPlayer().addManyTracks(mediaDB.getTracksWithArtistId(artistId));
	}

	public int getArtistCount() {
		if (artists == null) return 0;
		return artists.size();
	}

	public Artist getArtistAtIndex(int position) {
		if (artists == null) {
			throw new IllegalStateException("Artists have not been initialized");
		}

		return artists.get(position);
	}

	public List<Track> getArtistTracks(int position) {
		return mediaDB.getTracksWithArtistId(getArtistAtIndex(position).getId());
	}

	public void getArtistImageLink(int position, ArtistListImageTask.Callback callback) {
		new ArtistListImageTask(imageLoader, callback).execute(tracksForAlbumArt.get(position));
	}

	protected List<Artist> queryArtists() {
		return mediaDB.getAllArtists();
	}
}
