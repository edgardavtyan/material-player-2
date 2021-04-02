package com.edavtyan.materialplayer2.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.edavtyan.materialplayer2.App;
import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.base.BaseActivityTransparent;
import com.edavtyan.materialplayer2.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer2.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer2.modular.activity.modules.ActivityToolbarModule;
import com.edavtyan.materialplayer2.service.PlayerService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivityTransparent {
	@BindView(R.id.toolbar) Toolbar toolbar;
	@BindView(R.id.view_pager) ViewPager viewPager;
	@BindView(R.id.tab_layout) TabLayout tabLayout;
	@BindView(R.id.appbar) AppBarLayout appbar;

	@Inject ActivityToolbarModule toolbarModule;
	@Inject ActivityBaseMenuModule baseMenuModule;
	@Inject ScreenThemeModule themeModule;
	@Inject TabsAdapter tabsAdapter;
	@Inject TabsPartial tabsPartial;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		getComponent().inject(this);

		addModule(toolbarModule);
		addModule(baseMenuModule);
		addModule(themeModule);

		toolbarModule.setTitleString("");
		toolbarModule.setBackIconEnabled(false);

		viewPager.setAdapter(tabsAdapter);
		tabsPartial.setViewPager(viewPager);

		Intent intent = new Intent(this, PlayerService.class);
		startService(intent);
	}

	protected MainDIComponent getComponent() {
		return DaggerMainDIComponent
				.builder()
				.appDIComponent(((App) getApplication()).getAppComponent())
				.mainDIModule(new MainDIModule(this))
				.build();
	}
}
