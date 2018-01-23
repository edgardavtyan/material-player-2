package com.edavtyan.materialplayer.screens.search.album;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.screens.FragmentScope;
import com.edavtyan.materialplayer.screens.lists.album_list.AlbumListFactory;
import com.edavtyan.materialplayer.modular.model.ModelModulesFactory;

import dagger.Component;

@FragmentScope
@Component(
		dependencies = AppComponent.class,
		modules = {
				SearchAlbumModule.class,
				AlbumListFactory.class,
				ModelModulesFactory.class})
public interface SearchAlbumComponent {
	void inject(SearchAlbumFragment fragment);
}
