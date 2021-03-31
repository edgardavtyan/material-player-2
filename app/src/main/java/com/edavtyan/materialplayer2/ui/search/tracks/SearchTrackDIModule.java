package com.edavtyan.materialplayer2.ui.search.tracks;

import android.app.Activity;

import com.edavtyan.materialplayer2.db.MediaDB;
import com.edavtyan.materialplayer2.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer2.ui.FragmentScope;
import com.edavtyan.materialplayer2.ui.SdkFactory;
import com.edavtyan.materialplayer2.ui.search.base.SearchViewImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchTrackDIModule {
	private final SearchTrackFragment view;

	public SearchTrackDIModule(SearchTrackFragment view) {
		this.view = view;
	}

	@Provides
	@FragmentScope
	public SearchTrackFragment provideView() {
		return view;
	}

	@Provides
	@FragmentScope
	public SearchTrackAdapter provideAdapter(
			Activity activity, SearchTrackPresenter presenter, SdkFactory sdkFactory) {
		return new SearchTrackAdapter(activity, presenter, sdkFactory);
	}

	@Provides
	@FragmentScope
	public SearchTrackModel provideModel(ModelServiceModule serviceModule, MediaDB mediaDB) {
		return new SearchTrackModel(serviceModule, mediaDB);
	}

	@Provides
	@FragmentScope
	public SearchTrackPresenter providePresenter(SearchTrackModel model, SearchTrackFragment view) {
		return new SearchTrackPresenter(model, view);
	}

	@Provides
	@FragmentScope
	public SearchViewImpl provideSearchViewImpl(
			SearchTrackFragment view, SearchTrackPresenter presenter) {
		return new SearchViewImpl(view, presenter);
	}
}
