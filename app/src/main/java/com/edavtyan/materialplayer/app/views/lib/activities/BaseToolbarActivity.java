package com.edavtyan.materialplayer.app.views.lib.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.edavtyan.materialplayer.app.R;

public abstract class BaseToolbarActivity extends BaseActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle(getResources().getString(getToolbarTitleStringId()));
		setSupportActionBar(toolbar);
		if (isBackIconEnabled()) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	/*
	 * Protected methods
	 */

	protected boolean isBackIconEnabled() {
		return true;
	}

	/*
	 * Abstract methods
	 */

	public abstract int getToolbarTitleStringId();
}
