package com.edavtyan.materialplayer2.ui.search.base;

import android.support.v4.app.Fragment;
import android.view.View;

import com.edavtyan.materialplayer2.databinding.FragmentListSearchBinding;
import com.edavtyan.materialplayer2.ui.search.SearchActivity;

public class SearchViewImpl implements SearchActivity.OnSearchQueryChangedListener {
	private final SearchActivity activity;
	private final SearchPresenter presenter;
	private final Fragment fragment;
	private FragmentListSearchBinding binding;

	public SearchViewImpl(Fragment fragment, SearchPresenter presenter) {
		this.fragment = fragment;
		this.presenter = presenter;
		activity = (SearchActivity) fragment.getActivity();
	}

	public void init() {
		if (fragment.getView() == null) {
			throw new IllegalStateException(
					"Fragment " + fragment.getClass().getSimpleName() + "has no layout");
		}

		binding = FragmentListSearchBinding.bind(fragment.getView());
		activity.addOnSearchQueryChangedListener(this);
		presenter.onSearchChange(activity.getSearchQuery());
	}

	public void destroy() {
		activity.removeOnSearchQueryChangedListener(this);
	}

	public void showEmptyQuery() {
		binding.list.setVisibility(View.INVISIBLE);
		binding.searchEmpty.setVisibility(View.VISIBLE);
		binding.searchNotFound.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onSearchQueryChanged(String query) {
		binding.list.setVisibility(View.VISIBLE);
		binding.searchEmpty.setVisibility(View.INVISIBLE);
		binding.searchNotFound.setVisibility(View.INVISIBLE);
		presenter.onSearchChange(query);
	}

	public void showEmptyResult() {
		binding.list.setVisibility(View.INVISIBLE);
		binding.searchEmpty.setVisibility(View.INVISIBLE);
		binding.searchNotFound.setVisibility(View.VISIBLE);
	}
}
