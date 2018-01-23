package com.edavtyan.materialplayer.screens.now_playing;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.view.ContextThemeWrapper;

import com.edavtyan.materialplayer.screens.now_playing.NowPlayingModel.OnModelBoundListener;
import com.edavtyan.materialplayer.screens.now_playing.NowPlayingModel.OnNewTrackListener;
import com.edavtyan.materialplayer.screens.now_playing.NowPlayingModel.OnPlayPauseListener;
import com.edavtyan.materialplayer.player.Player;
import com.edavtyan.materialplayer.service.PlayerService;
import com.edavtyan.materialplayer.player.RepeatMode;
import com.edavtyan.materialplayer.player.ShuffleMode;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static com.ed.libsutils.assertj.assertions.Assertions.assertThat;
import static com.ed.libsutils.assertj.assertions.NoNpeAssert.assertThatNPENotThrown;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NowPlayingModelTest extends BaseTest {
	private NowPlayingModel model;
	private PlayerService.PlayerBinder binder;
	private Player player;
	private AlbumArtProvider albumArtProvider;

	@Override
	public void beforeEach() {
		super.beforeEach();

		player = mock(Player.class);

		context = mock(ContextThemeWrapper.class);
		PlayerService service = mock(PlayerService.class);
		when(service.getPlayer()).thenReturn(player);

		binder = mock(PlayerService.PlayerBinder.class);
		when(binder.getService()).thenReturn(service);

		albumArtProvider = mock(AlbumArtProvider.class);

		runOnUiThread(() -> {
			model = new NowPlayingModel(context, albumArtProvider);
			model.onServiceConnected(null, binder);
		});
	}

	@Test
	@SuppressWarnings("WrongConstant")
	public void bind_bindServiceWithCorrectIntent() {
		ArgumentCaptor<Intent> intentCaptor = ArgumentCaptor.forClass(Intent.class);

		NowPlayingModel model = new NowPlayingModel(context, albumArtProvider);
		model.bind();

		verify(context).bindService(intentCaptor.capture(), eq(model), eq(Context.BIND_AUTO_CREATE));
		assertThat(intentCaptor.getValue()).hasClass(PlayerService.class);
	}

	@Test
	public void unbind_unbindService() {
		model.bind();
		model.unbind();
		verify(context).unbindService(model);
	}

	@Test
	public void unbind_removePlayerListeners() {
		model.bind();
		model.unbind();
		verify(player).removeOnNewTrackListener(model);
		verify(player).removeOnPlayPauseListener(model);
	}

	@Test
	public void getRepeatMode_returnRepeatModeFromPlayer() {
		RepeatMode repeatMode = RepeatMode.REPEAT_ALL;
		when(player.getRepeatMode()).thenReturn(repeatMode);
		assertThat(model.getRepeatMode()).isSameAs(repeatMode);
	}

	@Test
	public void toggleRepeatMode_callService() {
		model.toggleRepeatMode();
		verify(player).toggleRepeatMode();
	}

	@Test
	public void getShuffleMode_returnShuffleModeFromPlayer() {
		ShuffleMode shuffleMode = ShuffleMode.ENABLED;
		when(player.getShuffleMode()).thenReturn(shuffleMode);
		assertThat(model.getShuffleMode()).isSameAs(shuffleMode);
	}

	@Test
	public void toggleShuffle() {
		model.toggleShuffleMode();
		verify(player).toggleShuffleMode();
	}

	@Test
	public void isPlaying_returnIsPlayingFromPlayer() {
		when(player.isPlaying()).thenReturn(true);
		assertThat(model.isPlaying()).isTrue();
	}

	@Test
	public void togglePlayPause_playPausePlayer() {
		model.playPause();
		verify(player).playPause();
	}

	@Test
	public void getTitle_returnTitleOfNowPlayingTrack() {
		Track track = new Track();
		track.setTitle("title");
		when(player.getCurrentTrack()).thenReturn(track);
		assertThat(model.getTitle()).isEqualTo("title");
	}

	@Test
	public void getArtist_returnArtistTitleOfNowPlayingTrack() {
		Track track = new Track();
		track.setArtistTitle("artist");
		when(player.getCurrentTrack()).thenReturn(track);
		assertThat(model.getArtist()).isEqualTo("artist");
	}

	@Test
	public void getAlbum_returnAlbumTitleOfNowPlayingTrack() {
		Track track = new Track();
		track.setAlbumTitle("album");
		when(player.getCurrentTrack()).thenReturn(track);
		assertThat(model.getAlbum()).isEqualTo("album");
	}

	@Test
	public void getDuration_returnDurationOfNowPlayingTrack() {
		Track track = new Track();
		track.setDuration(1234);
		when(player.getCurrentTrack()).thenReturn(track);
		assertThat(model.getDuration()).isEqualTo(1234);
	}

	@Test
	public void getPosition_returnPositionOfNowPlayingTrack() {
		when(player.getPosition()).thenReturn(5678L);
		assertThat(model.getPosition()).isEqualTo(5678);
	}

	@Test
	public void getTrackArt_returnArtFromArtProvider() {
		Track track = new Track();
		track.setAlbumId(123);
		when(player.getCurrentTrack()).thenReturn(track);

		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		when(albumArtProvider.load(track)).thenReturn(art);

		assertThat(model.getArt()).isSameAs(art);
	}

	@Test
	public void seek_setPlayerPosition() {
		model.seek(1357);
		verify(player).setPosition(1357);
	}

	@Test
	public void rewind_rewindPlayerViaService() {
		model.rewind();
		verify(player).skipToPrevious();
	}

	@Test
	public void fastForward_fastForwardPlayerViaService() {
		model.fastForward();
		verify(player).skipToNext();
	}

	@Test
	public void onServiceConnected_callOnModelConnectedListener() {
		OnModelBoundListener listener = mock(OnModelBoundListener.class);
		model.setOnModelBoundListener(listener);
		model.onServiceConnected(null, binder);
		verify(listener).onModelBound();
	}

	@Test
	public void onNewTrackListener_calledWhenOnNewTrackCalled() {
		OnNewTrackListener listener = mock(OnNewTrackListener.class);
		model.setOnNewTrackListener(listener);
		model.onNewTrack();
		verify(listener).onNewTrack();
	}

	@Test
	public void onNewTrackListener_notCalledIfNotSet() {
		assertThatNPENotThrown(model::onNewTrack);
	}

	@Test
	public void onPlayPauseListener_calledWhenOnPlayPauseCalled() {
		OnPlayPauseListener listener = mock(OnPlayPauseListener.class);
		model.setOnPlayPauseListener(listener);
		model.onPlayPause();
		verify(listener).onPlayPause();
	}

	@Test
	public void onPlayPauseListener_notCalledIfNotSet() {
		assertThatNPENotThrown(model::onPlayPause);
	}
}