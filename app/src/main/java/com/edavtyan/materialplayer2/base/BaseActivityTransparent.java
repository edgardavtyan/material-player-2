package com.edavtyan.materialplayer2.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer2.lib.theme.ThemeColors;

public abstract class BaseActivityTransparent extends BaseActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ThemeColors theme = new ThemeColors(this);
		setTheme(theme.getThemeTranslucentRes());
	}
}