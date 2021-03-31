package com.edavtyan.materialplayer2.ui.detail.album_detail;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer2.modular.viewholder.ContextMenuModule;
import com.edavtyan.materialplayer2.ui.SdkFactory;
import com.edavtyan.materialplayer2.ui.lists.track_list.TrackListAdapter;
import com.edavtyan.materialplayer2.ui.lists.track_list.TrackListPresenter;
import com.edavtyan.materialplayer2.ui.lists.track_list.TrackListViewHolder;

public class AlbumDetailAdapter extends TrackListAdapter {
	private final TrackListPresenter presenter;
	private final SdkFactory sdkFactory;

	public AlbumDetailAdapter(Context context, AlbumDetailPresenter presenter, SdkFactory sdkFactory) {
		super(context, presenter, sdkFactory);
		this.presenter = presenter;
		this.sdkFactory = sdkFactory;
	}

	@Override
	public TrackListViewHolder onCreateViewHolder(Context context, View view) {
		ContextMenuModule contextMenu = new ContextMenuModule(context, sdkFactory);
		return new AlbumDetailViewHolder(context, view, presenter, contextMenu);
	}
}
