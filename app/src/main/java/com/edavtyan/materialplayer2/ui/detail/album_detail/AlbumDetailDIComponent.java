package com.edavtyan.materialplayer2.ui.detail.album_detail;

import com.edavtyan.materialplayer2.AppDIComponent;
import com.edavtyan.materialplayer2.lib.playlist.PlaylistDIModuleActivity;
import com.edavtyan.materialplayer2.lib.theme.ThemeableActivityDIModule;
import com.edavtyan.materialplayer2.modular.activity.ActivityModulesDIModule;
import com.edavtyan.materialplayer2.modular.model.ModelModulesDIModule;
import com.edavtyan.materialplayer2.ui.ActivityScope;
import com.edavtyan.materialplayer2.ui.detail.lib.ParallaxHeaderDIModule;

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
				PlaylistDIModuleActivity.class})
public interface AlbumDetailDIComponent {
	void inject(AlbumDetailActivity activity);
}
