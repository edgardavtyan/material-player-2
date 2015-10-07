package com.edavtyan.materialplayer.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class ArtistActivity extends AppCompatActivity {
    public static final String EXTRA_ARTIST_TITLE = "artist_title";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        initToolbar();
        initCollapsingToolbar();
    }



    private void initCollapsingToolbar() {
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.artist_collapsingToolbar);

        collapsingToolbar.setTitle(getIntent().getStringExtra(EXTRA_ARTIST_TITLE));
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.artist_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
