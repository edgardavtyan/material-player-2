package com.edavtyan.materialplayer2.lib.music_api;

import android.content.Context;

import com.edavtyan.materialplayer2.lib.file_storage.StringFileStorage;

public class MusicApiFileStorage extends StringFileStorage {
	public MusicApiFileStorage(Context context) {
		super(context, "artist_info");
	}
}
