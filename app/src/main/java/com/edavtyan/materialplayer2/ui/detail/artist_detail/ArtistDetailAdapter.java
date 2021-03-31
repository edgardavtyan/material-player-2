package com.edavtyan.materialplayer2.ui.detail.artist_detail;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer2.modular.viewholder.ContextMenuModule;
import com.edavtyan.materialplayer2.ui.SdkFactory;
import com.edavtyan.materialplayer2.ui.lists.album_list.AlbumListAdapter;
import com.edavtyan.materialplayer2.ui.lists.album_list.AlbumListViewHolder;

public class ArtistDetailAdapter extends AlbumListAdapter {
	private final ArtistDetailPresenter presenter;
	private final SdkFactory sdkFactory;

	public ArtistDetailAdapter(Context context, ArtistDetailPresenter presenter, SdkFactory sdkFactory) {
		super(context, presenter, sdkFactory);
		this.presenter = presenter;
		this.sdkFactory = sdkFactory;
	}

	@Override
	public AlbumListViewHolder onCreateViewHolder(Context context, View view) {
		ContextMenuModule contextMenu = new ContextMenuModule(context, sdkFactory);
		return new ArtistDetailViewHolder(context, view, presenter, contextMenu);
	}
}
