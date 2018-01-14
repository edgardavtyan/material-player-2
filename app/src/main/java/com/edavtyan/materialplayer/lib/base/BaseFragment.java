package com.edavtyan.materialplayer.lib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.lib.theme.ThemeSwitchModule;
import com.edavtyan.materialplayer.modular.fragment.ModularFragment;

public abstract class BaseFragment extends ModularFragment {

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		BaseFactory factory = getApp().getBaseFactory(getActivity());
		addModule(new ThemeSwitchModule(this, getActivity(), factory.getPrefs()));
	}

	private App getApp() {
		return (App) getActivity().getApplication();
	}
}
