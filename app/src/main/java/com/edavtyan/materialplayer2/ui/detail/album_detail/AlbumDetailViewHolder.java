package com.edavtyan.materialplayer2.ui.detail.album_detail;

import android.content.Context;
import android.view.View;

import com.ed.libsutils.utils.DurationUtils;
import com.edavtyan.materialplayer2.modular.viewholder.ContextMenuModule;
import com.edavtyan.materialplayer2.ui.lists.track_list.TrackListPresenter;
import com.edavtyan.materialplayer2.ui.lists.track_list.TrackListViewHolder;


public class AlbumDetailViewHolder extends TrackListViewHolder {
	public AlbumDetailViewHolder(
			Context context,
			View itemView,
			TrackListPresenter presenter,
			ContextMenuModule contextMenu) {
		super(context, itemView, presenter, contextMenu);
	}

	@Override
	public void setInfo(long duration, String artist, String album) {
		binding.info.setText(DurationUtils.toStringUntilHours(duration));
	}
}
