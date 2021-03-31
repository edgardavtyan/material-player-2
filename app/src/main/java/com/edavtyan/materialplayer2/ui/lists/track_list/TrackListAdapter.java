package com.edavtyan.materialplayer2.ui.lists.track_list;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.modular.viewholder.ContextMenuModule;
import com.edavtyan.materialplayer2.ui.SdkFactory;
import com.edavtyan.materialplayer2.ui.lists.lib.ListAdapter;

public class TrackListAdapter extends ListAdapter<TrackListViewHolder> {

	private final TrackListPresenter presenter;
	private final SdkFactory sdkFactory;

	public TrackListAdapter(Context context, TrackListPresenter presenter, SdkFactory sdkFactory) {
		super(context, presenter);
		this.presenter = presenter;
		this.sdkFactory = sdkFactory;
	}

	@Override
	public int getLayoutId() {
		return R.layout.listitem_track;
	}

	@Override
	public TrackListViewHolder onCreateViewHolder(Context context, View view) {
		ContextMenuModule contextMenu = new ContextMenuModule(context, sdkFactory);
		return new TrackListViewHolder(context, view, presenter, contextMenu);
	}
}
