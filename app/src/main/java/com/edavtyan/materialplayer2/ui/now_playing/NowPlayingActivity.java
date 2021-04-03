package com.edavtyan.materialplayer2.ui.now_playing;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer2.App;
import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.base.BaseActivityTransparent;
import com.edavtyan.materialplayer2.databinding.ActivityNowplayingBinding;
import com.edavtyan.materialplayer2.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer2.lib.transition.OutputSharedViews;
import com.edavtyan.materialplayer2.lib.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer2.lib.transition.SharedViewSet;
import com.edavtyan.materialplayer2.modular.activity.ActivityModulesDIModule;
import com.edavtyan.materialplayer2.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer2.modular.activity.modules.ActivityToolbarModule;
import com.edavtyan.materialplayer2.ui.Navigator;
import com.edavtyan.materialplayer2.ui.now_playing.models.NowPlayingArt;
import com.edavtyan.materialplayer2.ui.now_playing.models.NowPlayingControls;
import com.edavtyan.materialplayer2.ui.now_playing.models.NowPlayingFab;
import com.edavtyan.materialplayer2.ui.now_playing.models.NowPlayingInfo;
import com.edavtyan.materialplayer2.ui.now_playing.models.NowPlayingSeekbar;

import javax.inject.Inject;

import lombok.Getter;

public class NowPlayingActivity extends BaseActivityTransparent {
	@Inject ActivityBaseMenuModule baseMenuModule;
	@Inject ActivityToolbarModule toolbarModule;
	@Inject ScreenThemeModule themeModule;

	@Inject NowPlayingPresenter presenter;
	@Inject Navigator navigator;
	@Inject SharedTransitionsManager transitionManager;
	@Inject QueueRevealAnimation queueRevealAnimation;

	@Inject @Getter NowPlayingControls controls;
	@Inject @Getter NowPlayingInfo info;
	@Inject @Getter NowPlayingArt art;
	@Inject @Getter NowPlayingSeekbar seekbar;
	@Inject @Getter NowPlayingFab fab;

	private boolean isQueueShown;
	private ActivityNowplayingBinding binding;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = ActivityNowplayingBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		getComponent().inject(this);
		addModule(baseMenuModule);
		addModule(toolbarModule);
		addModule(themeModule);
		presenter.bind();

		OutputSharedViews.Builder outputViewsBuilder = OutputSharedViews.builder();
		outputViewsBuilder.sharedViewSets(
				SharedViewSet.translating("art", binding.art, binding.sharedArt));
		outputViewsBuilder
				.enterFadingViews(
						binding.fastForward,
						binding.playPause,
						binding.repeat,
						binding.rewind,
						binding.shuffle,
						binding.art,
						binding.artShadow,
						binding.timeCurrent,
						binding.timeTotal,
						binding.seekbar,
						binding.info,
						binding.title,
						binding.fab,
						binding.appbar,
						binding.background)
				.exitPortraitFadingViews(
						binding.fastForward,
						binding.playPause,
						binding.repeat,
						binding.rewind,
						binding.shuffle,
						binding.art,
						binding.artShadow,
						binding.timeCurrent,
						binding.timeTotal,
						binding.seekbar,
						binding.info,
						binding.title,
						binding.fab,
						binding.appbar,
						binding.background)
				.exitLandscapeFadingViews(binding.innerContainer, binding.fab);
		transitionManager.createSharedTransition(outputViewsBuilder.build());
		transitionManager.beginEnterTransition(this, savedInstanceState);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		presenter.unbind();
	}

	@Override
	public void onBackPressed() {
		if (isQueueShown) {
			queueRevealAnimation.hide();
			isQueueShown = false;
		} else {
			transitionManager.beginExitTransition(this);
		}
	}

	public void showQueue() {
		queueRevealAnimation.show();
		isQueueShown = true;
	}

	protected NowPlayingDIComponent getComponent() {
		return DaggerNowPlayingDIComponent
				.builder()
				.appDIComponent(((App) getApplication()).getAppComponent())
				.nowPlayingDIModule(new NowPlayingDIModule(this, binding))
				.activityModulesDIModule(new ActivityModulesDIModule(R.string.nowplaying_toolbar_title))
				.build();
	}
}
