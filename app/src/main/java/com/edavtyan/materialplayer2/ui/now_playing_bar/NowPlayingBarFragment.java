package com.edavtyan.materialplayer2.ui.now_playing_bar;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ed.libsutils.utils.BitmapResizer;
import com.ed.libsutils.utils.DpConverter;
import com.edavtyan.materialplayer2.App;
import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.databinding.FragmentNowplayingBarBinding;
import com.edavtyan.materialplayer2.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer2.lib.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer2.lib.transition.SourceSharedViews;
import com.edavtyan.materialplayer2.modular.fragment.ModularFragment;
import com.edavtyan.materialplayer2.ui.Navigator;

import javax.inject.Inject;

public class NowPlayingBarFragment extends ModularFragment implements View.OnClickListener {

	public static final int SCALED_ART_SIZE_DP = 44;

	@Inject ScreenThemeModule themeModule;
	@Inject NowPlayingBarPresenter presenter;
	@Inject Navigator navigator;
	@Inject SharedTransitionsManager transitionsManager;

	private FragmentNowplayingBarBinding binding;

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.play_pause:
			presenter.onPlayPauseButtonClick();
			break;
		case R.id.art:
		case R.id.info_wrapper:
			presenter.onViewClick();
			break;
		}
	}

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_nowplaying_bar;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getComponent().inject(this);
		addModule(themeModule);
	}

	@Nullable
	@Override
	public View onCreateView(
			LayoutInflater inflater,
			@Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);
		binding = FragmentNowplayingBarBinding.bind(view);
		binding.playPause.setOnClickListener(this);
		binding.art.setOnClickListener(this);
		binding.infoWrapper.setOnClickListener(this);

		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		presenter.onCreate();
	}

	@Override
	public void onStop() {
		super.onStop();
		presenter.onDestroy();
	}

	public void setTrackTitle(String title) {
		binding.title.setText(title);
	}

	public void setTrackInfo(String artistTitle, String albumTitle) {
		Resources res = getContext().getResources();
		String info = res.getString(R.string.nowplaying_info_pattern, artistTitle, albumTitle);
		binding.info.setText(info);
	}

	public void setArt(@Nullable Bitmap art) {
		if (art != null) {
			int scaledArtSize = DpConverter.dpToPixel(SCALED_ART_SIZE_DP);
			Bitmap scaledArt = BitmapResizer.resize(art, scaledArtSize);
			binding.art.setImageBitmap(scaledArt);
		} else {
			binding.art.setImageResource(R.drawable.fallback_cover);
		}
	}

	public void setIsPlaying(boolean isPlaying) {
		binding.playPause.setImageResource(isPlaying ? R.drawable.ic_pause : R.drawable.ic_play);
	}

	public void setIsVisible(boolean visibility) {
		binding.infoWrapper.setVisibility(visibility ? View.VISIBLE : View.GONE);
	}

	public void gotoNowPlaying() {
		SourceSharedViews sharedViews = new SourceSharedViews(Pair.create(binding.art, "art"));
		transitionsManager.pushSourceViews(sharedViews);
		navigator.gotoNowPlaying(getActivity(), sharedViews.build());
	}

	protected NowPlayingBarDIComponent getComponent() {
		return DaggerNowPlayingBarDIComponent
				.builder()
				.appDIComponent(((App) getContext().getApplicationContext()).getAppComponent())
				.nowPlayingBarDIModule(new NowPlayingBarDIModule(getActivity(), this))
				.build();
	}
}
