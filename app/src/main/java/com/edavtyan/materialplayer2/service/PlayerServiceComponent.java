package com.edavtyan.materialplayer2.service;

import com.edavtyan.materialplayer2.AppDIComponent;
import com.edavtyan.materialplayer2.lib.replaygain.ReplayGainDIModule;
import com.edavtyan.materialplayer2.notification.PlayerNotificationCompatDIModule;
import com.edavtyan.materialplayer2.notification.PlayerNotificationDIModule;
import com.edavtyan.materialplayer2.notification.PlayerNotificationNougatDIModule;
import com.edavtyan.materialplayer2.player.PlayerDIModule;
import com.edavtyan.materialplayer2.player.effects.amplifier.AmplifierFactory;
import com.edavtyan.materialplayer2.player.effects.bassboost.BassBoostFactory;
import com.edavtyan.materialplayer2.player.effects.equalizer.EqualizerFactory;
import com.edavtyan.materialplayer2.player.effects.surround.SurroundFactory;
import com.edavtyan.materialplayer2.service.receivers.ReceiversFactory;

import dagger.Component;

@PlayerServiceScope
@Component(
		dependencies = AppDIComponent.class,
		modules = {
		PlayerDIModule.class,
		EqualizerFactory.class,
		AmplifierFactory.class,
		BassBoostFactory.class,
		SurroundFactory.class,
		ReceiversFactory.class,
		ReplayGainDIModule.class,
		PlayerNotificationCompatDIModule.class,
		PlayerNotificationNougatDIModule.class,
		PlayerNotificationDIModule.class})
public interface PlayerServiceComponent {
	void inject(PlayerService service);
}
