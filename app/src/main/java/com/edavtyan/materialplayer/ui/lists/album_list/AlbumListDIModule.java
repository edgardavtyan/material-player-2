package com.edavtyan.materialplayer.ui.lists.album_list;

import android.support.v4.app.FragmentActivity;

import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.ui.SdkFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class AlbumListDIModule {
	private final FragmentActivity activity;
	private final AlbumListView view;

	public AlbumListDIModule(FragmentActivity activity, AlbumListView view) {
		this.activity = activity;
		this.view = view;
	}

	@Provides
	@FragmentScope
	public FragmentActivity provideActivity() {
		return activity;
	}

	@Provides
	@FragmentScope
	public AlbumListView provideView() {
		return view;
	}

	@Provides
	@FragmentScope
	public AlbumListModel provideModel(
			ModelServiceModule serviceModule,
			AlbumDB albumDB,
			TrackDB trackDB) {
		return new AlbumListModel(serviceModule, albumDB, trackDB);
	}

	@Provides
	@FragmentScope
	public AlbumListPresenter providePresenter(AlbumListModel model, AlbumListView view) {
		return new AlbumListPresenter(model, view);
	}

	@Provides
	@FragmentScope
	public AlbumListAdapter provideAdapter(
			FragmentActivity activity, AlbumListPresenter presenter, SdkFactory sdkFactory) {
		return new AlbumListAdapter(activity, presenter, sdkFactory);
	}
}