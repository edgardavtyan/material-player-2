package com.edavtyan.materialplayer2.ui.search;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;

import com.ed.libsutils.utils.WindowUtils;
import com.edavtyan.materialplayer2.App;
import com.edavtyan.materialplayer2.base.BaseActivity;
import com.edavtyan.materialplayer2.databinding.ActivitySearchBinding;
import com.edavtyan.materialplayer2.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer2.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer2.modular.activity.modules.ActivityToolbarModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SearchActivity extends BaseActivity {
	@Inject ActivityToolbarModule toolbarModule;
	@Inject ActivityBaseMenuModule baseMenuModule;
	@Inject ScreenThemeModule themeModule;

	private ActivitySearchBinding binding;

	private final List<OnSearchQueryChangedListener> onSearchQueryChangedListeners = new ArrayList<>();

	private final View.OnClickListener onBackButtonClickListener = v -> finish();

	private final TextWatcher onSearchQueryChangeWatcher = new TextWatcher() {
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			for (OnSearchQueryChangedListener listener : onSearchQueryChangedListeners) {
				listener.onSearchQueryChanged(s.toString());
			}
		}

		@Override
		public void afterTextChanged(Editable s) {
		}
	};


	public interface OnSearchQueryChangedListener {
		void onSearchQueryChanged(String query);
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = ActivitySearchBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		getComponent().inject(this);
		addModule(baseMenuModule);
		addModule(themeModule);

		binding.back.setOnClickListener(onBackButtonClickListener);
		binding.search.addTextChangedListener(onSearchQueryChangeWatcher);

		binding.viewPager.setAdapter(new SearchTabsAdapter(getSupportFragmentManager()));
		binding.tabs.setupWithViewPager(binding.viewPager);

		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
			WindowUtils.makeStatusBarSemiTransparent(this);
		}
	}

	public void addOnSearchQueryChangedListener(OnSearchQueryChangedListener listener) {
		onSearchQueryChangedListeners.add(listener);
	}

	public void removeOnSearchQueryChangedListener(OnSearchQueryChangedListener listener) {
		onSearchQueryChangedListeners.remove(listener);
	}

	public String getSearchQuery() {
		return binding.search.getText().toString();
	}

	protected SearchDIComponent getComponent() {
		return DaggerSearchDIComponent
				.builder()
				.appDIComponent(((App) getApplication()).getAppComponent())
				.searchDIModule(new SearchDIModule(this))
				.build();
	}
}
