package com.edavtyan.materialplayer.lib.mvp.list;

import android.content.Context;

import com.edavtyan.materialplayer.lib.base.BaseFactory;

public class ListFactory extends BaseFactory {
	private CompactListPref compactListPref;

	public ListFactory(Context context) {
		super(context);
	}

	public CompactListPref provideCompactListPref() {
		if (compactListPref == null)
			compactListPref = new CompactListPref(provideContext(), providePrefs());
		return compactListPref;
	}
}