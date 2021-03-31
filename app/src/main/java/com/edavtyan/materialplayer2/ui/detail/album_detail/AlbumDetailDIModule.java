package com.edavtyan.materialplayer2.ui.detail.album_detail;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer2.db.MediaDB;
import com.edavtyan.materialplayer2.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer2.lib.testable.TestableRecyclerAdapter;
import com.edavtyan.materialplayer2.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer2.ui.ActivityScope;
import com.edavtyan.materialplayer2.ui.SdkFactory;
import com.edavtyan.materialplayer2.ui.detail.lib.ParallaxHeaderListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class AlbumDetailDIModule {
	private final AlbumDetailActivity activity;
	private final int albumId;

	public AlbumDetailDIModule(AlbumDetailActivity activity, int albumId) {
		this.activity = activity;
		this.albumId = albumId;
	}

	@Provides
	@ActivityScope
	public AppCompatActivity provideActivity() {
		return activity;
	}

	@Provides
	@ActivityScope
	public AlbumDetailModel provideModel(
			ModelServiceModule serviceModule,
			MediaDB mediaDB,
			AlbumArtProvider artProvider) {
		return new AlbumDetailModel(serviceModule, mediaDB, artProvider, albumId);
	}

	@Provides
	@ActivityScope
	public AlbumDetailAdapter provideAdapter(AlbumDetailPresenter presenter, SdkFactory sdkFactory) {
		return new AlbumDetailAdapter(activity, presenter, sdkFactory);
	}

	@Provides
	@ActivityScope
	public TestableRecyclerAdapter provideTestableRecyclerAdapter(AlbumDetailAdapter adapter) {
		return adapter;
	}

	@Provides
	@ActivityScope
	public AlbumDetailPresenter providePresenter(AlbumDetailModel model) {
		return new AlbumDetailPresenter(model, activity);
	}

	@Provides
	@ActivityScope
	public ParallaxHeaderListPresenter provideParallaxPresenter(AlbumDetailPresenter presenter) {
		return presenter;
	}
}
