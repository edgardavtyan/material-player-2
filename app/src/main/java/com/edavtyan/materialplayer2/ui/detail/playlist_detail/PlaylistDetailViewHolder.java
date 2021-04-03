package com.edavtyan.materialplayer2.ui.detail.playlist_detail;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.databinding.ListitemSimpleBinding;

public class PlaylistDetailViewHolder
		extends RecyclerView.ViewHolder
		implements ItemTouchHelperViewHolder, View.OnClickListener {

	private final PlaylistDetailPresenter presenter;
	private final ListitemSimpleBinding binding;

	public PlaylistDetailViewHolder(View itemView, PlaylistDetailPresenter presenter) {
		super(itemView);
		this.presenter = presenter;
		binding = ListitemSimpleBinding.bind(itemView);
		itemView.setOnClickListener(this);

		PopupMenu menu = new PopupMenu(itemView.getContext(), binding.menu);
		menu.inflate(R.menu.menu_playlist);
		menu.setOnMenuItemClickListener(this::onMenuItemClick);
		binding.menu.setOnClickListener(v -> menu.show());

		binding.handle.setOnTouchListener((view, event) -> {
			if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
				presenter.onHandleDrag(this);
			}
			return false;
		});
	}

	public void setText(String text) {
		binding.title.setText(text);
	}

	protected boolean onMenuItemClick(MenuItem menuItem) {
		switch (menuItem.getItemId()) {
		case R.id.menu_remove:
			presenter.onRemoveFromPlaylistMenuClick(getAdapterPosition());
			return true;
		}
		return false;
	}

	@Override
	public void onItemSelected() {
		binding.background.animate().alpha(1);
	}

	@Override
	public void onItemClear() {
		binding.background.animate().alpha(0);
	}

	@Override
	public void onClick(View v) {
		presenter.onItemClick(getAdapterPosition());
	}
}
