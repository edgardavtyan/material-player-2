package com.edavtyan.materialplayer2.ui.now_playing_queue;

import com.edavtyan.materialplayer2.AppDIComponent;
import com.edavtyan.materialplayer2.lib.theme.ThemeableFragmentDIModule;
import com.edavtyan.materialplayer2.modular.model.ModelModulesDIModule;
import com.edavtyan.materialplayer2.ui.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(
		dependencies = AppDIComponent.class,
		modules = {
				NowPlayingQueueDIModule.class,
				ModelModulesDIModule.class,
				ThemeableFragmentDIModule.class})
public interface NowPlayingQueueDIComponent {
	void inject(NowPlayingQueueFragment activity);
}
