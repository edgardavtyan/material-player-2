package com.edavtyan.materialplayer.components.lists.track_list;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.components.lists.lib.ListMvp;

@SuppressWarnings("unused")
public interface TrackListMvp {
	interface Model extends ListMvp.Model {
		Track getTrackAtIndex(int position);
		int getItemCount();
		void playQueue(int position);
		void addToQueue(int position);
		void bindService();
		void unbindService();
		void update();
		void close();
	}

	interface Presenter extends ListMvp.Presenter<TrackListViewHolder> {
		void onHolderClick(int position);
		void onAddToPlaylist(int position);
	}

	interface View extends ListMvp.View {
		void gotoNowPlaying();
	}
}