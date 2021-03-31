package com.edavtyan.materialplayer2.ui.search;

import com.edavtyan.materialplayer2.AppDIComponent;
import com.edavtyan.materialplayer2.modular.activity.ActivityModulesDIModule;
import com.edavtyan.materialplayer2.ui.ActivityScope;
import com.edavtyan.materialplayer2.lib.theme.ThemeableActivityDIModule;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppDIComponent.class,
		   modules = {SearchDIModule.class,
					  ActivityModulesDIModule.class,
					  ThemeableActivityDIModule.class})
public interface SearchDIComponent {
	void inject(SearchActivity activity);
}
