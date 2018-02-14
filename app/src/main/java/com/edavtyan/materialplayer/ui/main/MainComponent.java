package com.edavtyan.materialplayer.ui.main;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.ui.ActivityScope;
import com.edavtyan.materialplayer.lib.theme.ThemeableActivityFactory;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class,
		   modules = {
				   MainModule.class,
				   ActivityModulesFactory.class,
				   ThemeableActivityFactory.class})
public interface MainComponent {
	void inject(MainActivity activity);
}