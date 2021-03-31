package com.edavtyan.materialplayer2.ui.search.tracks;

import com.edavtyan.materialplayer2.AppDIComponent;
import com.edavtyan.materialplayer2.lib.playlist.PlaylistDIModuleFragment;
import com.edavtyan.materialplayer2.lib.theme.ThemeableFragmentDIModule;
import com.edavtyan.materialplayer2.ui.FragmentScope;
import com.edavtyan.materialplayer2.ui.lists.track_list.TrackListDIModule;
import com.edavtyan.materialplayer2.modular.model.ModelModulesDIModule;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppDIComponent.class,
		   modules = {
				   SearchTrackDIModule.class,
				   TrackListDIModule.class,
				   ModelModulesDIModule.class,
				   ThemeableFragmentDIModule.class,
				   PlaylistDIModuleFragment.class})
public interface SearchTrackDIComponent {
	void inject(SearchTrackFragment fragment);
}
