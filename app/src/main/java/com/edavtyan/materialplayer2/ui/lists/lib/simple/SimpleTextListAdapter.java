package com.edavtyan.materialplayer2.ui.lists.lib.simple;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.ui.lists.lib.ListAdapter;
import com.edavtyan.materialplayer2.ui.lists.lib.ListPresenter;

public abstract class SimpleTextListAdapter<VH extends RecyclerView.ViewHolder>
		extends ListAdapter<VH> {

	public SimpleTextListAdapter(Context context, ListPresenter<VH> presenter) {
		super(context, presenter);
	}

	@Override
	public int getLayoutId() {
		return R.layout.listitem_simple;
	}
}
