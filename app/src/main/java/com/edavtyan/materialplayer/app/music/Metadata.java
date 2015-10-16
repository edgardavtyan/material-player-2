package com.edavtyan.materialplayer.app.music;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.edavtyan.materialplayer.app.utils.AlbumArtUtils;

import java.io.File;

import lombok.Data;

@Data
public class Metadata {
    private static final Uri TRACKS_URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    private static final String[] TRACKS_PROJECTION = new String[] {
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
    };

    private static final int COLUMN_ALBUM_ID = 1;
    private static final int COLUMN_TITLE = 2;
    private static final int COLUMN_DURATION = 3;
    private static final int COLUMN_ARTIST = 4;
    private static final int COLUMN_ALBUM = 5;

    private static final String COLUMN_NAME_ID = MediaStore.Audio.Media._ID;
    private static final String COLUMN_NAME_ALBUM_ID = MediaStore.Audio.Media.ALBUM_ID;
    private static final String COLUMN_NAME_TITLE = MediaStore.Audio.Media.TITLE;
    private static final String COLUMN_NAME_DURATION = MediaStore.Audio.Media.DURATION;
    private static final String COLUMN_NAME_ARTIST = MediaStore.Audio.Media.ARTIST;
    private static final String COLUMN_NAME_ALBUM = MediaStore.Audio.Media.ALBUM;


    private int trackId;
    private int albumId;
    private long duration;
    private String trackTitle;
    private String artistTitle;
    private String albumTitle;
    private File artFile;


    public static Metadata fromId(int id, Context context) {
        Metadata metadata = new Metadata();
        Cursor cursor = null;
        try {
            metadata.setTrackId(id);

            cursor = context.getContentResolver().query(
                    TRACKS_URI, TRACKS_PROJECTION,
                    COLUMN_NAME_ID + "=" + id,
                    null, null);
            cursor.moveToFirst();
            metadata.setTrackTitle(cursor.getString(COLUMN_TITLE));
            metadata.setDuration(cursor.getLong(COLUMN_DURATION));
            metadata.setAlbumId(cursor.getInt(COLUMN_ALBUM_ID));
            metadata.setArtistTitle(cursor.getString(COLUMN_ARTIST));
            metadata.setAlbumTitle(cursor.getString(COLUMN_ALBUM));
            metadata.setArtFile(AlbumArtUtils.getArtFileFromId(metadata.getAlbumId(), context));
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return metadata;
    }
}