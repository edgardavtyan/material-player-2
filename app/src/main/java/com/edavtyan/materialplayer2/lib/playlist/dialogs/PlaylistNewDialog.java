package com.edavtyan.materialplayer2.lib.playlist.dialogs;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.base.BaseDialog;

import butterknife.BindView;
import lombok.Setter;

public class PlaylistNewDialog extends BaseDialog {
	@BindView(R.id.edittext) EditText editTextView;
	@BindView(R.id.error_exists) TextView errorExistsView;

	public interface OnConfirmListener {
		void onConfirm(String playlistName);
	}

	private @Setter OnConfirmListener onConfirmListener;

	public PlaylistNewDialog(Context context) {
		super(context);
		errorExistsView.setText(R.string.playlist_new_exists);
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
			onConfirmListener.onConfirm(editTextView.getText().toString());
		}
	}

	@Override
	public void show() {
		super.show();
		editTextView.setText("");
		errorExistsView.setVisibility(View.GONE);
	}

	public void showAlreadyExistsError() {
		errorExistsView.setVisibility(View.VISIBLE);
	}
}
