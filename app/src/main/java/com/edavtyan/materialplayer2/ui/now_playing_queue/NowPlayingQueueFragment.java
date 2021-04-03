package com.edavtyan.materialplayer2.ui.now_playing_queue;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.edavtyan.materialplayer2.App;
import com.edavtyan.materialplayer2.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer2.ui.lists.lib.ListFragment;
import com.edavtyan.materialplayer2.ui.lists.lib.ListView;

import javax.inject.Inject;

public class NowPlayingQueueFragment extends ListFragment implements ListView {
	@Inject NowPlayingQueuePresenter presenter;
	@Inject NowPlayingQueueAdapter adapter;
	@Inject ScreenThemeModule themeModule;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getComponent().inject(this);
		addModule(themeModule);

		initListView(presenter, adapter);

		adapter.setHasStableIds(true);
		presenter.onCreate();
	}

	@Override
	public boolean isBackgroundEnabled() {
		return true;
	}

	@Override
	public void onCreateView(View view) {
		super.onCreateView(view);
		binding.root.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		presenter.onDestroy();
	}

	public void removeItem(int position) {
		adapter.notifyItemRemoved(position);
	}

	protected NowPlayingQueueDIComponent getComponent() {
		return DaggerNowPlayingQueueDIComponent
				.builder()
				.appDIComponent(((App) getActivity().getApplication()).getAppComponent())
				.nowPlayingQueueDIModule(new NowPlayingQueueDIModule(this))
				.build();
	}
}
