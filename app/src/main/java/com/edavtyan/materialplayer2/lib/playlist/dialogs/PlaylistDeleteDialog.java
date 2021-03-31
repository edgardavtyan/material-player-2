package com.edavtyan.materialplayer2.lib.playlist.dialogs;

import android.content.Context;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.base.BaseDialog;
import com.edavtyan.materialplayer2.ui.lists.playlist_list.PlaylistListPresenter;

public class PlaylistDeleteDialog extends BaseDialog {
	private final PlaylistListPresenter presenter;

	public PlaylistDeleteDialog(Context context, PlaylistListPresenter presenter) {
		super(context);
		this.presenter = presenter;
	}

	@Override
	public int getDialogTitleRes() {
		return R.string.playlist_delete_title;
	}

	@Override
	public int getPositiveButtonTextRes() {
		return R.string.playlist_delete_action;
	}

	@Override
	public int getMessageRes() {
		return R.string.playlist_delete_message;
	}

	@Override
	public void onPositiveButtonClick() {
		super.onPositiveButtonClick();
		presenter.onDeletePlaylistConfirm();
	}
}
