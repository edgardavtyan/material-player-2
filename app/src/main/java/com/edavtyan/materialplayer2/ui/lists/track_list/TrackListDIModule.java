package com.edavtyan.materialplayer2.ui.lists.track_list;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.edavtyan.materialplayer2.db.MediaDB;
import com.edavtyan.materialplayer2.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer2.ui.FragmentScope;
import com.edavtyan.materialplayer2.ui.SdkFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class TrackListDIModule {
	private final Activity activity;
	private final TrackListFragment view;

	public TrackListDIModule(Activity activity, TrackListFragment view) {
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
	public TrackListView provideView() {
		return view;
	}

	@Provides
	@FragmentScope
	public Fragment provideFragment() {
		return view;
	}

	@Provides
	@FragmentScope
	public TrackListModel provideModel(
			ModelServiceModule serviceModule,
			MediaDB mediaDB) {
		return new TrackListModel(serviceModule, mediaDB);
	}

	@Provides
	@FragmentScope
	public TrackListPresenter providePresenter(TrackListView view, TrackListModel model) {
		return new TrackListPresenter(view, model);
	}

	@Provides
	@FragmentScope
	public TrackListAdapter provideAdapter(
			Activity activity, TrackListPresenter presenter, SdkFactory sdkFactory) {
		return new TrackListAdapter(activity, presenter, sdkFactory);
	}
}
