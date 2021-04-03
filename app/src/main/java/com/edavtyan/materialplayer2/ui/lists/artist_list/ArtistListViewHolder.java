package com.edavtyan.materialplayer2.ui.lists.artist_list;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.databinding.ListitemAlbumBinding;
import com.edavtyan.materialplayer2.lib.testable.TestableViewHolder;
import com.edavtyan.materialplayer2.modular.viewholder.ContextMenuModule;

public class ArtistListViewHolder
		extends TestableViewHolder
		implements View.OnClickListener,
				   PopupMenu.OnMenuItemClickListener {
	private final Context context;
	private final ArtistListPresenter presenter;
	private final ListitemAlbumBinding binding;

	public ArtistListViewHolder(
			Context context,
			View itemView,
			ArtistListPresenter presenter,
			ContextMenuModule contextMenu) {
		super(itemView);
		this.context = context;
		this.presenter = presenter;

		binding = ListitemAlbumBinding.bind(itemView);

		itemView.setOnClickListener(this);

		contextMenu.init(itemView, R.id.menu, R.menu.menu_track);
		contextMenu.setOnMenuItemClickListener(this);
	}

	public void setTitle(String title) {
		binding.title.setText(title);
	}

	public void setInfo(int albumsCount, int tracksCount) {
		Resources res = context.getResources();
		String albumsCountStr = res.getQuantityString(R.plurals.albums, albumsCount, albumsCount);
		String tracksCountStr = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		String info = res.getString(R.string.pattern_artist_info, albumsCountStr, tracksCountStr);
		binding.info.setText(info);
	}

	public void setImage(String artFilename) {
		RequestOptions options = new RequestOptions()
				.error(R.drawable.fallback_artist)
				.placeholder(R.drawable.fallback_artist);

		Glide.with(context)
			 .load(artFilename)
			 .apply(options)
			 .transition(DrawableTransitionOptions.withCrossFade())
			 .into(binding.art);
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
			presenter.onAddToPlaylist(getAdapterPositionNonFinal());
			return true;
		default:
			return false;
		}
	}
}
