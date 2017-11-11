package com.edavtyan.materialplayer.components.lists.artist_list;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.components.lists.lib.ListPresenter;

public class ArtistListPresenter
		extends ListPresenter<ArtistListViewHolder>
		implements ArtistListMvp.Presenter {

	private final ArtistListMvp.Model model;
	private final ArtistListMvp.View view;

	public ArtistListPresenter(ArtistListMvp.Model model, ArtistListMvp.View view) {
		super(model, view);
		this.model = model;
		this.view = view;
	}

	@Override
	public void onBindViewHolder(ArtistListViewHolder holder, int position) {
		Artist artist = model.getArtistAtIndex(position);
		holder.setTitle(artist.getTitle());
		holder.setInfo(artist.getAlbumsCount(), artist.getTracksCount());
		model.getArtistImage(position, holder::setImage);
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

	@Override
	public void onHolderClick(int position) {
		Artist artist = model.getArtistAtIndex(position);
		view.gotoArtistDetail(artist.getTitle());
	}

	@Override
	public void onAddToPlaylist(int position) {
		int artistId = model.getArtistAtIndex(position).getId();
		model.addToPlaylist(artistId);
	}
}