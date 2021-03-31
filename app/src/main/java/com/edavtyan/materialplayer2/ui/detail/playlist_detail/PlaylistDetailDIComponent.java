package com.edavtyan.materialplayer2.ui.detail.playlist_detail;

import com.edavtyan.materialplayer2.AppDIComponent;
import com.edavtyan.materialplayer2.lib.theme.ThemeableActivityDIModule;
import com.edavtyan.materialplayer2.modular.activity.ActivityModulesDIModule;
import com.edavtyan.materialplayer2.modular.model.ModelModulesDIModule;
import com.edavtyan.materialplayer2.ui.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
		dependencies = AppDIComponent.class,
		modules = {PlaylistDetailDIModule.class,
				   ActivityModulesDIModule.class,
				   ThemeableActivityDIModule.class,
				   ModelModulesDIModule.class})
public interface PlaylistDetailDIComponent {
	void inject(PlaylistDetailActivity activity);
}
