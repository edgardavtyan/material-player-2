package com.edavtyan.custompreference;

import android.support.v7.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.edavtyan.custompreference.utils.AttributeResolver;
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
	protected void createDialogBuilder(AlertDialog.Builder builder) {
		int padding = PixelConverter.dpToPx(24);

		colorSelectionView = new ColorSelectionView(context, null);
		colorSelectionView.setPadding(padding, 0, padding, 0);
		colorSelectionView.setColors(controller.getEntries());
		colorSelectionView.rebuild();
		colorSelectionView.setSelectedColor(controller.getSelectedPrefIndex());
		colorSelectionView.setOnColorSelectedListener(this);
		builder.setView(colorSelectionView);
	}

	@Override
	protected ColorSelectionController createController(AttributeSet attrs) {
		return new ColorSelectionController(this, attrs);
	}

	@Override
	protected void createEntryView() {
		AttributeResolver attrs = new AttributeResolver(context);

		int height = attrs.getDimen(R.attr.listPreferredItemHeight);
		setMinimumHeight(height);

		Drawable background = attrs.getDrawable(R.attr.selectableItemBackground);
		setBackgroundDrawable(background);

		int paddingLeft = attrs.getDimen(R.attr.listPreferredItemPaddingLeft);
		int paddingRight = attrs.getDimen(R.attr.listPreferredItemPaddingRight);
		setPadding(paddingLeft, 0, paddingRight, 0);

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
