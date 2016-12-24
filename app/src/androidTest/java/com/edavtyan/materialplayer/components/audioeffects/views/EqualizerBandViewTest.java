package com.edavtyan.materialplayer.components.audioeffects.views;

import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.audioeffects.views.EqualizerBandView.OnBandChangedListener;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EqualizerBandViewTest extends BaseTest {

	private EqualizerBandView equalizerView;
	private DoubleSeekbar bandView;
	private TextView gainView;
	private TextView frequencyView;

	@Override public void beforeEach() {
		super.beforeEach();
		equalizerView = new EqualizerBandView(context, null);
		bandView = (DoubleSeekbar) equalizerView.findViewById(R.id.band);
		gainView = (TextView) equalizerView.findViewById(R.id.gain);
		frequencyView = (TextView) equalizerView.findViewById(R.id.frequency);
	}

	@Test public void setGainLimit_setSeekbarMax() {
		equalizerView.setGainLimit(30);
		assertThat(bandView.getMax()).isEqualTo(30);
	}

	@Test public void setGain_setBandProgress() {
		equalizerView.setGain(25);
		assertThat(bandView.getProgress()).isEqualTo(25);
	}

	@Test public void setGain_positiveGain_setGainTextWithPlusSign() {
		equalizerView.setGain(25);
		assertThat(gainView.getText()).isEqualTo("+25 dB");
	}

	@Test public void setGain_zero_setGainTextWithoutSign() {
		equalizerView.setGain(0);
		assertThat(gainView.getText()).isEqualTo("0 dB");
	}

	@Test public void setGain_negativeGain_setGaintWithMinusSign() {
		equalizerView.setGain(-20);
		assertThat(gainView.getText()).isEqualTo("-20 dB");
	}

	@Test public void setFrequency_frequencyLessThatThousand_setFrequencyViewTextAsIsWithHertz() {
		equalizerView.setFrequency(999);
		assertThat(frequencyView.getText()).isEqualTo("999 Hz");
	}

	@Test public void setFrequency_frequencyIsThousandOrBigger_setFrequencyAsKiloHertzRoundedToFirstDecimal() {
		equalizerView.setFrequency(14789);
		assertThat(frequencyView.getText()).isEqualTo("14.8 kHz");
	}

	@Test public void setFrequency_frequencyIsDivisibleByThousand_setFrequencyWithoutDecimalPoint() {
		equalizerView.setFrequency(16000);
		assertThat(frequencyView.getText()).isEqualTo("16 kHz");
	}

	@Test public void onProgressChanged_progressIsPositive_setGainViewTextWithPlusSign() {
		equalizerView.onProgressChanged(45);
		assertThat(gainView.getText()).isEqualTo("+45 dB");
	}

	@Test public void onProgressChanged_progressIsZero_setGainViewTextWithoutSign() {
		equalizerView.onProgressChanged(0);
		assertThat(gainView.getText()).isEqualTo("0 dB");
	}

	@Test public void onProgressChanged_progressIsNegative_setGainViewTextWithMinusSign() {
		equalizerView.onProgressChanged(-30);
		assertThat(gainView.getText()).isEqualTo("-30 dB");
	}

	@Test public void onProgressChanged_onBandChangedListenerSet_callOnBandChangedWithEqualizerViewAndProgress() {
		OnBandChangedListener listener = mock(OnBandChangedListener.class);
		equalizerView.setOnBandChangedListener(listener);
		equalizerView.onProgressChanged(20);
		verify(listener).onBandChanged(equalizerView);
	}

	@Test public void onProgressChanged_onBandChangedListenerNotSet_notThrowException() {
		try {
			equalizerView.onProgressChanged(0);
		} catch (NullPointerException e) {
			fail("Expected OnProgressChanged to not throw NullPointerException"
				 + "if OnBandChangedListener is not set");
		}
	}

	@Test public void onStopTrackingTouch_onBandChangedListenerSet_callOnBandStopTracking() {
		OnBandChangedListener listener = mock(OnBandChangedListener.class);
		equalizerView.setOnBandChangedListener(listener);
		equalizerView.onStopTrackingTouch();
		verify(listener).onBandStopTracking();
	}

	@Test public void onStopTrackingTouch_onBandChangedListenerNotSet_notThrowException() {
		try {
			equalizerView.onStopTrackingTouch();
		} catch (NullPointerException e) {
			fail("Expected onStopTrackingTouch to not throw NullPointerException"
				 + "if onBandChangedListener is not set");
		}
	}
}
