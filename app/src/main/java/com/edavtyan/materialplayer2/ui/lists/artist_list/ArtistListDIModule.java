package com.edavtyan.materialplayer2.ui.lists.artist_list;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.edavtyan.materialplayer2.db.MediaDB;
import com.edavtyan.materialplayer2.lib.music_api.MusicApi;
import com.edavtyan.materialplayer2.lib.prefs.AdvancedGsonSharedPrefs;
import com.edavtyan.materialplayer2.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer2.ui.FragmentScope;
import com.edavtyan.materialplayer2.ui.SdkFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class ArtistListDIModule {
	private final Activity activity;
	private final ArtistListFragment view;

	public ArtistListDIModule(Activity activity, ArtistListFragment view) {
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
	public ArtistListView provideView() {
		return view;
	}

	@Provides
	@FragmentScope
	public Fragment provideFragment() {
		return view;
	}

	@Provides
	@FragmentScope
	public ArtistListModel provideModel(
			ModelServiceModule serviceModule,
			MediaDB mediaDB,
			ArtistListImageLoader imageLoader) {
		return new ArtistListModel(serviceModule, mediaDB, imageLoader);
	}

	@Provides
	@FragmentScope
	public ArtistListPresenter providePresenter(ArtistListModel model, ArtistListView view) {
		return new ArtistListPresenter(model, view);
	}

	@Provides
	@FragmentScope
	public ArtistListAdapter provideAdapter(
			Activity activity, ArtistListPresenter presenter, SdkFactory sdkFactory) {
		return new ArtistListAdapter(activity, presenter, sdkFactory);
	}

	@Provides
	@FragmentScope
	public ArtistListImageLoader provideImageLoader(
			MusicApi musicApi,
			ArtistListImageLinkCache linkCache) {
		return new ArtistListImageLoader(musicApi, linkCache);
	}

	@Provides
	@FragmentScope
	public ArtistListImageLinkCache provideArtistListImageLinkCache(AdvancedGsonSharedPrefs prefs) {
		return new ArtistListImageLinkCache(prefs);
	}
}
