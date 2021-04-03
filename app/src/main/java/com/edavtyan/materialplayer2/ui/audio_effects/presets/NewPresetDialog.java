package com.edavtyan.materialplayer2.ui.audio_effects.presets;

import android.content.Context;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.databinding.DialogPresetNewBinding;
import com.edavtyan.materialplayer2.ui.audio_effects.AudioEffectsPresenter;
import com.edavtyan.materialplayer2.base.BaseDialog;

public class NewPresetDialog extends BaseDialog {
	private final AudioEffectsPresenter presenter;
	private final DialogPresetNewBinding binding;

	public NewPresetDialog(Context context, AudioEffectsPresenter presenter) {
		super(context);
		this.presenter = presenter;
		this.binding = DialogPresetNewBinding.bind(getView());
	}

	@Override
	public int getDialogTitleRes() {
		return R.string.equalizer_presets_dialog_new_title;
	}

	@Override
	public int getPositiveButtonTextRes() {
		return android.R.string.ok;
	}

	@Override
	public int getLayoutRes() {
		return R.layout.dialog_preset_new;
	}

	@Override
	public boolean getShowKeyboard() {
		return true;
	}

	@Override
	public void onShow() {
		binding.edittext.requestFocus();
	}

	@Override
	public void onDismiss() {
		binding.edittext.setText(null);
	}

	@Override
	public void onPositiveButtonClick() {
		presenter.onNewPresetDialogOkButtonClicked(binding.edittext.getText().toString());
	}

	@Override
	public void onNegativeButtonClick() {
		presenter.onNewPresetDialogCancelButtonClicked();
	}

	public void show(String presetName) {
		binding.edittext.setText(presetName);
		binding.edittext.selectAll();
		show();
	}
}
