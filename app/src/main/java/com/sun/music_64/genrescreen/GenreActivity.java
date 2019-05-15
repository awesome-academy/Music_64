package com.sun.music_64.genrescreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.sun.music_64.R;

import java.util.List;

public class GenreActivity extends AppCompatActivity {

    private List<Genre> mGenres;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerViewGenres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
        initviews();
        initToolbarGenreList();
        initRecyclerViewGenres();
    }

    private void initviews() {
        mToolbar = findViewById(R.id.toolbar);
        mRecyclerViewGenres = findViewById(R.id.recycler_music);
    }

    private void initToolbarGenreList() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initRecyclerViewGenres() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        GenreAdapter adapter = new GenreAdapter(getApplicationContext());
        mRecyclerViewGenres.setLayoutManager(linearLayoutManager);
        mRecyclerViewGenres.setAdapter(adapter);
    }
}
