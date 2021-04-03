package com.edavtyan.materialplayer2.ui.main;

import android.content.Intent;
import android.os.Bundle;

import com.edavtyan.materialplayer2.App;
import com.edavtyan.materialplayer2.base.BaseActivityTransparent;
import com.edavtyan.materialplayer2.databinding.ActivityMainBinding;
import com.edavtyan.materialplayer2.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer2.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer2.modular.activity.modules.ActivityToolbarModule;
import com.edavtyan.materialplayer2.service.PlayerService;

import javax.inject.Inject;

public class MainActivity extends BaseActivityTransparent {
	@Inject ActivityToolbarModule toolbarModule;
	@Inject ActivityBaseMenuModule baseMenuModule;
	@Inject ScreenThemeModule themeModule;
	@Inject TabsAdapter tabsAdapter;
	@Inject TabsPartial tabsPartial;

	private ActivityMainBinding binding;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = ActivityMainBinding.inflate(getLayoutInflater());

		setContentView(binding.getRoot());
		getComponent().inject(this);

		addModule(toolbarModule);
		addModule(baseMenuModule);
		addModule(themeModule);

		toolbarModule.setTitleString("");
		toolbarModule.setBackIconEnabled(false);

		binding.viewPager.setAdapter(tabsAdapter);
		tabsPartial.setViewPager(binding.viewPager);

		Intent intent = new Intent(this, PlayerService.class);
		startService(intent);
	}

	protected MainDIComponent getComponent() {
		return DaggerMainDIComponent
				.builder()
				.appDIComponent(((App) getApplication()).getAppComponent())
				.mainDIModule(new MainDIModule(this, binding))
				.build();
	}
}
