package com.edavtyan.materialplayer.app.music.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.activities.AlbumActivity;
import com.edavtyan.materialplayer.app.adapters.RecyclerServiceCursorAdapter;
import com.edavtyan.materialplayer.app.music.providers.ArtProvider;
import com.edavtyan.materialplayer.app.music.data.Track;
import com.edavtyan.materialplayer.app.music.columns.AlbumColumns;
import com.edavtyan.materialplayer.app.music.providers.TracksProvider;

import java.util.List;

public class AlbumsAdapter extends RecyclerServiceCursorAdapter<AlbumsAdapter.AlbumViewHolder> {
    public AlbumsAdapter(Context context) {
        super(context);
    }

    /*
     * ViewHolder
     */

    public class AlbumViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        private final TextView titleTextView;
        private final TextView infoTextView;
        private final ImageView artImageView;
        private final ImageButton menuButton;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            titleTextView = (TextView) itemView.findViewById(R.id.title);
            infoTextView = (TextView) itemView.findViewById(R.id.info);
            artImageView = (ImageView) itemView.findViewById(R.id.art);
            menuButton = (ImageButton) itemView.findViewById(R.id.menu);

            PopupMenu popupMenu = new PopupMenu(context, menuButton);
            popupMenu.inflate(R.menu.menu_track);
            popupMenu.setOnMenuItemClickListener(this);
            menuButton.setOnClickListener(view -> popupMenu.show());
        }

        @Override
        public void onClick(View view) {
            getCursor().moveToPosition(getAdapterPosition());
            Intent i = new Intent(context, AlbumActivity.class);
            i.putExtra(AlbumActivity.EXTRA_ALBUM_ID, getCursor().getInt(AlbumColumns.ID));
            context.startActivity(i);
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.menu_addToPlaylist:
                    getCursor().moveToPosition(getAdapterPosition());
                    int albumId = getCursor().getInt(AlbumColumns.ID);
                    List<Track> tracks = TracksProvider.allWithAlbumId(albumId, context);
                    getService().getPlayer().getQueue().addAll(tracks);

                default:
                    return false;
            }
        }
    }


    /*
     * RecyclerViewCursorAdapter
     */

    @Override
    protected View newView(Context context, ViewGroup parent) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.listitem_album, parent, false);

        AlbumViewHolder vh = new AlbumViewHolder(view);
        view.setTag(vh);
        return view;
    }

    @Override
    protected void bindView(View view, Context context, Cursor cursor) {
        AlbumViewHolder vh = (AlbumViewHolder) view.getTag();
        vh.titleTextView.setText(cursor.getString(AlbumColumns.TITLE));
        vh.infoTextView.setText(getAlbumInfo(cursor));

        String artPath = getCursor().getString(AlbumColumns.ART);
        Glide.with(context)
                .load(ArtProvider.fromPath(artPath))
                .error(R.drawable.fallback_cover_listitem)
                .into(vh.artImageView);
    }

    @Override
    protected AlbumViewHolder createViewHolder(View view) {
        return new AlbumViewHolder(view);
    }

    /*
     * Private methods
     */

    private String getAlbumInfo(Cursor cursor) {
        Resources res = context.getResources();

        String tracksCount = res.getQuantityString(
                R.plurals.tracks,
                cursor.getInt(AlbumColumns.ARTIST),
                cursor.getInt(AlbumColumns.SONGS_COUNT));

        return res.getString(
                R.string.pattern_album_info,
                cursor.getString(AlbumColumns.ARTIST),
                tracksCount);
    }
}
