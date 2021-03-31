package com.edavtyan.materialplayer2;

import android.content.Context;

import com.edavtyan.materialplayer2.db.DbDIModule;
import com.edavtyan.materialplayer2.db.MediaDB;
import com.edavtyan.materialplayer2.lib.album_art.AlbumArtDIModule;
import com.edavtyan.materialplayer2.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer2.lib.lyrics.LyricsDIModule;
import com.edavtyan.materialplayer2.lib.lyrics.LyricsProvider;
import com.edavtyan.materialplayer2.lib.music_api.MusicApi;
import com.edavtyan.materialplayer2.lib.music_api.MusicApiDIModule;
import com.edavtyan.materialplayer2.lib.playlist.models.PlaylistManager;
import com.edavtyan.materialplayer2.lib.playlist.models.PlaylistModelsDIModule;
import com.edavtyan.materialplayer2.lib.prefs.AdvancedGsonSharedPrefs;
import com.edavtyan.materialplayer2.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer2.lib.prefs.AdvancedSharedPrefsDIModule;
import com.edavtyan.materialplayer2.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer2.lib.transition.SharedTransitionDIModule;
import com.edavtyan.materialplayer2.lib.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer2.ui.Navigator;
import com.edavtyan.materialplayer2.ui.SdkFactory;
import com.edavtyan.materialplayer2.utils.PendingIntents;
import com.edavtyan.materialplayer2.utils.UtilsDIModule;
import com.edavtyan.materialplayer2.utils.WebClient;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		AppDIModule.class,
		AdvancedSharedPrefsDIModule.class,
		UtilsDIModule.class,
		MusicApiDIModule.class,
		LyricsDIModule.class,
		DbDIModule.class,
		AlbumArtDIModule.class,
		SharedTransitionDIModule.class,
		PlaylistModelsDIModule.class})
public interface AppDIComponent {
	Context context();
	SdkFactory sdkFactory();
	MusicApi musicAPi();
	LyricsProvider lyricsProvider();
	AdvancedSharedPrefs advancedPrefs();
	AdvancedGsonSharedPrefs advancedGsonPrefs();
	Navigator navigator();
	WebClient webClient();
	TestableBitmapFactory testableBitmapFactory();
	PendingIntents pendingIntents();
	MediaDB mediaDB();
	AlbumArtProvider albumArtProvider();
	Gson gson();
	SharedTransitionsManager sharedTransitionsManager();
	PlaylistManager playlistManager();
}
