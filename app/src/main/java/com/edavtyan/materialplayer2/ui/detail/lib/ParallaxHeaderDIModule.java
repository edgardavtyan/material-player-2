package com.edavtyan.materialplayer2.ui.detail.lib;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer2.databinding.ActivityDetailBinding;
import com.edavtyan.materialplayer2.lib.testable.TestableRecyclerAdapter;
import com.edavtyan.materialplayer2.ui.ActivityScope;
import com.edavtyan.materialplayer2.lib.transition.SharedTransitionsManager;

import dagger.Module;
import dagger.Provides;

@Module
public class ParallaxHeaderDIModule {
	@Provides
	@ActivityScope
	public ParallaxHeaderListModule provideParallaxHeaderListModule(
			AppCompatActivity activity,
			TestableRecyclerAdapter adapter,
			ParallaxHeaderListPresenter presenter,
			SharedTransitionsManager transitionsManager,
			ActivityDetailBinding binding) {
		return new ParallaxHeaderListModule(activity, adapter, presenter, transitionsManager, binding);
	}
}
