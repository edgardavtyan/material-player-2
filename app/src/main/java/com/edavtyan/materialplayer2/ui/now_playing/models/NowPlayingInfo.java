package com.edavtyan.materialplayer2.ui.now_playing.models;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.databinding.ActivityNowplayingBinding;
import com.edavtyan.materialplayer2.ui.now_playing.NowPlayingActivity;

import butterknife.ButterKnife;

public class NowPlayingInfo {
	private final NowPlayingActivity activity;
	private final ActivityNowplayingBinding binding;

	public NowPlayingInfo(NowPlayingActivity activity, ActivityNowplayingBinding binding) {
		this.activity = activity;
		this.binding = binding;
		ButterKnife.bind(this, activity);
	}

	public void setTitle(String title) {
		binding.title.setText(title);
	}

	public void setInfo(String artist, String album) {
		String info = activity.getString(R.string.nowplaying_info_pattern, artist, album);
		binding.info.setText(info);
	}
}
