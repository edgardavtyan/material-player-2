package com.edavtyan.materialplayer2.modular.activity.modules;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ed.libsutils.utils.WindowUtils;
import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.modular.activity.ActivityModule;

public class ActivityToolbarModule extends ActivityModule {

	private static final int DEFAULT_TITLE_STRING_ID = R.string.app_name;
	private static final boolean DEFAULT_BACK_ICON_ENABLED = true;

	private final AppCompatActivity activity;

    private final Toolbar toolbar;

	private int titleStringId;
	private boolean isBackIconEnabled;

	public ActivityToolbarModule(AppCompatActivity activity) {
		this.activity = activity;
		this.toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
		titleStringId = DEFAULT_TITLE_STRING_ID;
		isBackIconEnabled = DEFAULT_BACK_ICON_ENABLED;
	}

	public ActivityToolbarModule(AppCompatActivity activity, @StringRes int titleStringId) {
		this(activity);
		this.titleStringId = titleStringId;
		this.isBackIconEnabled = DEFAULT_BACK_ICON_ENABLED;
	}

	public ActivityToolbarModule(
			AppCompatActivity activity,
			@StringRes int titleStringId,
			boolean isBackIconEnabled) {
		this(activity);
		this.titleStringId = titleStringId;
		this.isBackIconEnabled = isBackIconEnabled;
	}

	public void setTitleString(String title) {
		toolbar.setTitle(title);
	}

	public void setBackIconEnabled(boolean enabled) {
		assert activity.getSupportActionBar() != null;
		activity.getSupportActionBar().setDisplayHomeAsUpEnabled(enabled);
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		toolbar.setTitle(activity.getResources().getString(titleStringId));
		activity.setSupportActionBar(toolbar);
		setBackIconEnabled(isBackIconEnabled);

		if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
			WindowUtils.makeStatusBarSemiTransparent(activity);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			activity.onBackPressed();
			break;
		}

		return super.onOptionsItemSelected(item);
	}
}
