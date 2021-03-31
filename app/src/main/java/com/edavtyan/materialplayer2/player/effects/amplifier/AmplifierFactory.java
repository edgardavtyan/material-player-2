package com.edavtyan.materialplayer2.player.effects.amplifier;

import android.annotation.TargetApi;
import android.media.audiofx.LoudnessEnhancer;
import android.os.Build;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer2.service.PlayerServiceScope;
import com.edavtyan.materialplayer2.lib.prefs.AdvancedSharedPrefs;

import dagger.Module;
import dagger.Provides;

@Module
@TargetApi(Build.VERSION_CODES.KITKAT)
public class AmplifierFactory {
	@Provides
	@PlayerServiceScope
	@Nullable
	public Amplifier provideAmplifier(AmplifierPrefs prefs, int sessionId) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			return new StandardAmplifier(new LoudnessEnhancer(sessionId), prefs);
		} else {
			return null;
		}
	}

	@Provides
	@PlayerServiceScope
	public AmplifierPrefs provideAmplifierPrefs(AdvancedSharedPrefs prefs) {
		return new AmplifierPrefs(prefs);
	}
}
