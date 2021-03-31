package com.edavtyan.materialplayer2.ui.search.album;

import android.content.Context;

import com.edavtyan.materialplayer2.ui.SdkFactory;
import com.edavtyan.materialplayer2.ui.lists.album_list.AlbumListAdapter;

public class SearchAlbumAdapter extends AlbumListAdapter {
	public SearchAlbumAdapter(Context context, SearchAlbumPresenter presenter, SdkFactory sdkFactory) {
		super(context, presenter, sdkFactory);
	}
}
