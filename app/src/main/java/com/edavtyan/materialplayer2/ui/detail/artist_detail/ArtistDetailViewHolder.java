package com.edavtyan.materialplayer2.ui.detail.artist_detail;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.modular.viewholder.ContextMenuModule;
import com.edavtyan.materialplayer2.ui.lists.album_list.AlbumListViewHolder;

import butterknife.BindView;

public class ArtistDetailViewHolder extends AlbumListViewHolder {
	@BindView(R.id.info) TextView infoView;

	private final Context context;

	public ArtistDetailViewHolder(
			Context context,
			View itemView,
			ArtistDetailPresenter presenter,
			ContextMenuModule contextMenu) {
		super(context, itemView, presenter, contextMenu);
		this.context = context;
	}

	@Override
	public void setInfo(int tracksCount, String artist) {
		Resources res = context.getResources();
		String info = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		infoView.setText(info);
	}
}
