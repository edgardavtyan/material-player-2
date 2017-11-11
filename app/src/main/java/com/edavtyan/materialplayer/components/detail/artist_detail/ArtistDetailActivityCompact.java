package com.edavtyan.materialplayer.components.detail.artist_detail;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.detail.lib.ParallaxHeaderListCompactActivity;
import com.edavtyan.materialplayer.components.detail.lib.ParallaxHeaderListCompactModule;
import com.edavtyan.materialplayer.modular.activity.ActivityToolbarModule;

import static com.edavtyan.materialplayer.components.detail.artist_detail.ArtistDetailMvp.EXTRA_ARTIST_TITLE;

public class ArtistDetailActivityCompact
		extends ParallaxHeaderListCompactActivity
		implements ArtistDetailMvp.View {

	private Navigator navigator;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String artistTitle = getIntent().getStringExtra(EXTRA_ARTIST_TITLE);
		ArtistDetailFactory factory = ((App) getApplicationContext()).getArtistDetailDI(this, this, artistTitle);

		navigator = factory.getNavigator();

		addModule(new ActivityToolbarModule(this));
		addModule(new ParallaxHeaderListCompactModule(this, factory.getAdapter(), factory.getPresenter()));

		init(factory.getAdapter());
	}

	@Override
	public void setArtistTitle(String title) {
		setTitle(title);
	}

	@Override
	public void setArtistInfo(int albumsCount, int tracksCount) {
		Resources res = getResources();
		String portraitTopInfo = res.getQuantityString(R.plurals.albums, albumsCount, albumsCount);
		String portraitBottomInfo = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		String landscapeInfo = res.getString(R.string.pattern_artist_info, portraitTopInfo, portraitBottomInfo);
		setInfo(portraitTopInfo, portraitBottomInfo, landscapeInfo);
	}

	@Override
	public void setArtistImage(Bitmap image) {
		setImage(image, R.drawable.fallback_artist);
	}

	@Override
	public void gotoAlbumDetail(int albumId) {
		navigator.gotoAlbumDetail(albumId);
	}
}