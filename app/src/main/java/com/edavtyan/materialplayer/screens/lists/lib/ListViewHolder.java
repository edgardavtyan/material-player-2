package com.edavtyan.materialplayer.screens.lists.lib;

import android.content.Context;
import android.support.annotation.MenuRes;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.screens.SdkFactory;
import com.edavtyan.materialplayer.lib.testable.TestableViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class ListViewHolder
		extends TestableViewHolder
		implements View.OnClickListener,
				   PopupMenu.OnMenuItemClickListener {

	@BindView(R.id.menu) ImageButton menuButton;

	public ListViewHolder(Context context, View itemView) {
		super(itemView);

		ButterKnife.bind(this, itemView);

		itemView.setOnClickListener(this);

		SdkFactory sdkFactory = ((App) context.getApplicationContext()).getSdkFactory();

		PopupMenu popupMenu = sdkFactory.createPopupMenu(context, menuButton);
		popupMenu.inflate(getMenuResource());
		popupMenu.setOnMenuItemClickListener(this);

		menuButton.setOnClickListener(v -> popupMenu.show());
	}

	@MenuRes
	public int getMenuResource() {
		return R.menu.menu_track;
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		return false;
	}

	@Override
	public void onClick(View v) {

	}
}