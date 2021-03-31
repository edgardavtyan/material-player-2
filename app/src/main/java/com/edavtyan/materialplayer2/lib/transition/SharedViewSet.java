package com.edavtyan.materialplayer2.lib.transition;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.ed.libsutils.utils.ViewUtils;
import com.ed.libsutils.utils.WindowUtils;

import lombok.Getter;

import static com.edavtyan.materialplayer2.lib.transition.SharedTransitionsManager.PARAM_HEIGHT;
import static com.edavtyan.materialplayer2.lib.transition.SharedTransitionsManager.PARAM_WIDTH;
import static com.edavtyan.materialplayer2.lib.transition.SharedTransitionsManager.PARAM_X;
import static com.edavtyan.materialplayer2.lib.transition.SharedTransitionsManager.PARAM_Y;

public class SharedViewSet {
	private @Getter final String transitionName;
	private @Getter final TransitionType transitionType;

	private final View normalView;
	private final View enterPortraitView;
	private final View enterLandscapeView;
	private final View exitLandscapeView;
	private View exitPortraitView;
	private final int enterDuration;
	private final int exitDuration;
	private final int exitDelay;

	public static SharedViewSet translating(String transitionName, View normalView, View sharedView) {
		return new SharedViewSet(transitionName, normalView, sharedView, TransitionType.TRANSLATE);
	}

	private SharedViewSet(
			String transitionName,
			View normalView,
			View sharedView,
			TransitionType transitionType) {
		this.transitionName = transitionName;
		this.normalView = normalView;
		this.transitionType = transitionType;
		enterPortraitView = sharedView;
		enterLandscapeView = sharedView;
		exitPortraitView = sharedView;
		exitLandscapeView = sharedView;
		enterDuration = 400;
		exitDuration = 400;
		exitDelay = 0;
	}

	public View getEnterView(Activity activity) {
		return WindowUtils.isPortrait(activity) ? enterPortraitView : enterLandscapeView;
	}

	public SharedViewSet exitPortraitView(View view) {
		exitPortraitView = view;
		return this;
	}

	public TransitionData buildEnterData(Activity activity) {
		return WindowUtils.isPortrait(activity)
				? buildEnterData(activity.getIntent(), enterPortraitView)
				: buildEnterData(activity.getIntent(), enterLandscapeView);
	}

	public TransitionData buildExitData(Activity activity) {
		return WindowUtils.isPortrait(activity)
				? buildExitData(activity.getIntent(), exitPortraitView)
				: buildExitData(activity.getIntent(), exitLandscapeView);
	}

	private TransitionData buildEnterData(Intent intent, View sharedView) {
		float intentX = intent.getFloatExtra(transitionName + PARAM_X, 0);
		float intentY = intent.getFloatExtra(transitionName + PARAM_Y, 0);
		float intentWidth = intent.getIntExtra(transitionName + PARAM_WIDTH, 0);
		float intentHeight = intent.getIntExtra(transitionName + PARAM_HEIGHT, 0);
		int[] sharedViewLocation = ViewUtils.getLocationOnScreen(sharedView);
		int[] normalViewLocation = ViewUtils.getLocationOnScreen(normalView);

		TransitionData data = new TransitionData();
		data.setStartXDelta(intentX - sharedViewLocation[0]);
		data.setStartYDelta(intentY - sharedViewLocation[1]);
		data.setStartScaleX(intentWidth / normalView.getWidth());
		data.setStartScaleY(intentHeight / normalView.getHeight());
		data.setEndXDelta(normalViewLocation[0] - sharedViewLocation[0]);
		data.setEndYDelta(normalViewLocation[1] - sharedViewLocation[1]);
		data.setSharedView(sharedView);
		data.setNormalView(normalView);
		data.setDuration(enterDuration);

		return data;
	}

	private TransitionData buildExitData(Intent intent, View sharedView) {
		float intentX = intent.getFloatExtra(transitionName + PARAM_X, 0);
		float intentY = intent.getFloatExtra(transitionName + PARAM_Y, 0);
		float intentWidth = intent.getIntExtra(transitionName + PARAM_WIDTH, 0);
		float intentHeight = intent.getIntExtra(transitionName + PARAM_HEIGHT, 0);

		int[] normalViewLocation = ViewUtils.getLocationOnScreen(normalView);
		int[] sharedViewLocation = ViewUtils.getLocationOnScreen(sharedView);

		TransitionData data = new TransitionData();
		data.setStartXDelta(normalViewLocation[0] - sharedViewLocation[0]);
		data.setStartYDelta(normalViewLocation[1] - sharedViewLocation[1]);
		data.setStartScaleX(intentWidth / normalView.getWidth());
		data.setStartScaleY(intentHeight / normalView.getHeight());
		data.setEndXDelta(intentX - sharedViewLocation[0]);
		data.setEndYDelta(intentY - sharedViewLocation[1]);
		data.setSharedView(sharedView);
		data.setNormalView(normalView);
		data.setDuration(exitDuration);
		data.setDelay(exitDelay);

		return data;
	}
}
