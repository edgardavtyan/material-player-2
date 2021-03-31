package com.edavtyan.materialplayer2.ui.prefs;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer2.App;
import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.base.BaseActivity;
import com.edavtyan.materialplayer2.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer2.modular.activity.ActivityModulesDIModule;
import com.edavtyan.materialplayer2.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer2.modular.activity.modules.ActivityToolbarModule;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class PrefActivity extends BaseActivity {
	@Inject ActivityToolbarModule toolbarModule;
	@Inject ActivityBaseMenuModule baseMenuModule;
	@Inject ScreenThemeModule themeModule;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_pref);
		ButterKnife.bind(this);

		getComponent().inject(this);
		addModule(toolbarModule);
		addModule(baseMenuModule);
		addModule(themeModule);
	}

	protected PrefDIComponent getComponent() {
		return DaggerPrefDIComponent
				.builder()
				.appDIComponent(((App) getApplication()).getAppComponent())
				.prefDIModule(new PrefDIModule(this))
				.activityModulesDIModule(new ActivityModulesDIModule(R.string.pref_title))
				.build();
	}
}
