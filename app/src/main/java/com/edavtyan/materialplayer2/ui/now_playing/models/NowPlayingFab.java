package com.edavtyan.materialplayer2.ui.now_playing.models;

import android.view.View;

import com.edavtyan.materialplayer2.databinding.ActivityNowplayingBinding;
import com.edavtyan.materialplayer2.ui.now_playing.NowPlayingPresenter;

public class NowPlayingFab implements View.OnClickListener {
	private final NowPlayingPresenter presenter;

	public NowPlayingFab(ActivityNowplayingBinding binding, NowPlayingPresenter presenter) {
		this.presenter = presenter;
		binding.fab.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		presenter.onFabClick();
	}
}
