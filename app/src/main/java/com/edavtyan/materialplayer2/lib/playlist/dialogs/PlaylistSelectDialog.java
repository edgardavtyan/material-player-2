package com.edavtyan.materialplayer2.lib.playlist.dialogs;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.base.BaseDialog;
import com.edavtyan.materialplayer2.databinding.DialogPlaylistAddBinding;

import lombok.Setter;

public class PlaylistSelectDialog extends BaseDialog {

	private final PlaylistSelectDialogAdapter adapter;

	private @Setter PlaylistSelectDialogViewHolder.OnClickListener onPlaylistClickListener;
	private @Setter View.OnClickListener onNewPlaylistClickListener;

	public PlaylistSelectDialog(Context context) {
		super(context);

		DialogPlaylistAddBinding binding = DialogPlaylistAddBinding.bind(getView());

		adapter = new PlaylistSelectDialogAdapter(context);
		adapter.setOnClickListener(p -> onPlaylistClickListener.onClick(p));
		binding.list.setAdapter(adapter);
		binding.list.setLayoutManager(new LinearLayoutManager(context));
	}

	public void show(String[] playlistNames) {
		adapter.update(playlistNames);
		show();
	}

	@Override
	public int getDialogTitleRes() {
		return R.string.playlist_select_title;
	}

	@Override
	public int getPositiveButtonTextRes() {
		return R.string.playlist_select_createNew;
	}

	@Override
	public int getLayoutRes() {
		return R.layout.dialog_playlist_add;
	}

	@Override
	public void onPositiveButtonClick() {
		onNewPlaylistClickListener.onClick(null);
	}
}
