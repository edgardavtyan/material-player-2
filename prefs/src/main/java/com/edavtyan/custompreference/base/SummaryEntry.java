package com.edavtyan.custompreference.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edavtyan.custompreference.R;

import lombok.Setter;

public class SummaryEntry implements View.OnClickListener {
	private final TextView titleView;
	private final TextView summaryView;
	private @Setter OnClickListener onClickListener;

	public interface OnClickListener {
		void onEntryClick();
	}

	public SummaryEntry(Context context, LinearLayout view) {
		LayoutInflater.from(context).inflate(R.layout.entry_summary, view, true);
		view.setOrientation(LinearLayout.VERTICAL);
		view.setOnClickListener(this);
		titleView = (TextView) view.findViewById(R.id.title);
		summaryView = (TextView) view.findViewById(R.id.summary);
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setSummary(String summary) {
		summaryView.setText(summary);
	}

	@Override
	public void onClick(View v) {
		if (onClickListener != null) {
			onClickListener.onEntryClick();
		}
	}
}
