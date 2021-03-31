package com.edavtyan.materialplayer2.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer2.App;
import com.edavtyan.materialplayer2.lib.replaygain.ReplayGainManager;
import com.edavtyan.materialplayer2.notification.PlayerNotification;
import com.edavtyan.materialplayer2.notification.PlayerNotificationPresenter;
import com.edavtyan.materialplayer2.player.Player;
import com.edavtyan.materialplayer2.player.PlayerPrefs;
import com.edavtyan.materialplayer2.player.effects.amplifier.Amplifier;
import com.edavtyan.materialplayer2.player.effects.bassboost.BassBoost;
import com.edavtyan.materialplayer2.player.effects.equalizer.Equalizer;
import com.edavtyan.materialplayer2.player.effects.surround.Surround;
import com.edavtyan.materialplayer2.player.managers.AudioFocusManager;
import com.edavtyan.materialplayer2.player.managers.MediaSessionManager;
import com.edavtyan.materialplayer2.service.receivers.AudioBecomingNoisyReceiver;
import com.edavtyan.materialplayer2.service.receivers.CloseReceiver;
import com.edavtyan.materialplayer2.service.receivers.HeadphonesConnectedReceiver;
import com.edavtyan.materialplayer2.service.receivers.MediaButtonReceiver;
import com.edavtyan.materialplayer2.service.receivers.PlayPauseReceiver;
import com.edavtyan.materialplayer2.service.receivers.SkipToNextReceiver;
import com.edavtyan.materialplayer2.service.receivers.SkipToPreviousReceiver;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;

public class PlayerService extends Service {
	public static final String ACTION_PLAY_PAUSE = "action_playPause";
	public static final String ACTION_FAST_FORWARD = "action_fastForward";
	public static final String ACTION_REWIND = "action_rewind";
	public static final String ACTION_CLOSE = "action_close";

	public class PlayerBinder extends Binder {
		public PlayerService getService() {
			return PlayerService.this;
		}
	}

	@Inject @Named("standard") Equalizer standardEqualizer;
	@Inject @Named("opensl") Equalizer openslEqualizer;

	@Inject PlayerPrefs playerPrefs;
	@Inject @Getter Player player;
	@Inject @Getter Surround surround;
	@Inject @Getter BassBoost bassBoost;
	@Inject @Nullable Amplifier amplifier;
	@Inject AudioFocusManager audioFocusManager;
	@Inject MediaSessionManager mediaSessionManager;
	@Inject ReplayGainManager rgManager;
	@Inject PlayerNotification notification;
	@Inject PlayerNotificationPresenter presenter;

	@Inject AudioBecomingNoisyReceiver audioBecomingNoisyReceiver;
	@Inject CloseReceiver closeReceiver;
	@Inject HeadphonesConnectedReceiver headphonesConnectedReceiver;
	@Inject MediaButtonReceiver mediaButtonReceiver;
	@Inject PlayPauseReceiver playPauseReceiver;
	@Inject SkipToNextReceiver skipToNextReceiver;
	@Inject SkipToPreviousReceiver skipToPreviousReceiver;

	private @Getter Equalizer equalizer;

	@Override
	public IBinder onBind(Intent intent) {
		return new PlayerBinder();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		startForeground(1, notification.getNotification());
		return START_NOT_STICKY;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		((App) getApplicationContext()).getPlayerServiceComponent().inject(this);

		registerReceiver(playPauseReceiver, new IntentFilter(ACTION_PLAY_PAUSE));
		registerReceiver(skipToPreviousReceiver, new IntentFilter(ACTION_REWIND));
		registerReceiver(skipToNextReceiver, new IntentFilter(ACTION_FAST_FORWARD));
		registerReceiver(closeReceiver, new IntentFilter(ACTION_CLOSE));
		registerReceiver(audioBecomingNoisyReceiver, new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY));
		registerReceiver(mediaButtonReceiver, new IntentFilter(Intent.ACTION_MEDIA_BUTTON));
		registerReceiver(headphonesConnectedReceiver, new IntentFilter(Intent.ACTION_HEADSET_PLUG));

		audioFocusManager.requestFocus();
		mediaSessionManager.init();
		presenter.onCreate();

		player.addPlugin(rgManager);

		playerPrefs.addOnUseAdvancedEngineListener(this, this::onUseAdvancedEngineChanged);

		equalizer = playerPrefs.getUseAdvancedEngine() ? openslEqualizer : standardEqualizer;
	}

	private void onUseAdvancedEngineChanged(boolean isAdvanced) {
		equalizer = isAdvanced ? openslEqualizer : standardEqualizer;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		presenter.onDestroy();
		audioFocusManager.dropFocus();
		mediaSessionManager.close();
		playerPrefs.removeOnUseAdvancedEngineListener(this);
	}
}
