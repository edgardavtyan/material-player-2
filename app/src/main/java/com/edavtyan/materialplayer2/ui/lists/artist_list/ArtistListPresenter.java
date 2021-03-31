package com.edavtyan.materialplayer2.ui.lists.artist_list;

import com.edavtyan.materialplayer2.db.types.Artist;
import com.edavtyan.materialplayer2.lib.transition.SourceSharedViews;
import com.edavtyan.materialplayer2.ui.lists.lib.ListPresenter;

public class ArtistListPresenter implements ListPresenter<ArtistListViewHolder> {

	private final ArtistListModel model;
	private final ArtistListView view;

	public ArtistListPresenter(ArtistListModel model, ArtistListView view) {
		super();
		this.model = model;
		this.view = view;
	}

	@Override
	public void onBindViewHolder(ArtistListViewHolder holder, int position) {
		Artist artist = model.getArtistAtIndex(position);
		holder.setTitle(artist.getTitle());
		holder.setInfo(artist.getAlbumsCount(), artist.getTracksCount());
		model.getArtistImageLink(position, holder::setImage);
	}

	@Override
	public int getItemCount() {
		return model.getArtistCount();
	}

	@Override
	public void onCreate() {
		model.bindService();
		model.update();
	}

	@Override
	public void onDestroy() {
		model.unbindService();
	}

	public void onHolderClick(int position) {
		view.gotoArtistDetail(model.getArtistAtIndex(position).getTitle());
	}

	public void onAddToQueue(int position) {
		int artistId = model.getArtistAtIndex(position).getId();
		model.addToPlaylist(artistId);
	}

	public void onEnterTransitionFinished() {

	}

	public void onAddToPlaylist(int position) {
		view.showPlaylistSelectionDialog(model.getArtistTracks(position));
	}
}
