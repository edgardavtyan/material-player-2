package com.edavtyan.materialplayer2.lib.replaygain;

import android.content.Context;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer2.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer2.player.effects.amplifier.Amplifier;
import com.edavtyan.materialplayer2.service.PlayerServiceScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ReplayGainDIModule {
	@Provides
	@PlayerServiceScope
	public ReplayGainManager provideReplayGainManager(
			@Nullable Amplifier amplifier,
			ReplayGainReader rgReader,
			ReplayGainPrefs prefs) {
		return new ReplayGainManager(amplifier, rgReader, prefs);
	}

	@Provides
	@PlayerServiceScope
	public ReplayGainReader provideReplayGainReader() {
		return new ReplayGainReader();
	}

	@Provides
	@PlayerServiceScope
	public ReplayGainPrefs provideReplayGainPrefs(Context context, AdvancedSharedPrefs prefs) {
		return new ReplayGainPrefs(context, prefs);
	}
}
