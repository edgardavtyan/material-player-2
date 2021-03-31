package com.edavtyan.materialplayer2.ui.lists.lib;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ed.libsutils.utils.WindowUtils;
import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.lib.layout_managers.FixedGridLayoutManager;
import com.edavtyan.materialplayer2.lib.testable.TestableRecyclerAdapter;
import com.edavtyan.materialplayer2.modular.fragment.FragmentModule;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragmentModule extends FragmentModule {
	@BindView(R.id.list) RecyclerView list;
	private final Fragment fragment;
	private final TestableRecyclerAdapter adapter;
	private final ListPresenter presenter;

	public ListFragmentModule(
			Fragment fragment,
			TestableRecyclerAdapter adapter,
			ListPresenter presenter) {
		this.fragment = fragment;
		this.adapter = adapter;
		this.presenter = presenter;
	}

	public void notifyDataSetChanged() {
		adapter.notifyDataSetChangedNonFinal();
	}

	@Override
	public void onCreateView(View view) {
		super.onCreateView(view);
		ButterKnife.bind(this, view);

		int spanCount = WindowUtils.isPortrait(fragment.getContext()) ? 1 : 2;
		list.setAdapter(adapter);
		list.setLayoutManager(new FixedGridLayoutManager(fragment.getContext(), spanCount));

		presenter.onCreate();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		presenter.onDestroy();
	}
}
