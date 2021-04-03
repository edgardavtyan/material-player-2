package com.edavtyan.materialplayer2.base;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import lombok.Getter;
import lombok.Setter;

public abstract class BaseDialog {
	private final AlertDialog dialog;
	@Getter private View view;

	@SuppressWarnings("FieldCanBeLocal")
	private final DialogInterface.OnShowListener onShowListener = dialogInterface -> {
		BaseDialog.this.onShow();
	};


	public interface OnDismissListener {
		void onDismiss();
	}

	private @Setter OnDismissListener onDismissListener;

	@StringRes
	public abstract int getDialogTitleRes();

	public BaseDialog(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setTitle(getDialogTitleRes())
				.setNegativeButton(android.R.string.cancel, (d, w) -> onNegativeButtonClick());

		if (getPositiveButtonTextRes() != -1) {
			if (closeWhenPositiveButtonClicked()) {
				builder.setPositiveButton(getPositiveButtonTextRes(), (d, w) -> onPositiveButtonClick());
			} else {
				builder.setPositiveButton(getPositiveButtonTextRes(), null);
			}
		}

		if (getLayoutRes() != -1) {
			LayoutInflater inflater = LayoutInflater.from(context);
			view = inflater.inflate(getLayoutRes(), null, false);
			builder.setView(view);
		}

		if (getMessageRes() != -1) {
			builder.setMessage(getMessageRes());
		}

		dialog = builder.create();
		dialog.setOnShowListener(onShowListener);
		dialog.setOnDismissListener(d -> onDismiss());
	}

	@LayoutRes
	public int getLayoutRes() {
		return -1;
	}

	@StringRes
	public int getPositiveButtonTextRes() {
		return -1;
	}

	@StringRes
	public int getMessageRes() {
		return -1;
	}

	public boolean getShowKeyboard() {
		return false;
	}

	public boolean closeWhenPositiveButtonClicked() {
		return true;
	}

	public void onShow() {}

	public void onPositiveButtonClick() {}

	public void onNegativeButtonClick() {}

	public void onDismiss() {
		if (onDismissListener != null) onDismissListener.onDismiss();
	}

	public void show() {
		if (getShowKeyboard() && dialog.getWindow() != null) {
			dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		}

		dialog.show();

		if (!closeWhenPositiveButtonClicked()) {
			dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> onPositiveButtonClick());
		}
	}

	public void close() {
		dialog.dismiss();
	}
}
