package com.edavtyan.materialplayer.screens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.screens.audio_effects.AudioEffectsActivity;
import com.edavtyan.materialplayer.screens.detail.album_detail.AlbumDetailActivityNormal;
import com.edavtyan.materialplayer.screens.detail.album_detail.AlbumDetailView;
import com.edavtyan.materialplayer.screens.detail.artist_detail.ArtistDetailActivityNormal;
import com.edavtyan.materialplayer.screens.detail.artist_detail.ArtistDetailView;
import com.edavtyan.materialplayer.screens.detail.lib.CompactDetailPref;
import com.edavtyan.materialplayer.screens.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.screens.now_playing_queue.NowPlayingQueueActivity;
import com.edavtyan.materialplayer.screens.prefs.PrefActivity;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static com.ed.libsutils.assertj.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NavigatorTest extends BaseTest {
	private AppCompatActivity activity;
	private Navigator navigator;
	private ArgumentCaptor<Intent> intentCaptor;

	@Override
	public void beforeEach() {
		super.beforeEach();
		CompactDetailPref compactDetailPref = mock(CompactDetailPref.class);
		activity = mock(AppCompatActivity.class);
		navigator = new Navigator(activity);
		intentCaptor = ArgumentCaptor.forClass(Intent.class);
	}

	@Test
	public void gotoArtistDetail_startActivityWithCorrectParameters() {
		navigator.gotoArtistDetailNormal("title");

		verify(activity).startActivity(intentCaptor.capture());

		assertThat(intentCaptor.getValue())
				.hasClass(ArtistDetailActivityNormal.class)
				.hasExtraString(ArtistDetailView.EXTRA_ARTIST_TITLE, "title");
	}

	@Test
	public void gotoAlbumDetail_startAlbumDetailActivityWithCorrectParameters() {
		navigator.gotoAlbumDetailNormal(7);

		verify(activity).startActivity(intentCaptor.capture());

		assertThat(intentCaptor.getValue())
				.hasClass(AlbumDetailActivityNormal.class)
				.hasExtraInt(AlbumDetailView.EXTRA_ALBUM_ID, 7);
	}

	@Test
	public void gotoNowPlaying_startNowPlayingActivityWithCorrectParameters() {
		navigator.gotoNowPlaying();

		verify(activity).startActivity(intentCaptor.capture());

		assertThat(intentCaptor.getValue()).hasClass(NowPlayingActivity.class);
	}

	@Test
	public void gotoNowPlayingQueue_startPlaylistActivity() {
		navigator.gotoNowPlayingQueue(activity);

		verify(activity).startActivity(intentCaptor.capture());

		assertThat(intentCaptor.getValue()).hasClass(NowPlayingQueueActivity.class);
	}

	@Test
	public void gotoAudioEffects_startAudioEffectsActivity() {
		navigator.gotoAudioEffects();
		verify(activity).startActivity(intentCaptor.capture());
		assertThat(intentCaptor.getValue()).hasClass(AudioEffectsActivity.class);
	}

	@Test
	public void gotoSetting_startSettingActivity() {
		navigator.gotoSettings();
		verify(activity).startActivity(intentCaptor.capture());
		assertThat(intentCaptor.getValue()).hasClass(PrefActivity.class);
	}
}