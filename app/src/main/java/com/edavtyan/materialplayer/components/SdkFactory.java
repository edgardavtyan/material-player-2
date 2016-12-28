package com.edavtyan.materialplayer.components;

import android.content.Context;
import android.view.MenuInflater;
import android.view.View;
import android.widget.PopupMenu;

public class SdkFactory {
	public PopupMenu createPopupMenu(Context context, View anchor) {
		return new PopupMenu(context, anchor);
	}

	public MenuInflater createMenuInflater(Context context) {
		return new MenuInflater(context);
	}
}