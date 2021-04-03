package com.edavtyan.materialplayer2.ui.lists.lib.simple;

import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.edavtyan.materialplayer2.databinding.ListitemSimpleBinding;
import com.edavtyan.materialplayer2.lib.testable.TestableViewHolder;

public abstract class SimpleTextListViewHolder extends TestableViewHolder {
	private final ListitemSimpleBinding binding;

	protected abstract int getMenuRes();

	public SimpleTextListViewHolder(View itemView) {
		super(itemView);
		binding = ListitemSimpleBinding.bind(itemView);
		itemView.setOnClickListener(v -> onItemClick());

		PopupMenu menu = new PopupMenu(itemView.getContext(), binding.menu);
		menu.inflate(getMenuRes());
		menu.setOnMenuItemClickListener(this::onMenuItemClick);
		binding.menu.setOnClickListener(v -> menu.show());
	}

	public void setText(String text) {
		binding.title.setText(text);
	}

	protected void onItemClick() {

	}

	protected boolean onMenuItemClick(MenuItem menuItem) {
		return false;
	}
}
