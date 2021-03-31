package com.edavtyan.materialplayer2.ui.now_playing.models;

import android.widget.TextView;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.ui.now_playing.NowPlayingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;

public class NowPlayingInfo {

	@Getter @BindView(R.id.title) TextView titleView;
	@Getter @BindView(R.id.info) TextView infoView;

	private final NowPlayingActivity activity;

	public NowPlayingInfo(NowPlayingActivity activity) {
		this.activity = activity;
		ButterKnife.bind(this, activity);
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setInfo(String artist, String album) {
		String info = activity.getString(R.string.nowplaying_info_pattern, artist, album);
		infoView.setText(info);
	}
}
