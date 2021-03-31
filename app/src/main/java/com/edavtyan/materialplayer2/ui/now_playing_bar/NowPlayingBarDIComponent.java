package com.edavtyan.materialplayer2.ui.now_playing_bar;

import com.edavtyan.materialplayer2.AppDIComponent;
import com.edavtyan.materialplayer2.ui.FragmentScope;
import com.edavtyan.materialplayer2.lib.theme.ThemeableFragmentDIModule;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppDIComponent.class,
		   modules = {
				   NowPlayingBarDIModule.class,
				   ThemeableFragmentDIModule.class})
public interface NowPlayingBarDIComponent {
	void inject(NowPlayingBarFragment fragment);
}
