package com.sun.music_64.genrescreen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.sun.music_64.R;
import com.sun.music_64.data.model.Track;

import java.util.ArrayList;
import java.util.List;

public class GenreActivity extends AppCompatActivity implements LoadContract.View, ItemClickRecyclerView {

    private static final String KEY_PUT = "sendtogenre";
    private List<Track> mTracks;
    private static final int LIMIT = 30;
    private static final int OFFSET = 0;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerViewGenres;
    private LinearLayoutManager mLinearLayoutManager;
    private GenreAdapter mGenreAdapter;
    private ItemClickRecyclerView mItemClickRecyclerView;
    private LoadPresenter mLoadPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
        initviews();
        initToolbarGenreList();
        initRecyclerViewGenres();
        getData();
    }

    private void getData() {
        initPresenter(getIntent().getStringExtra(KEY_PUT));
    }

    private void initviews() {
        mToolbar = findViewById(R.id.toolbar_list);
        mRecyclerViewGenres = findViewById(R.id.recycler_music);
    }

    private void initToolbarGenreList() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initRecyclerViewGenres() {
        mTracks = new ArrayList<>();
        mItemClickRecyclerView = this;
        mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mGenreAdapter = new GenreAdapter(getApplicationContext(), mTracks, mItemClickRecyclerView);
        mRecyclerViewGenres.setLayoutManager(mLinearLayoutManager);
        mRecyclerViewGenres.setAdapter(mGenreAdapter);
    }

    private void initPresenter(String keygenre) {
        mLoadPresenter = new LoadPresenter();
        mLoadPresenter.setView(this);
        mLoadPresenter.handleLoadGenre(keygenre, LIMIT, OFFSET);
    }

    private void cancelConnect() {
        mLoadPresenter.cancelAsync();
    }

    @Override
    public void loadUserSuccess(List<Track> tracks) {
        mTracks.clear();
        mTracks.addAll(tracks);
        mGenreAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadUserFailure(String error) {
        Toast.makeText(this, "Load fail", Toast.LENGTH_SHORT).show();
        cancelConnect();
    }

    @Override
    public void onClick(View view, int position) {
        Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShow(View view, int position) {
        Toast.makeText(this, "Hello show infor" + position, Toast.LENGTH_SHORT).show();
    }

    public static void gotoActivity(Context context, String keygenre) {
        Intent intent = new Intent(context, GenreActivity.class);
        intent.putExtra(KEY_PUT, keygenre);
        context.startActivity(intent);
    }
}
