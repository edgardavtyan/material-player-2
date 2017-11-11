package com.edavtyan.materialplayer.components.lists.album_list;

import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.components.lists.lib.ListPresenter;
import com.edavtyan.materialplayer.utils.Logger;

public class AlbumListPresenter
		extends ListPresenter<AlbumListViewHolder>
		implements AlbumListMvp.Presenter {

	private final AlbumListMvp.Model model;
	private final AlbumListMvp.View view;

	public AlbumListPresenter(AlbumListMvp.Model model, AlbumListMvp.View view) {
		super(model, view);
		this.model = model;
		this.view = view;
	}

	@Override
	public void onBindViewHolder(AlbumListViewHolder holder, int position) {
		Album album = model.getAlbumAtIndex(position);
		holder.setTitle(album.getTitle());
		holder.setInfo(album.getTracksCount(), album.getArtistTitle());
		holder.setArt(album.getArt());
	}

	@Override
	public int getItemCount() {
		Logger.d(this, "Albums count: %d", model.getAlbumsCount());
		return model.getAlbumsCount();
	}

	@Override
	public void onHolderClick(int position) {
		int albumId = model.getAlbumAtIndex(position).getId();
		view.gotoAlbumDetail(albumId);
	}

	@Override
	public void onAddToPlaylist(int position) {
		int albumId = model.getAlbumAtIndex(position).getId();
		model.addToPlaylist(albumId);
	}

	@Override
	public void onCreate() {
		model.update();
		model.bindService();
	}

	@Override
	public void onDestroy() {
		model.unbindService();
	}
}