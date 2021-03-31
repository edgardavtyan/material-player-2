package com.edavtyan.materialplayer2.ui.lists.playlist_list;

import com.edavtyan.materialplayer2.AppDIComponent;
import com.edavtyan.materialplayer2.lib.playlist.PlaylistDIModuleFragment;
import com.edavtyan.materialplayer2.lib.theme.ThemeableFragmentDIModule;
import com.edavtyan.materialplayer2.modular.model.ModelModulesDIModule;
import com.edavtyan.materialplayer2.ui.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(
		dependencies = AppDIComponent.class,
		modules = {
				PlaylistListDIModule.class,
				PlaylistDIModuleFragment.class,
				ModelModulesDIModule.class,
				ThemeableFragmentDIModule.class})
public interface PlaylistListDIComponent {
	void inject(PlaylistListFragment fragment);
}
