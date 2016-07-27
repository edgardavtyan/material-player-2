package com.edavtyan.custompreference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.edavtyan.custompreference.utils.PixelConverter;

public class ColorSelectionPreference extends DialogPreference<ColorSelectionController>
		implements ColorSelectionView.OnColorSelectedListener {

	private ColorSelectionView colorSelectionView;
	private ColorCircleView colorView;


	public ColorSelectionPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ColorSelectionPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}


	@Override
	protected View onCreateDialogView() {
		int padding = PixelConverter.dpToPx(24);

		colorSelectionView = new ColorSelectionView(context, null);
		colorSelectionView.setPadding(padding, 0, padding, 0);
		colorSelectionView.setColors(controller.getEntries());
		colorSelectionView.rebuild();
		colorSelectionView.setSelectedColor(controller.getSelectedPrefIndex());
		colorSelectionView.setOnColorSelectedListener(this);
		return colorSelectionView;
	}

	@Override
	protected ColorSelectionController createController(AttributeSet attrs) {
		return new ColorSelectionController(this, attrs);
	}

	@Override
	protected void createEntryView() {
		inflate(context, R.layout.entry_color, this);

		TextView titleView = (TextView) findViewById(R.id.title);
		titleView.setText(controller.getTitle());

		colorView = (ColorCircleView) findViewById(R.id.color);
		colorView.setColor(controller.getCurrentColor());
	}

	@Override
	public void onColorSelected(int position) {
		controller.savePref(position);
		colorSelectionView.setSelectedColor(position);
		colorView.setColor(controller.getCurrentColor());
		closeDialog();
	}
}
