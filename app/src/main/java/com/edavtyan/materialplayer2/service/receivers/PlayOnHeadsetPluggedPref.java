package com.edavtyan.materialplayer2.service.receivers;

import android.content.Context;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer2.lib.prefs.BooleanPref;

public class PlayOnHeadsetPluggedPref extends BooleanPref {
	public PlayOnHeadsetPluggedPref(Context context, AdvancedSharedPrefs prefs) {
		super(context, prefs);
	}

	@Override
	protected int getKeyId() {
		return R.string.pref_play_on_headset_plug_key;
	}

	@Override
	protected int getDefaultValueId() {
		return R.bool.pref_play_on_headset_plug_default;
	}
}
