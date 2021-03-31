package com.edavtyan.materialplayer2.ui.lists.lib;

public class ServiceNotConnectedException extends IllegalStateException {
	public ServiceNotConnectedException() {
		super("PlayerService is not connected yet");
	}
}
