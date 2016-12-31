package com.edavtyan.materialplayer.lib.mvp.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;

public abstract class ListAdapter<VH extends RecyclerView.ViewHolder>
		extends TestableRecyclerAdapter<VH> {

	private final Context context;
	private final ListMvp.Presenter<VH> presenter;

	public ListAdapter(Context context, ListMvp.Presenter<VH> presenter) {
		this.context = context;
		this.presenter = presenter;
	}

	public abstract int getNormalLayoutId();
	public abstract int getCompactLayoutId();
	public abstract VH onCreateViewHolder(Context context, View view);

	@Override
	public VH onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(viewType, parent, false);
		return onCreateViewHolder(context, view);
	}

	@Override
	public void onBindViewHolder(VH holder, int position) {
		presenter.onBindViewHolder(holder, position);
	}

	@Override
	public int getItemCount() {
		return presenter.getItemCount();
	}

	@Override
	public int getItemViewType(int position) {
		return presenter.isCompactModeEnabled() ? getCompactLayoutId() : getNormalLayoutId();
	}
}
