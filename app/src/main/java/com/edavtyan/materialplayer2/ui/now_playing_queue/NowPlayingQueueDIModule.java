package com.edavtyan.materialplayer2.ui.now_playing_queue;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer2.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer2.ui.FragmentScope;
import com.edavtyan.materialplayer2.ui.SdkFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class NowPlayingQueueDIModule {
	private final NowPlayingQueueFragment fragment;

	public NowPlayingQueueDIModule(NowPlayingQueueFragment fragment) {
		this.fragment = fragment;
	}

	@Provides
	@FragmentScope
	public NowPlayingQueueFragment provideView() {
		return fragment;
	}

	@Provides
	@FragmentScope
	public Fragment provideFragment() {
		return fragment;
	}

	@Provides
	@FragmentScope
	public Activity provideActivity() {
		return fragment.getActivity();
	}

	@Provides
	@FragmentScope
	public AppCompatActivity provideAppCompatActivity() {
		return (AppCompatActivity) fragment.getActivity();
	}

	@Provides
	@FragmentScope
	public NowPlayingQueueModel provideModel(ModelServiceModule serviceModule) {
		return new NowPlayingQueueModel(serviceModule);
	}

	@Provides
	@FragmentScope
	public NowPlayingQueuePresenter providePresenter(
			NowPlayingQueueModel model,
			NowPlayingQueueFragment view) {
		return new NowPlayingQueuePresenter(model, view);
	}

	@Provides
	@FragmentScope
	public NowPlayingQueueAdapter provideAdapter(
			AppCompatActivity activity,
			NowPlayingQueuePresenter presenter,
			SdkFactory sdkFactory) {
		return new NowPlayingQueueAdapter(activity, presenter, sdkFactory);
	}
}
