package com.edavtyan.materialplayer.ui.lists.album_list;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.testable.TestableViewHolder;
import com.edavtyan.materialplayer.modular.viewholder.ContextMenuModule;
import com.edavtyan.materialplayer.transition.SourceSharedViews;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumListViewHolder
		extends TestableViewHolder
		implements PopupMenu.OnMenuItemClickListener,
				   View.OnClickListener {

	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.info) TextView infoView;
	@BindView(R.id.art) ImageView artView;

	private final Context context;
	private final AlbumListPresenter presenter;

	public AlbumListViewHolder(
			Context context,
			View itemView,
			AlbumListPresenter presenter,
			ContextMenuModule contextMenuModule) {
		super(itemView);
		this.context = context;
		this.presenter = presenter;

		ButterKnife.bind(this, itemView);

		itemView.setOnClickListener(this);

		contextMenuModule.init(itemView, R.id.menu, R.menu.menu_track);
		contextMenuModule.setOnMenuItemClickListener(this);
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setInfo(int tracksCount, String artist) {
		Resources res = context.getResources();
		String tracksCountStr = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		String info = res.getString(R.string.pattern_album_info, artist, tracksCountStr);
		infoView.setText(info);
	}

	public void setArt(String artPath) {
		// TODO: unit test correct artPath

		RequestOptions options = new RequestOptions()
				.error(R.drawable.fallback_cover)
				.placeholder(R.drawable.fallback_cover);

		Glide.with(context)
			 .load(artPath)
			 .apply(options)
			 .into(artView);
	}

	@Override
	public void onClick(View v) {
		SourceSharedViews sharedViews = new SourceSharedViews(Pair.create(artView, "art"));
		sharedViews.setOnEnterAnimationEndListener(presenter::onEnterAnimationEnded);
		presenter.onHolderClick(getAdapterPositionNonFinal(), sharedViews);
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_add_to_playlist:
			presenter.onAddToPlaylist(getAdapterPositionNonFinal());
			return true;
		default:
			return false;
		}
	}
}