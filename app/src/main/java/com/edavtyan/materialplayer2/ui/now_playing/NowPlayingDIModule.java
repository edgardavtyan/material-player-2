package com.edavtyan.materialplayer2.ui.now_playing;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.databinding.ActivityNowplayingBinding;
import com.edavtyan.materialplayer2.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer2.ui.ActivityScope;
import com.edavtyan.materialplayer2.ui.now_playing.models.NowPlayingArt;
import com.edavtyan.materialplayer2.ui.now_playing.models.NowPlayingControls;
import com.edavtyan.materialplayer2.ui.now_playing.models.NowPlayingFab;
import com.edavtyan.materialplayer2.ui.now_playing.models.NowPlayingInfo;
import com.edavtyan.materialplayer2.ui.now_playing.models.NowPlayingSeekbar;
import com.edavtyan.materialplayer2.ui.now_playing_queue.NowPlayingQueueFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class NowPlayingDIModule {
	private final NowPlayingActivity activity;
	private final ActivityNowplayingBinding binding;

	public NowPlayingDIModule(NowPlayingActivity activity, ActivityNowplayingBinding binding) {
		this.activity = activity;
		this.binding = binding;
	}

	@Provides
	@ActivityScope
	public NowPlayingActivity provideActivity() {
		return activity;
	}

	@Provides
	@ActivityScope
	public AppCompatActivity provideAppCompatActivity() {
		return activity;
	}

	@Provides
	@ActivityScope
	public NowPlayingQueueFragment provideNowPlayingQueueFragment() {
		return (NowPlayingQueueFragment) activity.getSupportFragmentManager()
												 .findFragmentById(R.id.fragment_queue);
	}

	@Provides
	@ActivityScope
	public NowPlayingModel provideModel(NowPlayingActivity activity,AlbumArtProvider albumArtProvider) {
		return new NowPlayingModel(activity, albumArtProvider);
	}

	@Provides
	@ActivityScope
	public NowPlayingPresenter providePresenter(NowPlayingModel model, NowPlayingActivity view) {
		return new NowPlayingPresenter(model, view);
	}

	@Provides
	@ActivityScope
	public NowPlayingArt provideArtPartial() {
		return new NowPlayingArt(binding);
	}

	@Provides
	@ActivityScope
	public NowPlayingControls provideControlsPartial(NowPlayingPresenter presenter) {
		return new NowPlayingControls(binding, presenter);
	}

	@Provides
	@ActivityScope
	public NowPlayingFab provideFabPartial(NowPlayingPresenter presenter) {
		return new NowPlayingFab(binding, presenter);
	}

	@Provides
	@ActivityScope
	public NowPlayingInfo provideInfoPartial(NowPlayingActivity activity) {
		return new NowPlayingInfo(activity, binding);
	}

	@Provides
	@ActivityScope
	public NowPlayingSeekbar provideSeekbarPartial(NowPlayingPresenter presenter) {
		return new NowPlayingSeekbar(binding, presenter);
	}

	@Provides
	@ActivityScope
	public QueueRevealAnimation provideQueueRevealAnimation(NowPlayingQueueFragment queueFragment) {
		return new QueueRevealAnimation(activity, queueFragment);
	}
}
