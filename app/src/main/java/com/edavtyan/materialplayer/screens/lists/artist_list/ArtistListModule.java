package com.edavtyan.materialplayer.screens.lists.artist_list;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.edavtyan.materialplayer.screens.FragmentScope;
import com.edavtyan.materialplayer.screens.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.utils.WebClient;

import dagger.Module;
import dagger.Provides;

@Module
public class ArtistListModule {
	private final FragmentActivity activity;
	private final ArtistListView view;

	public ArtistListModule(FragmentActivity activity, ArtistListView view) {
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
	public ArtistListView provideView() {
		return view;
	}

	@Provides
	@FragmentScope
	public ArtistListModel provideModel(
			ModelServiceModule serviceModule,
			ArtistDB artistDB,
			TrackDB trackDB,
			ArtistListImageLoader imageLoader,
			CompactListPref compactListPref) {
		return new ArtistListModel(
				serviceModule,
				artistDB,
				trackDB,
				imageLoader,
				compactListPref);
	}

	@Provides
	@FragmentScope
	public ArtistListPresenter providePresenter(ArtistListModel model, ArtistListView view) {
		return new ArtistListPresenter(model, view);
	}

	@Provides
	@FragmentScope
	public ArtistListAdapter provideAdapter(FragmentActivity activity, ArtistListPresenter presenter) {
		return new ArtistListAdapter(activity, presenter);
	}

	@Provides
	@FragmentScope
	public ArtistListImageLoader provideImageLoader(
			WebClient webClient,
			TestableBitmapFactory bitmapFactory,
			LastfmApi lastfmApi,
			ArtistListImageFileStorage fileStorage,
			ArtistListImageMemoryCache memoryCache) {
		return new ArtistListImageLoader(webClient, bitmapFactory, lastfmApi, fileStorage, memoryCache);
	}

	@Provides
	@FragmentScope
	public ArtistListImageFileStorage provideImageFileStorage(Context context) {
		return new ArtistListImageFileStorage(context);
	}

	@Provides
	@FragmentScope
	public ArtistListImageMemoryCache provideImageMemoryCache() {
		return new ArtistListImageMemoryCache();
	}
}