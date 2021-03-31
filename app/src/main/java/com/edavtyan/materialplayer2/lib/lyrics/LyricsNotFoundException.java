package com.edavtyan.materialplayer2.lib.lyrics;

public class LyricsNotFoundException extends Exception {
	public LyricsNotFoundException(String artist, String track) {
		super("Lyrics for " + artist + ":" + track + " not found");
	}
}
