package com.edavtyan.materialplayer.components.audioeffects.views;

import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class TitledSeekbarTest extends BaseTest {
	private TitledSeekbar titledSeekbar;
	private TextView titleView;
	private SeekBar seekbarView;

	@Override
	public void beforeEach() {
		super.beforeEach();
		titledSeekbar = (TitledSeekbar) View.inflate(context, R.layout.test_titled_seekbar, null);
		titleView = titledSeekbar.findView(R.id.title);
		seekbarView = titledSeekbar.findView(R.id.seekbar);
	}

	@Test
	public void set_inner_views_attributes() {
		assertThat(titleView.getText()).isEqualTo("Title");
		assertThat(seekbarView.getMax()).isEqualTo(500);
		assertThat(seekbarView.getProgress()).isEqualTo(150);
	}

	@Test
	public void set_seekbar_max() {
		titledSeekbar.setMax(100);
		assertThat(seekbarView.getMax()).isEqualTo(100);
	}

	@Test
	public void set_seekbar_progress() {
		titledSeekbar.setProgress(10);
		assertThat(seekbarView.getProgress()).isEqualTo(10);
	}

	@Test
	public void not_throw_npe_if_listener_not_set_when_progress_changed() {
		try {
			titledSeekbar.onProgressChanged(seekbarView, 0, true);
		} catch (NullPointerException e) {
			fail("Expected to not throw NPE here", e);
		}
	}

	@Test
	public void not_throw_npe_if_listener_not_set_when_seekbar_stops_tracking() {
		try {
			titledSeekbar.onStopTrackingTouch(seekbarView);
		} catch (NullPointerException e) {
			fail("Expected to not throw NPE here", e);
		}
	}

	@Test
	public void raise_listener_when_progress_changed_by_user() {
		TitledSeekbar.OnProgressChangedListener listener = createAndSetListener();
		titledSeekbar.onProgressChanged(seekbarView, 10, true);
		verify(listener).onProgressChange(R.id.test_titled_seekbar, 10);
	}

	@Test
	public void not_raise_listener_when_progress_changed_not_by_user() {
		TitledSeekbar.OnProgressChangedListener listener = createAndSetListener();
		titledSeekbar.onProgressChanged(seekbarView, 20, false);
		verify(listener, never()).onProgressChange(R.id.test_titled_seekbar, 20);
	}

	@Test
	public void raise_listener_on_stop_tracking() {
		TitledSeekbar.OnProgressChangedListener listener = createAndSetListener();
		titledSeekbar.onStopTrackingTouch(seekbarView);
		verify(listener).onStopTrackingTouch(R.id.test_titled_seekbar);
	}

	@Test
	public void not_raise_listener_on_start_tracking() {
		TitledSeekbar.OnProgressChangedListener listener = createAndSetListener();
		titledSeekbar.onStartTrackingTouch(seekbarView);
		verifyZeroInteractions(listener);
	}

	private TitledSeekbar.OnProgressChangedListener createAndSetListener() {
		TitledSeekbar.OnProgressChangedListener listener = mock(TitledSeekbar.OnProgressChangedListener.class);
		titledSeekbar.setOnProgressChangedListener(listener);
		return listener;
	}
}