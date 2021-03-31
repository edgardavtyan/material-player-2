package com.edavtyan.materialplayer2.lib.music_api;

public class InfoNotFoundException extends Exception {
	public InfoNotFoundException(String info) {
		super(String.format("Info about '%s' not found', info", info));
	}
}
