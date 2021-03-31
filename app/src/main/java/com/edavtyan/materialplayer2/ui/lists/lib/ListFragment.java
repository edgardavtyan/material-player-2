package com.edavtyan.materialplayer2.ui.lists.lib;

import android.view.View;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.modular.fragment.ModularFragment;
import com.edavtyan.materialplayer2.ui.main.MainActivity;

import butterknife.BindView;

public class ListFragment extends ModularFragment implements ListView {
	@BindView(R.id.background) View backgroundView;

	private ListFragmentModule listFragmentModule;

	protected void initListView(ListPresenter presenter, ListAdapter adapter) {
		listFragmentModule = new ListFragmentModule(this, adapter, presenter);
		addModule(listFragmentModule);
	}

	@Override
	public void onCreateView(View view) {
		super.onCreateView(view);
		if (isBackgroundEnabled()) {
			backgroundView.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void disableTouchEvents() {
		((MainActivity) getActivity()).disableTouchEvents();
	}

	@Override
	public void enableTouchEvents() {
		((MainActivity) getActivity()).enableTouchEvents();
	}

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_list;
	}

	public void notifyDataSetChanged() {
		listFragmentModule.notifyDataSetChanged();
	}

	public boolean isBackgroundEnabled() {
		return false;
	}
}
