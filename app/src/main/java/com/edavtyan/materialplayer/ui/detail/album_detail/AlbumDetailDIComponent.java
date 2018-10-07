package com.edavtyan.materialplayer.ui.detail.album_detail;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.lib.theme.ThemeableActivityDIModule;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesDIModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesDIModule;
import com.edavtyan.materialplayer.ui.ActivityScope;
import com.edavtyan.materialplayer.ui.detail.lib.ParallaxHeaderDIModule;
import com.edavtyan.materialplayer.ui.lists.playlist_list.PlaylistDialogsActivityDIModule;

import dagger.Component;

@ActivityScope
@Component(
		dependencies = {AppDIComponent.class},
		modules = {
				AlbumDetailDIModule.class,
				ParallaxHeaderDIModule.class,
				ActivityModulesDIModule.class,
				ThemeableActivityDIModule.class,
				ModelModulesDIModule.class,
				PlaylistDialogsActivityDIModule.class})
public interface AlbumDetailDIComponent {
	void inject(AlbumDetailActivity activity);
}
