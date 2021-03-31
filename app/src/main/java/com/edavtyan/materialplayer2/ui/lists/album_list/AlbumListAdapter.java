package com.edavtyan.materialplayer2.ui.lists.album_list;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.modular.viewholder.ContextMenuModule;
import com.edavtyan.materialplayer2.ui.SdkFactory;
import com.edavtyan.materialplayer2.ui.lists.lib.ListAdapter;

public class AlbumListAdapter extends ListAdapter<AlbumListViewHolder> {

	private final AlbumListPresenter presenter;
	private final SdkFactory sdkFactory;

	public AlbumListAdapter(Context context, AlbumListPresenter presenter, SdkFactory sdkFactory) {
		super(context, presenter);
		this.presenter = presenter;
		this.sdkFactory = sdkFactory;
	}

	@Override
	public int getLayoutId() {
		return R.layout.listitem_album;
	}

	@Override
	public AlbumListViewHolder onCreateViewHolder(Context context, View view) {
		ContextMenuModule contextMenu = new ContextMenuModule(context, sdkFactory);
		return new AlbumListViewHolder(context, view, presenter, contextMenu);
	}
}
