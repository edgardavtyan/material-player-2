package com.edavtyan.materialplayer2.ui.search.artist;

import com.edavtyan.materialplayer2.AppDIComponent;
import com.edavtyan.materialplayer2.lib.playlist.PlaylistDIModuleFragment;
import com.edavtyan.materialplayer2.lib.theme.ThemeableFragmentDIModule;
import com.edavtyan.materialplayer2.ui.FragmentScope;
import com.edavtyan.materialplayer2.ui.lists.artist_list.ArtistListDIModule;
import com.edavtyan.materialplayer2.modular.model.ModelModulesDIModule;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppDIComponent.class,
		   modules = {
				   SearchArtistDIModule.class,
				   ArtistListDIModule.class,
				   ModelModulesDIModule.class,
				   ThemeableFragmentDIModule.class,
				   PlaylistDIModuleFragment.class})
public interface SearchArtistDIComponent {
	void inject(SearchArtistFragment fragment);
}