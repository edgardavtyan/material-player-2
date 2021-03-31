package com.edavtyan.materialplayer2.ui.search.artist;

import android.app.Activity;

import com.edavtyan.materialplayer2.db.MediaDB;
import com.edavtyan.materialplayer2.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer2.ui.FragmentScope;
import com.edavtyan.materialplayer2.ui.SdkFactory;
import com.edavtyan.materialplayer2.ui.lists.artist_list.ArtistListImageLoader;
import com.edavtyan.materialplayer2.ui.search.base.SearchViewImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchArtistDIModule {
	private final SearchArtistFragment view;

	public SearchArtistDIModule(SearchArtistFragment view) {
		this.view = view;
	}

	@Provides
	@FragmentScope
	public SearchArtistFragment provideView() {
		return view;
	}

	@Provides
	@FragmentScope
	public SearchArtistAdapter provideAdapter(
			Activity activity, SearchArtistPresenter presenter, SdkFactory sdkFactory) {
		return new SearchArtistAdapter(activity, presenter, sdkFactory);
	}

	@Provides
	@FragmentScope
	public SearchArtistModel provideModel(
			ModelServiceModule serviceModule,
			MediaDB mediaDB,
			ArtistListImageLoader imageLoader) {
		return new SearchArtistModel(serviceModule, mediaDB, imageLoader);
	}

	@Provides
	@FragmentScope
	public SearchArtistPresenter providePresenter(SearchArtistModel model, SearchArtistFragment view) {
		return new SearchArtistPresenter(model, view);
	}

	@Provides
	@FragmentScope
	public SearchViewImpl provideSearchViewImpl(
			SearchArtistFragment view, SearchArtistPresenter presenter) {
		return new SearchViewImpl(view, presenter);
	}
}
