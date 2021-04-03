package com.edavtyan.materialplayer2.ui.detail.artist_detail;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.databinding.ListitemAlbumBinding;
import com.edavtyan.materialplayer2.modular.viewholder.ContextMenuModule;
import com.edavtyan.materialplayer2.ui.lists.album_list.AlbumListViewHolder;

public class ArtistDetailViewHolder extends AlbumListViewHolder {
	private final Context context;
	private final ListitemAlbumBinding binding;

	public ArtistDetailViewHolder(
			Context context,
			View itemView,
			ArtistDetailPresenter presenter,
			ContextMenuModule contextMenu) {
		super(context, itemView, presenter, contextMenu);
		this.context = context;
		this.binding = ListitemAlbumBinding.bind(itemView);
	}

	@Override
	public void setInfo(int tracksCount, String artist) {
		Resources res = context.getResources();
		String info = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		binding.info.setText(info);
	}
}
