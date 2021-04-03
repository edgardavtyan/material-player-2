package com.edavtyan.materialplayer2.ui.lists.album_list;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.util.Pair;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.databinding.ListitemAlbumBinding;
import com.edavtyan.materialplayer2.lib.testable.TestableViewHolder;
import com.edavtyan.materialplayer2.lib.transition.SourceSharedViews;
import com.edavtyan.materialplayer2.modular.viewholder.ContextMenuModule;

public class AlbumListViewHolder
		extends TestableViewHolder
		implements PopupMenu.OnMenuItemClickListener,
				   View.OnClickListener {
	private final Context context;
	private final AlbumListPresenter presenter;
	private final ListitemAlbumBinding binding;

	public AlbumListViewHolder(
			Context context,
			View itemView,
			AlbumListPresenter presenter,
			ContextMenuModule contextMenuModule) {
		super(itemView);
		this.context = context;
		this.presenter = presenter;

		binding = ListitemAlbumBinding.bind(itemView);

		itemView.setOnClickListener(this);

		contextMenuModule.init(itemView, R.id.menu, R.menu.menu_track);
		contextMenuModule.setOnMenuItemClickListener(this);
	}

	public void setTitle(String title) {
		binding.title.setText(title);
	}

	public void setInfo(int tracksCount, String artist) {
		Resources res = context.getResources();
		String tracksCountStr = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		String info = res.getString(R.string.pattern_album_info, artist, tracksCountStr);
		binding.info.setText(info);
	}

	public void setArt(String artPath) {
		// TODO: unit test correct artPath

		RequestOptions options = new RequestOptions()
				.error(R.drawable.fallback_cover)
				.placeholder(R.drawable.fallback_cover);

		Glide.with(context)
			 .load(artPath)
			 .apply(options)
			 .transition(DrawableTransitionOptions.withCrossFade())
			 .into(binding.art);
	}

	@Override
	public void onClick(View v) {
		SourceSharedViews sharedViews = new SourceSharedViews(Pair.create(binding.art, "art"));
		sharedViews.setOnExitAnimationEndListener(presenter::onEnterAnimationEnded);
		presenter.onHolderClick(getAdapterPositionNonFinal(), sharedViews);
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_add_to_queue:
			presenter.onAddToQueue(getAdapterPositionNonFinal());
			return true;
		case R.id.menu_add_to_playlist:
			presenter.onAddToPlaylist(getAdapterPositionNonFinal());
		default:
			return false;
		}
	}
}
