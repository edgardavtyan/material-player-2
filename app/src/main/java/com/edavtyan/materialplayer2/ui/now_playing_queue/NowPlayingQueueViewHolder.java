package com.edavtyan.materialplayer2.ui.now_playing_queue;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;

import com.ed.libsutils.utils.DurationUtils;
import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.databinding.ListitemTrackBinding;
import com.edavtyan.materialplayer2.lib.testable.TestableViewHolder;
import com.edavtyan.materialplayer2.modular.viewholder.ContextMenuModule;
import com.edavtyan.materialplayer2.ui.SdkFactory;

public class NowPlayingQueueViewHolder
		extends TestableViewHolder
		implements View.OnClickListener,
				   PopupMenu.OnMenuItemClickListener {
	private final Context context;
	private final NowPlayingQueuePresenter presenter;
	private final ListitemTrackBinding binding;

	public NowPlayingQueueViewHolder(
			Context context,
			View itemView,
			NowPlayingQueuePresenter presenter,
			SdkFactory sdkFactory) {
		super(itemView);
		this.context = context;
		this.presenter = presenter;
		itemView.setOnClickListener(this);

		binding = ListitemTrackBinding.bind(itemView);

		ContextMenuModule contextMenu = new ContextMenuModule(context, sdkFactory);
		contextMenu.init(itemView, R.id.menu, R.menu.menu_queue);
		contextMenu.setOnMenuItemClickListener(this);
	}

	public void setTitle(String title) {
		binding.title.setText(title);
	}

	public void setInfo(long durationMillis, String artistTitle, String albumTitle) {
		String timeStr = DurationUtils.toStringUntilHours(durationMillis);
		String info = context.getString(R.string.pattern_track_info, timeStr, artistTitle, albumTitle);
		binding.info.setText(info);
	}

	public void setIsPlaying(boolean isPlaying) {
		binding.nowPlaying.setVisibility(isPlaying ? View.VISIBLE : View.GONE);
	}

	@Override
	public void onClick(View v) {
		presenter.onItemClick(getAdapterPositionNonFinal());
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_remove:
			presenter.onRemoveItemClick(getAdapterPositionNonFinal());
			return true;
		default:
			return false;
		}
	}
}
