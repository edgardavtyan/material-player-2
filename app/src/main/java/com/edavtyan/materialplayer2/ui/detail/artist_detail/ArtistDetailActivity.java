package com.edavtyan.materialplayer2.ui.detail.artist_detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.edavtyan.materialplayer2.App;
import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.base.BaseActivity;
import com.edavtyan.materialplayer2.db.types.Track;
import com.edavtyan.materialplayer2.lib.playlist.models.PlaylistPresenter;
import com.edavtyan.materialplayer2.lib.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer2.lib.transition.SourceSharedViews;
import com.edavtyan.materialplayer2.lib.views.RV;
import com.edavtyan.materialplayer2.ui.Navigator;
import com.edavtyan.materialplayer2.ui.lists.album_list.AlbumListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArtistDetailActivity
		extends BaseActivity
		implements AlbumListView {

	public static final String EXTRA_ARTIST_TITLE = "extra_artistTitle";

	@BindView(R.id.list) RV listView;

	@Inject Navigator navigator;
	@Inject SharedTransitionsManager transitionsManager;
	@Inject PlaylistPresenter playlistPresenter;
	@Inject ArtistDetailPresenter presenter;
	@Inject ArtistDetailAdapter adapter;

	private @Nullable SourceSharedViews sharedViews;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		getComponent().inject(this);
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_artist);
		ButterKnife.bind(this);

		if (sharedViews != null) {
			transitionsManager.updateSourceViews(sharedViews);
		}

		presenter.onCreate();

		listView.setAdapter(adapter);
		listView.setLayoutManager(new LinearLayoutManager(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.menu_artist_detail, menu);
		return result;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_sort_name:
			presenter.onSortByName();
			return true;
		case R.id.menu_sort_year:
			presenter.onSortByDate();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void showPlaylistSelectionDialog(List<Track> tracks) {
		playlistPresenter.onAddToPlaylistClick(tracks);
	}

	@Override
	public void notifyItemChanged(int position) {

	}

	public void setArtistTitle(String title) {
		setTitle(title);
	}

	public void gotoAlbumDetail(int albumId, SourceSharedViews sharedViews) {
		this.sharedViews = sharedViews;
		transitionsManager.pushSourceViews(sharedViews);
		navigator.gotoAlbumDetail(this, albumId, sharedViews.build());
	}

	protected ArtistDetailDIComponent getComponent() {
		String artistTitle = getIntent().getStringExtra(EXTRA_ARTIST_TITLE);
		return DaggerArtistDetailDIComponent
				.builder()
				.appDIComponent(((App) getApplication()).getAppComponent())
				.artistDetailDIModule(new ArtistDetailDIModule(this, artistTitle))
				.build();
	}

	public void notifyDataSetChanged() {
		adapter.notifyDataSetChanged();
	}
}

