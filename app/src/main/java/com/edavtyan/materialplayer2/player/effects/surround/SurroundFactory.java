package com.edavtyan.materialplayer2.player.effects.surround;

import android.media.audiofx.Virtualizer;

import com.edavtyan.materialplayer2.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer2.service.PlayerServiceScope;

import dagger.Module;
import dagger.Provides;

@Module
public class SurroundFactory {
	@Provides
	@PlayerServiceScope
	public Surround provideSurround(SurroundPrefs prefs, int sessionId) {
		try {
			return new StandardSurround(
					new Virtualizer(0, sessionId),
					prefs);
		} catch (RuntimeException e) {
			return new StandardSurround(null, prefs);
		}
	}

	@Provides
	@PlayerServiceScope
	public SurroundPrefs provideSurroundPrefs(AdvancedSharedPrefs prefs) {
		return new SurroundPrefs(prefs);
	}
}
