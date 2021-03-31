package com.edavtyan.materialplayer2.ui.detail.album_detail;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer2.db.types.Album;
import com.edavtyan.materialplayer2.db.MediaDB;
import com.edavtyan.materialplayer2.db.types.Track;
import com.edavtyan.materialplayer2.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer2.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer2.player.Player;
import com.edavtyan.materialplayer2.service.PlayerService;
import com.edavtyan.materialplayer2.ui.lists.track_list.TrackListModel;

import java.util.List;

public class AlbumDetailModel extends TrackListModel {
	private final MediaDB mediaDB;
	private final AlbumArtProvider albumArtProvider;
	private final int albumId;

	private @Nullable Player.OnNewTrackListener onNewTrackListener;

	public AlbumDetailModel(
			ModelServiceModule serviceModule,
			MediaDB mediaDB,
			AlbumArtProvider albumArtProvider,
			int albumId) {
		super(serviceModule, mediaDB);
		this.mediaDB = mediaDB;
		this.albumArtProvider = albumArtProvider;
		this.albumId = albumId;
	}

	public Album getAlbum() {
		return mediaDB.getAlbumWithAlbumId(albumId);
	}

	@Nullable
	public Bitmap getAlbumArt() {
		return albumArtProvider.load(getTrackAtIndex(0));
	}

	public long getTotalAlbumDuration() {
		long totalDurationMS = 0;
		for (Track track : tracks) {
			totalDurationMS += track.getDuration();
		}

		return totalDurationMS;
	}

	@Override
	protected List<Track> queryTracks() {
		return mediaDB.getTracksWithAlbumId(albumId);
	}

	@Override
	public void onServiceConnected(PlayerService service) {
		super.onServiceConnected(service);

		if (onNewTrackListener != null) {
			service.getPlayer().setOnNewTrackListener(onNewTrackListener);
		}
	}

	public void addOnNewTrackListener(Player.OnNewTrackListener listener) {
		onNewTrackListener = listener;
	}

	public void removeOnNewTrackListener(Player.OnNewTrackListener listener) {
		getService().getPlayer().removeOnNewTrackListener(listener);
	}
}
