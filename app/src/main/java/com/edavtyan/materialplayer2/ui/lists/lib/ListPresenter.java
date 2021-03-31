package com.edavtyan.materialplayer2.ui.lists.lib;

public interface ListPresenter<VH> {
	void onBindViewHolder(VH holder, int position);
	int getItemCount();
	void onDestroy();
	void onCreate();
}
