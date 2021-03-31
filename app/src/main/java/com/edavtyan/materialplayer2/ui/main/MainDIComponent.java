package com.edavtyan.materialplayer2.ui.main;

import com.edavtyan.materialplayer2.AppDIComponent;
import com.edavtyan.materialplayer2.lib.theme.ThemeableActivityDIModule;
import com.edavtyan.materialplayer2.modular.activity.ActivityModulesDIModule;
import com.edavtyan.materialplayer2.ui.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppDIComponent.class,
		   modules = {
				   MainDIModule.class,
				   ActivityModulesDIModule.class,
				   ThemeableActivityDIModule.class})
public interface MainDIComponent {
	void inject(MainActivity activity);
}
