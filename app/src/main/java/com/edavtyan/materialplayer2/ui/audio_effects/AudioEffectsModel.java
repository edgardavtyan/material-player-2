package com.edavtyan.materialplayer2.ui.audio_effects;

import com.edavtyan.materialplayer2.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer2.player.effects.bassboost.BassBoost;
import com.edavtyan.materialplayer2.player.effects.equalizer.Equalizer;
import com.edavtyan.materialplayer2.player.effects.surround.Surround;

public class AudioEffectsModel {
	private final ModelServiceModule serviceModule;

	public AudioEffectsModel(ModelServiceModule serviceModule) {
		this.serviceModule = serviceModule;
	}

	public void setOnServiceConnectedListener(ModelServiceModule.OnServiceConnectedListener listener) {
		serviceModule.setOnServiceConnectedListener(listener);
	}

	public void init() {
		serviceModule.bind();
	}

	public void close() {
		serviceModule.unbind();
	}

	public boolean isConnected() {
		return serviceModule.getService() != null;
	}

	public Equalizer getEqualizer() {
		return serviceModule.getService().getEqualizer();
	}

	public BassBoost getBassBoost() {
		return serviceModule.getService().getBassBoost();
	}

	public Surround getSurround() {
		return serviceModule.getService().getSurround();
	}

	public boolean isSupported() {
		return getEqualizer().isSupported() &&
			   getBassBoost().isSupported() &&
			   getSurround().isSupported();
	}
}
