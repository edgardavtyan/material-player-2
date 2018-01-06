package com.edavtyan.materialplayer.lib.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.modular.activity.ModularActivity;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityThemeSwitchModule;
import com.edavtyan.materialplayer.utils.WindowUtils;

import butterknife.ButterKnife;

public abstract class BaseActivity extends ModularActivity {

	public abstract int getLayoutId();

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		BaseFactory factory = getApp().getBaseFactory(this);
		addModule(new ActivityThemeSwitchModule(this, factory.getPrefs(), factory.getThemeUtils()));
		addModule(new ActivityBaseMenuModule(this, factory.getNavigator()));

		setContentView(getLayoutId());

		ButterKnife.bind(this);

		if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
			WindowUtils.makeStatusBarSemiTransparent(this);
		}
	}

	public App getApp() {
		return (App) getApplication();
	}
}
