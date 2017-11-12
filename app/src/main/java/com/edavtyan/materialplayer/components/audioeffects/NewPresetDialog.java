package com.edavtyan.materialplayer.components.audioeffects;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewPresetDialog {
	@BindView(R.id.preset_edit_text) EditText presetNameEditText;
	@BindView(R.id.preset_error_exists) TextView presetErrorExistsView;

	private final AlertDialog dialog;
	private final AudioEffectsMvp.Presenter presenter;

	@SuppressWarnings("FieldCanBeLocal")
	private final View.OnClickListener positiveButtonListener
			= new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			presenter.onNewPresetDialogOkButtonClicked(presetNameEditText.getText().toString());
		}
	};

	public NewPresetDialog(Context context, AudioEffectsMvp.Presenter presenter) {
		this.presenter = presenter;

		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.dialog_preset_new, null, false);
		ButterKnife.bind(this, view);

		this.dialog = new AlertDialog.Builder(context)
				.setView(view)
				.setTitle(R.string.equalizer_presets_dialog_new_title)
				.setPositiveButton(android.R.string.ok, null)
				.setNegativeButton(android.R.string.cancel, null)
				.create();
	}

	public void show() {
		dialog.show();
		dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(positiveButtonListener);
	}

	public void dismiss() {
		dialog.dismiss();
		presetNameEditText.setText(null);
		presetErrorExistsView.setVisibility(View.GONE);
	}

	public void showPresetAlreadyExists() {
		presetErrorExistsView.setVisibility(View.VISIBLE);
	}
}
