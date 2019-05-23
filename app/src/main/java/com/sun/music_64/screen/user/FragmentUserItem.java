package com.sun.music_64.screen.user;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sun.music_64.R;
import com.sun.music_64.data.model.Track;
import com.sun.music_64.data.sqlite.DBManagerTrack;
import com.sun.music_64.screen.genrescreen.GenrePresenter;
import com.sun.music_64.screen.genrescreen.ItemClickRecyclerView;
import com.sun.music_64.screen.user.favorite.FavoriteAdapter;
import com.sun.music_64.screen.user.favorite.FavoriteContract;
import com.sun.music_64.screen.user.favorite.FavoritePresenter;
import com.sun.music_64.screen.user.favorite.ItemclickRecyclerFavorite;

import java.util.ArrayList;
import java.util.List;

public class FragmentUserItem extends Fragment implements ItemclickRecyclerFavorite, FavoriteContract.View {

    private static final String KEY_COLOR = "key_color";
    private FavoriteAdapter mFavoriteAdapter;
    private RecyclerView mRecyclerFavorite;
    private FavoritePresenter mFavoritePresenter;

    public FragmentUserItem() {
    }

    public static FragmentUserItem newInstance(int color) {
        FragmentUserItem fragment = new FragmentUserItem();
        Bundle args = new Bundle();
        args.putInt(KEY_COLOR, color);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_user_instance, container, false);
        ConstraintLayout constraintLayout = mRootView.findViewById(R.id.constraint_user_track);
        initRecyclerTrack(mRootView);
        switch (getArguments().getInt(KEY_COLOR)) {
            case 1:
                initPresenter();
                break;
            case 2:
                constraintLayout.setBackgroundColor(Color.RED);
                break;
            case 3:
                constraintLayout.setBackgroundColor(Color.YELLOW);
                break;
        }
        return mRootView;
    }

    public void initRecyclerTrack(View view) {
        mRecyclerFavorite = view.findViewById(R.id.recycler_user_track);
        mFavoriteAdapter = new FavoriteAdapter(new ArrayList<Track>(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerFavorite.setLayoutManager(linearLayoutManager);
        mRecyclerFavorite.setAdapter(mFavoriteAdapter);
    }

    private void initPresenter() {
        mFavoritePresenter = new FavoritePresenter();
        mFavoritePresenter.setView(this, getActivity().getApplicationContext());
        loadData();
    }

    private void loadData() {
        mFavoritePresenter.handleLoadTrack();
    }

    @Override
    public void onClick(View view, int position) {
    }

    @Override
    public void onShow(View view, int position) {
        mFavoritePresenter.handleAddTrack(new Track());
    }

    @Override
    public void onHeartClick(View view, int position) {
        mFavoritePresenter.handledeleteTrack(mFavoriteAdapter.gettracks().get(position));
        mFavoritePresenter.handleLoadTrack();
    }

    @Override
    public void addTrackSuccess() {
        mFavoritePresenter.handleLoadTrack();
    }

    @Override
    public void addTrackFaill() {
    }

    @Override
    public void loadTrackSuccess(List<Track> tracks) {
        mFavoriteAdapter.setData(tracks);
    }

    @Override
    public void loadTrackFailure(String error) {
    }

    @Override
    public void deleteTrackSuccess() {
        mFavoritePresenter.handleLoadTrack();
    }

    @Override
    public void deleteTrackFailt() {
    }
}
