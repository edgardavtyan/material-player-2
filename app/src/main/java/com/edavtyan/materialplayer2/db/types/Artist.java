package com.edavtyan.materialplayer2.db.types;

import lombok.Data;

@Data
public class Artist {
	private int id;
	private String title;
	private int albumsCount;
	private int tracksCount;
}
