package com.edavtyan.materialplayer2.ui.lists.track_list;

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
				TrackListDIModule.class,
				ModelModulesDIModule.class,
				ThemeableFragmentDIModule.class,
				PlaylistDIModuleFragment.class})
public interface TrackListDIComponent {
	void inject(TrackListFragment fragment);
}
