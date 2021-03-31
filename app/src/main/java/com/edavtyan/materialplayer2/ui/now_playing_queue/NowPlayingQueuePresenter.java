package com.edavtyan.materialplayer2.ui.now_playing_queue;

import com.edavtyan.materialplayer2.db.types.Track;
import com.edavtyan.materialplayer2.ui.lists.lib.ListModel;
import com.edavtyan.materialplayer2.ui.lists.lib.ListPresenter;

public class NowPlayingQueuePresenter implements ListPresenter<NowPlayingQueueViewHolder> {

	private final NowPlayingQueueModel model;
	private final NowPlayingQueueFragment view;

	@SuppressWarnings("FieldCanBeLocal")
	private final NowPlayingQueueModel.OnNewTrackListener onNewTrackListener
			= new NowPlayingQueueModel.OnNewTrackListener() {
		@Override
		public void onNewTrack() {
			view.notifyDataSetChanged();
		}
	};

	@SuppressWarnings("FieldCanBeLocal")
	private final ListModel.OnServiceConnectedListener onServiceConnectedListener
			= new ListModel.OnServiceConnectedListener() {
		@Override
		public void onServiceConnected() {
			view.notifyDataSetChanged();
		}
	};

	public NowPlayingQueuePresenter(NowPlayingQueueModel model, NowPlayingQueueFragment view) {
		super();
		this.model = model;
		this.model.setOnNewTrackListener(onNewTrackListener);
		this.model.setOnServiceConnectedListener(onServiceConnectedListener);
		this.view = view;
	}

	@Override
	public void onCreate() {
		model.bindService();
	}

	@Override
	public void onDestroy() {
		model.unbindService();
	}

	public void onItemClick(int position) {
		model.playItemAtPosition(position);
		view.notifyDataSetChanged();
	}

	public void onRemoveItemClick(int position) {
		model.removeItemAtPosition(position);
		view.removeItem(position);
	}

	@Override
	public void onBindViewHolder(NowPlayingQueueViewHolder holder, int position) {
		Track track = model.getTrackAtPosition(position);
		holder.setTitle(track.getTitle());
		holder.setInfo(track.getDuration(), track.getArtistTitle(), track.getAlbumTitle());
		holder.setIsPlaying(model.getNowPlayingTrack() == track);
	}

	@Override
	public int getItemCount() {
		return model.getTrackCount();
	}

	public int getItemId(int position) {
		return model.getTrackAtPosition(position).getId();
	}
}
