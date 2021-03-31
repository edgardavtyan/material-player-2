package com.edavtyan.materialplayer2.ui.search.tracks;

import android.content.Context;

import com.edavtyan.materialplayer2.ui.SdkFactory;
import com.edavtyan.materialplayer2.ui.lists.track_list.TrackListAdapter;

public class SearchTrackAdapter extends TrackListAdapter {
	public SearchTrackAdapter(Context context, SearchTrackPresenter presenter, SdkFactory sdkFactory) {
		super(context, presenter, sdkFactory);
	}
}
