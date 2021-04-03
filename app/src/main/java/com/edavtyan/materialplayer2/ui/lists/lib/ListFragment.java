package com.edavtyan.materialplayer2.ui.lists.lib;

import android.view.View;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.databinding.FragmentListBinding;
import com.edavtyan.materialplayer2.modular.fragment.ModularFragment;
import com.edavtyan.materialplayer2.ui.main.MainActivity;

public class ListFragment extends ModularFragment implements ListView {
	private ListFragmentModule listFragmentModule;

	protected FragmentListBinding binding;

	protected void initListView(ListPresenter presenter, ListAdapter adapter) {
		listFragmentModule = new ListFragmentModule(this, adapter, presenter);
		addModule(listFragmentModule);
	}

	@Override
	public void onCreateView(View view) {
		super.onCreateView(view);
		binding = FragmentListBinding.bind(view);
		if (isBackgroundEnabled()) {
			binding.background.setVisibility(View.VISIBLE);
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
