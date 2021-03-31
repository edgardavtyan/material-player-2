package com.edavtyan.materialplayer2.ui.search.tracks;

import com.edavtyan.materialplayer2.db.MediaDB;
import com.edavtyan.materialplayer2.db.types.Track;
import com.edavtyan.materialplayer2.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer2.ui.lists.track_list.TrackListModel;
import com.edavtyan.materialplayer2.ui.search.base.SearchModel;

import java.util.List;

import lombok.Setter;

public class SearchTrackModel extends TrackListModel implements SearchModel {
	private final MediaDB mediaDB;

	private @Setter String query;

	public SearchTrackModel(ModelServiceModule serviceModule, MediaDB mediaDB) {
		super(serviceModule, mediaDB);
		this.mediaDB = mediaDB;
	}

	@Override
	protected List<Track> queryTracks() {
		return mediaDB.searchTracks(query);
	}

	@Override
	public int getSearchResultCount() {
		return getItemCount();
	}
}
