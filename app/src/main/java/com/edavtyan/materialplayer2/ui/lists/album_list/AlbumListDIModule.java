package com.edavtyan.materialplayer2.ui.lists.album_list;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.edavtyan.materialplayer2.db.MediaDB;
import com.edavtyan.materialplayer2.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer2.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer2.ui.FragmentScope;
import com.edavtyan.materialplayer2.ui.SdkFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class AlbumListDIModule {
	private final Activity activity;
	private final AlbumListFragment view;

	public AlbumListDIModule(Activity activity, AlbumListFragment view) {
		this.activity = activity;
		this.view = view;
	}

	@Provides
	@FragmentScope
	public Activity provideActivity() {
		return activity;
	}

	@Provides
	@FragmentScope
	public AlbumListView provideView() {
		return view;
	}

	@Provides
	@FragmentScope
	public Fragment provideFragment() {
		return view;
	}

	@Provides
	@FragmentScope
	public AlbumListModel provideModel(
			ModelServiceModule serviceModule,
			MediaDB mediaDB,
			AlbumArtProvider albumArtProvider) {
		return new AlbumListModel(serviceModule, mediaDB, albumArtProvider);
	}

	@Provides
	@FragmentScope
	public AlbumListPresenter providePresenter(AlbumListModel model, AlbumListView view) {
		return new AlbumListPresenter(model, view);
	}

	@Provides
	@FragmentScope
	public AlbumListAdapter provideAdapter(
			Activity activity, AlbumListPresenter presenter, SdkFactory sdkFactory) {
		return new AlbumListAdapter(activity, presenter, sdkFactory);
	}
}
