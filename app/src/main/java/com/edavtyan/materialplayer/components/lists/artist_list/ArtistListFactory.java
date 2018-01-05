package com.edavtyan.materialplayer.components.lists.artist_list;

import android.content.Context;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.lists.lib.ListFactory;
import com.edavtyan.materialplayer.db.DBModule;
import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;
import com.edavtyan.materialplayer.lib.lastfm.LastfmArtistInfoFileStorage;

public class ArtistListFactory extends ListFactory {
	private final ArtistListView view;
	private final DBModule dbModule;
	private ArtistListModel model;
	private ArtistListPresenter presenter;
	private ArtistListAdapter adapter;
	private LastfmApi lastfmApi;
	private ArtistListImageFileStorage fileStorage;
	private ArtistListImageMemoryCache memoryCache;
	private ArtistListImageLoader imageLoader;

	public ArtistListFactory(Context context, ArtistListView view) {
		super(context);
		this.view = view;
		dbModule = new DBModule(context);
	}

	public ArtistListView getView() {
		return view;
	}

	public ArtistListModel getModel() {
		if (model == null)
			model = new ArtistListModel(
					getModelServiceModule(),
					dbModule.getArtistDB(),
					dbModule.getTrackDB(),
					getImageLoader(),
					getCompactListPref());
		return model;
	}

	public ArtistListPresenter getPresenter() {
		if (presenter == null)
			presenter = new ArtistListPresenter(getModel(), getView());
		return presenter;
	}

	public ArtistListAdapter getAdapter() {
		if (adapter == null)
			adapter = new ArtistListAdapter(getContext(), getPresenter());
		return adapter;
	}

	public LastfmApi getLastfmApi() {
		if (lastfmApi == null)
			lastfmApi = new LastfmApi(
					getWebClient(),
					new LastfmArtistInfoFileStorage(getContext()),
					getContext().getString(R.string.lastfm_api_key));
		return lastfmApi;
	}

	public ArtistListImageLoader getImageLoader() {
		if (imageLoader == null)
			imageLoader = new ArtistListImageLoader(
					getWebClient(),
					getBitmapFactory(),
					getLastfmApi(),
					getFileStorage(),
					getMemoryCache());
		return imageLoader;
	}

	public ArtistListImageFileStorage getFileStorage() {
		if (fileStorage == null)
			fileStorage = new ArtistListImageFileStorage(getContext());
		return fileStorage;
	}

	public ArtistListImageMemoryCache getMemoryCache() {
		if (memoryCache == null)
			memoryCache = new ArtistListImageMemoryCache();
		return memoryCache;
	}
}
