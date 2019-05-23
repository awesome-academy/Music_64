package com.sun.music_64.screen.genrescreen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.sun.music_64.R;
import com.sun.music_64.data.model.Track;

import java.util.ArrayList;
import java.util.List;

public class GenreActivity extends AppCompatActivity implements GenreContract.View, ItemClickRecyclerView {

    private static final String KEY_PUT = "sendtogenre";
    private static final int LIMIT = 30;
    private static final int OFFSET = 0;
    private List<Track> mtracks;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerGenres;
    private ItemClickRecyclerView mItemClickRecyclerView;
    private GenreAdapter mGenreAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private GenrePresenter mGenrePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);

        initviews();
        initToolbar();
        initRecyclerGenres();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    @Override
    public void loadGenresSuccess(List<Track> tracks) {
        mtracks.clear();
        mtracks.addAll(tracks);
        mGenreAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadGenresFailure(String error) {
    }

    @Override
    public void onClick(View view, int position) {
    }

    @Override
    public void onShow(View view, int position) {
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static Intent getIntent(Context context, String keyGenre) {
        Intent intent = new Intent(context, GenreActivity.class);
        intent.putExtra(KEY_PUT, keyGenre);
        return intent;
    }

    private void initviews() {
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        mToolbar = findViewById(R.id.toolbar_list);
        mRecyclerGenres = findViewById(R.id.recycler_music);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initRecyclerGenres() {
        mtracks = new ArrayList<>();
        mItemClickRecyclerView = this;
        mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mGenreAdapter = new GenreAdapter(mtracks, mItemClickRecyclerView);
        mRecyclerGenres.setLayoutManager(mLinearLayoutManager);
        mRecyclerGenres.setAdapter(mGenreAdapter);
    }

    private void getData() {
        initPresenter(getIntent().getStringExtra(KEY_PUT));
    }

    private void initPresenter(String keyGenre) {
        mGenrePresenter = new GenrePresenter();
        mGenrePresenter.setView(this);
        mGenrePresenter.handleLoadGenres(keyGenre, LIMIT, OFFSET);
    }
}
