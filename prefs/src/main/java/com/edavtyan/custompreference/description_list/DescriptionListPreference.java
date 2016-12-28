package com.edavtyan.custompreference.description_list;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.edavtyan.custompreference.base.BaseDialog;
import com.edavtyan.custompreference.base.BasePreference;
import com.edavtyan.custompreference.base.SummaryEntry;
import com.edavtyan.custompreference.utils.PixelConverter;

public class DescriptionListPreference
		extends BasePreference
		implements SummaryEntry.OnClickListener {

	private SummaryEntry entryView;
	private BaseDialog dialog;
	private DescriptionListPresenter presenter;

	public DescriptionListPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		initPreference(attrs);
	}

	public DescriptionListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initPreference(attrs);
	}

	public void showDialog() {
		dialog.show();
	}

	public void closeDialog() {
		dialog.dismiss();
	}

	public void setTitle(String title) {
		dialog.setTitle(title);
		entryView.setTitle(title);
	}

	public void setSummary(String summary) {
		entryView.setSummary(summary);
	}

	@Override
	public void onEntryClick() {
		presenter.onEntryClicked();
	}

	private void initPreference(AttributeSet attrs) {
		entryView = initEntryView();
		if (isInEditMode()) {
		} else {
			presenter = initPresenter(initModel(attrs));
			dialog = initDialog(presenter);
			presenter.onViewsInit();
		}
	}

	private SummaryEntry initEntryView() {
		SummaryEntry entryView = new SummaryEntry(context, this);
		entryView.setOnClickListener(this);
		return entryView;
	}

	private BaseDialog initDialog(DescriptionListPresenter presenter) {
		RecyclerView list = new RecyclerView(context);
		list.setLayoutManager(new LinearLayoutManager(context));
		list.setAdapter(new DescriptionListAdapter(context, presenter));
		list.setPadding(0, PixelConverter.dpToPx(8), 0, 0);

		BaseDialog dialog = new BaseDialog(context);
		dialog.setView(list);
		return dialog;
	}

	private DescriptionListPresenter initPresenter(DescriptionListModel model) {
		DescriptionListPresenter presenter = new DescriptionListPresenter(this, model);
		return presenter;
	}

	private DescriptionListModel initModel(AttributeSet attributeSet) {
		return new DescriptionListModel(context, attributeSet);
	}
}