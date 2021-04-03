package com.edavtyan.materialplayer2.ui.now_playing.models;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.databinding.ActivityNowplayingBinding;

public class NowPlayingArt {
	private final ActivityNowplayingBinding binding;

	public NowPlayingArt(ActivityNowplayingBinding binding) {
		this.binding = binding;
	}

	public void setArt(@Nullable Bitmap art) {
		if (art != null) {
			binding.art.setImageBitmap(art);
			binding.sharedArt.setImageBitmap(art);
		} else {
			binding.art.setImageResource(R.drawable.fallback_cover);
			binding.sharedArt.setImageResource(R.drawable.fallback_cover);
		}
	}
}
