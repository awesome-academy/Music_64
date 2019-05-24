package com.sun.music_64.screen.searchscreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.sun.music_64.R;
import com.sun.music_64.data.model.Track;
import com.sun.music_64.screen.genrescreen.ItemClickRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchingActivity extends AppCompatActivity implements SearchingContract.View, ItemClickRecyclerView {

    private SearchView searchView;
    private SearchingAdapter mSearchAdapter;
    private ItemClickRecyclerView mItemClickRecyclerView;
    private SearchingPresenter mSearchingPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);
        initViews();
        initToolbar();
        initSearchView();
        initRecyclerSearch();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void searchTrackSuccess(List<Track> tracks) {
        mSearchAdapter.getData(tracks);
    }

    @Override
    public void searchTrackFailure(String error) {
    }

    @Override
    public void onClick(View view, int position) {
    }

    @Override
    public void onShow(View view, int position) {
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, SearchingActivity.class);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_search);
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initSearchView() {

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                getData(s);
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                mSearchAdapter.resetTrack();
                return false;
            }
        });
    }

    private void initViews() {
        searchView = findViewById(R.id.search_main);
    }

    private void initRecyclerSearch() {
        RecyclerView mRecyclerTrack = findViewById(R.id.recycler_search);
        mItemClickRecyclerView = this;
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mSearchAdapter = new SearchingAdapter(new ArrayList<Track>(), mItemClickRecyclerView);
        mRecyclerTrack.setLayoutManager(mLinearLayoutManager);
        mRecyclerTrack.setAdapter(mSearchAdapter);
    }

    private void getData(String input) {
        initPresenter(input);
    }

    private void initPresenter(String input) {
        mSearchingPresenter = new SearchingPresenter();
        mSearchingPresenter.setView(this);
        registerPresenter(input);
    }

    private void registerPresenter(String input) {
        if (mSearchingPresenter != null)
            mSearchingPresenter.handleSearchTrack(input);
    }
}
