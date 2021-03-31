package com.edavtyan.materialplayer2.ui.lists.artist_list;

import com.edavtyan.materialplayer2.AppDIComponent;
import com.edavtyan.materialplayer2.lib.theme.ThemeableFragmentDIModule;
import com.edavtyan.materialplayer2.modular.model.ModelModulesDIModule;
import com.edavtyan.materialplayer2.ui.FragmentScope;
import com.edavtyan.materialplayer2.lib.playlist.PlaylistDIModuleFragment;

import dagger.Component;

@FragmentScope
@Component(
		dependencies = AppDIComponent.class,
		modules = {
				ArtistListDIModule.class,
				ModelModulesDIModule.class,
				PlaylistDIModuleFragment.class,
				ThemeableFragmentDIModule.class})
public interface ArtistListDIComponent {
	void inject(ArtistListFragment fragment);
}
