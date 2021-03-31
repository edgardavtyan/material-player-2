package com.edavtyan.materialplayer2;

import android.app.Application;

import com.edavtyan.materialplayer2.lib.theme.ThemeColors;
import com.edavtyan.materialplayer2.service.DaggerPlayerServiceComponent;
import com.edavtyan.materialplayer2.service.PlayerServiceComponent;

import javax.inject.Inject;

import lombok.Setter;

public class App extends Application {
	private @Setter PlayerServiceComponent playerServiceComponent;
	private @Setter AppDIComponent appComponent;

	@Inject ThemeColors colors;

	@SuppressWarnings("ConstantConditions")
	public PlayerServiceComponent getPlayerServiceComponent() {
		if (playerServiceComponent != null) {
			return playerServiceComponent;
		} else {
			return DaggerPlayerServiceComponent
					.builder()
					.appDIComponent(getAppComponent())
					.build();
		}
	}

	public AppDIComponent getAppComponent() {
		if (appComponent == null) {
			appComponent = DaggerAppDIComponent
					.builder()
					.appDIModule(new AppDIModule(this))
					.build();
		}

		return appComponent;
	}
}
