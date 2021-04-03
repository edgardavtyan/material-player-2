package com.edavtyan.materialplayer2.ui.lists.track_list;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;

import com.ed.libsutils.utils.DurationUtils;
import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.databinding.ListitemTrackBinding;
import com.edavtyan.materialplayer2.lib.testable.TestableViewHolder;
import com.edavtyan.materialplayer2.modular.viewholder.ContextMenuModule;

public class TrackListViewHolder
		extends TestableViewHolder
		implements View.OnClickListener,
				   PopupMenu.OnMenuItemClickListener {
	private final Context context;
	private final TrackListPresenter presenter;

	protected final ListitemTrackBinding binding;

	public TrackListViewHolder(
			Context context,
			View itemView,
			TrackListPresenter presenter,
			ContextMenuModule contextMenu) {
		super(itemView);
		this.context = context;
		this.presenter = presenter;
		this.binding = ListitemTrackBinding.bind(itemView);
		itemView.setOnClickListener(this);

		contextMenu.init(itemView, R.id.menu, R.menu.menu_track);
		contextMenu.setOnMenuItemClickListener(this);
	}

	public void setTitle(String title) {
		binding.title.setText(title);
	}

	public void setInfo(long duration, String artist, String album) {
		String durationStr = DurationUtils.toStringUntilHours(duration);
		String info = context.getString(R.string.pattern_track_info, durationStr, artist, album);
		binding.info.setText(info);
	}

	@Override
	public void onClick(View v) {
		presenter.onHolderClick(getAdapterPositionNonFinal());
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_add_to_queue:
			presenter.onAddToQueue(getAdapterPositionNonFinal());
			return true;
		case R.id.menu_add_to_playlist:
			presenter.onAddToPlaylistClick(getAdapterPositionNonFinal());
			return true;
		default:
			return false;
		}
	}
}
