package com.edavtyan.materialplayer2.db;

public class CursorIsNullException extends IllegalStateException {
	public CursorIsNullException() {
		super("Cursor returned by content provider is null");
	}
}
