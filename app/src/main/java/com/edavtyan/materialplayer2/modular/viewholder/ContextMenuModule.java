package com.edavtyan.materialplayer2.modular.viewholder;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.MenuRes;
import android.support.v7.widget.PopupMenu;
import android.view.View;

import com.edavtyan.materialplayer2.ui.SdkFactory;

public class ContextMenuModule {
	private final Context context;
	private final SdkFactory sdkFactory;

	private PopupMenu popupMenu;

	public ContextMenuModule(
			Context context, SdkFactory sdkFactory) {
		this.context = context;
		this.sdkFactory = sdkFactory;
	}

	public void init(View itemView, @IdRes int anchorId, @MenuRes int menuResId) {
		View anchorView = itemView.findViewById(anchorId);
		popupMenu = sdkFactory.createPopupMenu(context, anchorView);
		popupMenu.inflate(menuResId);
		anchorView.setOnClickListener(v -> popupMenu.show());
	}

	public void setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener listener) {
		popupMenu.setOnMenuItemClickListener(listener);
	}
}
