package com.edavtyan.materialplayer2.lib.playlist.dialogs;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.databinding.DialogPresetNewBinding;
import com.edavtyan.materialplayer2.base.BaseDialog;

import lombok.Setter;

public class PlaylistNewDialog extends BaseDialog {
	private final DialogPresetNewBinding binding;

	public interface OnConfirmListener {
		void onConfirm(String playlistName);
	}

	private @Setter OnConfirmListener onConfirmListener;

	public PlaylistNewDialog(Context context) {
		super(context);
		binding = DialogPresetNewBinding.bind(getView());
		binding.errorExists.setText(R.string.playlist_new_exists);
	}

	@Override
	public int getDialogTitleRes() {
		return R.string.playlist_new_title;
	}

	@Override
	public int getPositiveButtonTextRes() {
		return R.string.playlist_new_action;
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
	public boolean closeWhenPositiveButtonClicked() {
		return false;
	}

	@Override
	public void onPositiveButtonClick() {
		if (onConfirmListener != null) {
			onConfirmListener.onConfirm(binding.edittext.getText().toString());
		}
	}

	@Override
	public void show() {
		super.show();
		binding.edittext.setText("");
		binding.errorExists.setVisibility(View.GONE);
	}

	public void showAlreadyExistsError() {
		binding.errorExists.setVisibility(View.VISIBLE);
	}
}
