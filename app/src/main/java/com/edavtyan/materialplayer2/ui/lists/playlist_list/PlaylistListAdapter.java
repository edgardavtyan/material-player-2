package com.edavtyan.materialplayer2.ui.lists.playlist_list;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.ui.lists.lib.ListAdapter;

public class PlaylistListAdapter extends ListAdapter<PlaylistListViewHolder> {
	private final PlaylistListPresenter presenter;

	public PlaylistListAdapter(Context context, PlaylistListPresenter presenter) {
		super(context, presenter);
		this.presenter = presenter;
	}

	@Override
	public int getLayoutId() {
		return R.layout.listitem_simple;
	}

	@Override
	public PlaylistListViewHolder onCreateViewHolder(Context context, View view) {
		return new PlaylistListViewHolder(view, presenter);
	}
}
