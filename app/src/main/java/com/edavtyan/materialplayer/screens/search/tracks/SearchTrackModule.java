package com.edavtyan.materialplayer.screens.search.tracks;

import android.support.v4.app.FragmentActivity;

import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.screens.FragmentScope;
import com.edavtyan.materialplayer.screens.SdkFactory;
import com.edavtyan.materialplayer.screens.search.base.SearchViewImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchTrackModule {
	private final SearchTrackFragment view;

	public SearchTrackModule(SearchTrackFragment view) {
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
			FragmentActivity activity, SearchTrackPresenter presenter, SdkFactory sdkFactory) {
		return new SearchTrackAdapter(activity, presenter, sdkFactory);
	}

	@Provides
	@FragmentScope
	public SearchTrackModel provideModel(
			ModelServiceModule serviceModule,
			TrackDB trackDB) {
		return new SearchTrackModel(serviceModule, trackDB);
	}

	@Provides
	@FragmentScope
	public SearchTrackPresenter providePresenter(
			SearchTrackModel model, SearchTrackFragment view) {
		return new SearchTrackPresenter(model, view);
	}

	@Provides
	@FragmentScope
	public SearchViewImpl provideSearchViewImpl(
			SearchTrackFragment view, SearchTrackPresenter presenter) {
		return new SearchViewImpl(view, presenter);
	}
}
