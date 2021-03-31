package com.edavtyan.materialplayer2.ui.detail.artist_detail;

import com.edavtyan.materialplayer2.AppDIComponent;
import com.edavtyan.materialplayer2.lib.playlist.PlaylistDIModuleActivity;
import com.edavtyan.materialplayer2.modular.activity.ActivityModulesDIModule;
import com.edavtyan.materialplayer2.ui.ActivityScope;
import com.edavtyan.materialplayer2.ui.detail.lib.ParallaxHeaderDIModule;
import com.edavtyan.materialplayer2.lib.theme.ThemeableActivityDIModule;
import com.edavtyan.materialplayer2.modular.model.ModelModulesDIModule;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppDIComponent.class,
		   modules = {
				   ArtistDetailDIModule.class,
				   ActivityModulesDIModule.class,
				   ThemeableActivityDIModule.class,
				   ParallaxHeaderDIModule.class,
				   ModelModulesDIModule.class,
				   PlaylistDIModuleActivity.class})
public interface ArtistDetailDIComponent {
	void inject(ArtistDetailActivity activity);
}
