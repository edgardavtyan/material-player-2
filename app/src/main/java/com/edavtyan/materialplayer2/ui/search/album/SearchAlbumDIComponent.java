package com.edavtyan.materialplayer2.ui.search.album;

import com.edavtyan.materialplayer2.AppDIComponent;
import com.edavtyan.materialplayer2.lib.playlist.PlaylistDIModuleFragment;
import com.edavtyan.materialplayer2.lib.theme.ThemeableFragmentDIModule;
import com.edavtyan.materialplayer2.modular.model.ModelModulesDIModule;
import com.edavtyan.materialplayer2.ui.FragmentScope;
import com.edavtyan.materialplayer2.ui.lists.album_list.AlbumListDIModule;

import dagger.Component;

@FragmentScope
@Component(
		dependencies = AppDIComponent.class,
		modules = {
				SearchAlbumDIModule.class,
				AlbumListDIModule.class,
				ModelModulesDIModule.class,
				PlaylistDIModuleFragment.class,
				ThemeableFragmentDIModule.class})
public interface SearchAlbumDIComponent {
	void inject(SearchAlbumFragment fragment);
}
