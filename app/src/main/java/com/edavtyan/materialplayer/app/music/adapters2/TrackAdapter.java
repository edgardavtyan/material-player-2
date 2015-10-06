package com.edavtyan.materialplayer.app.music.adapters2;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.edavtyan.materialplayer.app.R;

import java.util.concurrent.TimeUnit;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder> {
    public static final Uri URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

    public static final String SORT_ORDER = MediaStore.Audio.Media.TITLE + " ASC";

    public static final String[] PROJECTION = {
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TRACK,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.DURATION
    };



    private CursorAdapter cursorAdapter;

    private Context context;



    public TrackAdapter(Context context, Cursor cursor) {
        this.context = context;

        cursorAdapter = new CursorAdapter(context, cursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                View view =  LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.listitem_track, parent, false);

                TrackViewHolder viewHolder = new TrackViewHolder(view);
                view.setTag(viewHolder);
                return view;
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TrackViewHolder vh = (TrackViewHolder) view.getTag();
                vh.titleTextView.setText(cursor.getString(2));
                vh.infoTextView.setText(getInfo(cursor, vh));
            }
        };
    }



    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = cursorAdapter.newView(context, cursorAdapter.getCursor(), parent);
        return new TrackViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TrackViewHolder holder, int position) {
        cursorAdapter.getCursor().moveToPosition(position);
        cursorAdapter.bindView(holder.itemView, context, cursorAdapter.getCursor());
    }

    @Override
    public int getItemCount() {
        return cursorAdapter.getCount();
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView infoTextView;

        public TrackViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.listitem_track_title);
            infoTextView = (TextView) itemView.findViewById(R.id.listitem_track_info);
        }
    }



    private String getDurationStr(long duration) {
        long totalSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
        long seconds = totalSeconds % 60;
        long minutes = totalSeconds / 60;
        long hours = minutes / 60;

        if (hours == 0) {
            return String.format("%02d:%02d", minutes, seconds);
        } else {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }
    }

    private String getInfo(Cursor cursor, TrackViewHolder vh) {
        String durationStr = getDurationStr(cursor.getLong(5));
        Resources res = vh.itemView.getContext().getResources();
        return res.getString(R.string.listitem_track_info,
                durationStr, cursor.getString(3), cursor.getString(4));
    }
}