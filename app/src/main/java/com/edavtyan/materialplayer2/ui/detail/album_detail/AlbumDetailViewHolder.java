package com.edavtyan.materialplayer2.ui.detail.album_detail;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ed.libsutils.utils.DurationUtils;
import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.modular.viewholder.ContextMenuModule;
import com.edavtyan.materialplayer2.ui.lists.track_list.TrackListPresenter;
import com.edavtyan.materialplayer2.ui.lists.track_list.TrackListViewHolder;

import butterknife.BindView;

public class AlbumDetailViewHolder extends TrackListViewHolder {
	@BindView(R.id.info) TextView infoView;

	public AlbumDetailViewHolder(
			Context context,
			View itemView,
			TrackListPresenter presenter,
			ContextMenuModule contextMenu) {
		super(context, itemView, presenter, contextMenu);
	}

	@Override
	public void setInfo(long duration, String artist, String album) {
		infoView.setText(DurationUtils.toStringUntilHours(duration));
	}
}
