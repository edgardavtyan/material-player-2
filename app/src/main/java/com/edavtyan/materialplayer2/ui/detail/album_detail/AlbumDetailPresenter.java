package com.edavtyan.materialplayer2.ui.detail.album_detail;

import com.edavtyan.materialplayer2.db.types.Album;
import com.edavtyan.materialplayer2.player.Player;
import com.edavtyan.materialplayer2.ui.detail.lib.ParallaxHeaderListPresenter;
import com.edavtyan.materialplayer2.ui.lists.track_list.TrackListPresenter;

public class AlbumDetailPresenter
		extends TrackListPresenter
		implements ParallaxHeaderListPresenter, Player.OnNewTrackListener {

	private final AlbumDetailActivity activity;
	private final AlbumDetailModel model;

	public AlbumDetailPresenter(AlbumDetailModel model, AlbumDetailActivity activity) {
		super(activity, model);
		this.activity = activity;
		this.model = model;
		this.model.addOnNewTrackListener(this);
	}

	@Override
	public void onCreate() {
		super.onCreate();

		Album album = model.getAlbum();
		activity.setAlbumTitle(album.getTitle());
		activity.setAlbumInfo(
				album.getArtistTitle(),
				album.getTracksCount(),
				model.getTotalAlbumDuration());
		activity.setAlbumImage(model.getAlbumArt());
	}

	@Override
	public void onHolderClick(int position) {
		model.playQueue(position);
	}

	@Override
	public void onNewTrack() {
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		model.removeOnNewTrackListener(this);
	}
}
