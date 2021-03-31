package com.edavtyan.materialplayer2.lib.transition;

import android.os.Bundle;
import android.support.v4.util.Pair;
import android.view.View;

import com.ed.libsutils.utils.ViewUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class SourceSharedViews {
	private final List<Pair<View, String>> sharedViews;
	private final @Getter Class activityClass;

	private @Setter OnExitAnimationEndListener onExitAnimationEndListener;

	public interface OnExitAnimationEndListener {
		void onExitAnimationEnd();
	}

	@SafeVarargs
	public SourceSharedViews(Pair<View, String>... sharedViews) {
		this.sharedViews = new ArrayList<>(Arrays.asList(sharedViews));
		activityClass = sharedViews[0].first.getContext().getClass();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SourceSharedViews)) {
			return false;
		}

		SourceSharedViews other = (SourceSharedViews) obj;

		if (sharedViews.size() != other.sharedViews.size()) {
			return false;
		}

		if (activityClass != other.activityClass) {
			return false;
		}

		for (int i = 0; i < sharedViews.size(); i++) {
			Pair<View, String> thisPair = sharedViews.get(i);
			Pair<View, String> otherPair = other.sharedViews.get(i);
			if (thisPair.first.getId() != otherPair.first.getId()) {
				return false;
			}

			if (!thisPair.second.equals(otherPair.second)) {
				return false;
			}
		}

		return true;
	}

	public Bundle build() {
		Bundle bundle = new Bundle();
		ArrayList<String> transitionNames = new ArrayList<>(sharedViews.size());

		for (Pair<View, String> pair : sharedViews) {
			View view = pair.first;
			String transitionName = pair.second;
			int[] viewLocation = ViewUtils.getLocationOnScreen(view);
			transitionNames.add(transitionName);
			bundle.putFloat(transitionName + SharedTransitionsManager.PARAM_X, viewLocation[0]);
			bundle.putFloat(transitionName + SharedTransitionsManager.PARAM_Y, viewLocation[1]);
			bundle.putInt(transitionName + SharedTransitionsManager.PARAM_WIDTH, view.getWidth());
			bundle.putInt(transitionName + SharedTransitionsManager.PARAM_HEIGHT, view.getHeight());
		}

		bundle.putStringArrayList(SharedTransitionsManager.EXTRA_TRANSITION_NAMES, transitionNames);
		return bundle;
	}

	public void show() {
		for (Pair<View, String> pair : sharedViews) {
			pair.first.setVisibility(View.VISIBLE);
		}
	}

	public View find(String transitionName) {
		for (Pair<View, String> sharedView : sharedViews) {
			if (sharedView.second.equals(transitionName)) {
				return sharedView.first;
			}
		}

		throw new IllegalStateException(
				"Source shared view with transition name '" + transitionName + "' not found");
	}

	public void raiseOnExitTransitionEndListener() {
		if (onExitAnimationEndListener != null) {
			onExitAnimationEndListener.onExitAnimationEnd();
		}
	}
}
