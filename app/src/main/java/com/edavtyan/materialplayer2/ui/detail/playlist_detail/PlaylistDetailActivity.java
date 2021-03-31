package com.edavtyan.materialplayer2.ui.detail.playlist_detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.edavtyan.materialplayer2.App;
import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.base.BaseActivity;
import com.edavtyan.materialplayer2.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer2.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer2.modular.activity.modules.ActivityToolbarModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaylistDetailActivity extends BaseActivity {
	public static final String EXTRA_PLAYLIST_NAME = "extra_playlistName";

	@BindView(R.id.list) RecyclerView list;

	@Inject PlaylistDetailAdapter adapter;
	@Inject PlaylistDetailPresenter presenter;
	@Inject ActivityToolbarModule toolbarModule;
	@Inject ActivityBaseMenuModule baseMenuModule;
	@Inject ScreenThemeModule themeModule;

	private ItemTouchHelper touchHelper;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_playlist);
		getComponent().inject(this);
		addModule(toolbarModule);
		addModule(baseMenuModule);
		addModule(themeModule);
		ButterKnife.bind(this);

		ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
		touchHelper = new ItemTouchHelper(callback);
		touchHelper.attachToRecyclerView(list);

		list.setLayoutManager(new LinearLayoutManager(this));
		list.setAdapter(adapter);

		presenter.onCreate();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		presenter.onDestroy();
	}

	public void notifyItemRemoved(int position) {
		adapter.notifyItemRemoved(position);
	}

	public void notifyItemMoved(int fromPosition, int toPosition) {
		adapter.notifyItemMoved(fromPosition, toPosition);
	}

	public void onHandleDrag(PlaylistDetailViewHolder holder) {
		touchHelper.startDrag(holder);
	}

	private PlaylistDetailDIComponent getComponent() {
		String playlistName = getIntent().getStringExtra(EXTRA_PLAYLIST_NAME);
		return DaggerPlaylistDetailDIComponent
				.builder()
				.appDIComponent(((App) getApplication()).getAppComponent())
				.playlistDetailDIModule(new PlaylistDetailDIModule(this, playlistName))
				.build();
	}

}
