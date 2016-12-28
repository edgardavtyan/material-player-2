package com.edavtyan.materialplayer.components.now_playing;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;

public class NowPlayingActivity extends BaseToolbarActivity implements NowPlayingMvp.View {
	private NowPlayingMvp.Presenter presenter;
	private Navigator navigator;

	private Controls controls;
	private Info info;
	private Art art;
	private Seekbar seekbar;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		App app = (App) getApplicationContext();
		NowPlayingFactory factory = app.getNowPlayingFactory(this, this);
		presenter = factory.providePresenter();
		controls = factory.provideControls();
		info = factory.provideInfo();
		art = factory.provideArt();
		seekbar = factory.provideSeekbar();
		navigator = factory.provideNavigator();
		factory.provideFab();

		presenter.bind();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		presenter.unbind();
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_nowplaying;
	}

	@Override
	protected int getToolbarTitleStringId() {
		return R.string.nowplaying_toolbar_title;
	}

	@Override
	public Controls getControls() {
		return controls;
	}

	@Override
	public Info getInfo() {
		return info;
	}

	@Override
	public Art getArt() {
		return art;
	}

	@Override
	public Seekbar getSeekbar() {
		return seekbar;
	}

	@Override
	public void gotoPlaylistScreen() {
		navigator.gotoNowPlayingQueue();
	}
}