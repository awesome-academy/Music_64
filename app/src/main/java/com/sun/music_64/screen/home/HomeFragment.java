package com.sun.music_64.screen.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sun.music_64.R;
import com.sun.music_64.data.model.Track;
import com.sun.music_64.screen.genrescreen.GenreActivity;
import com.sun.music_64.screen.genrescreen.GenreEntity;
import com.sun.music_64.screen.genrescreen.ItemClickRecyclerView;
import com.sun.music_64.screen.searchscreen.SearchingActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener, ItemClickRecyclerView
        , SuggestionContract.View {

    /*
    count Tracks result Limit and start index row = 0
     */
    private static final int LIMIT = 20;
    private static final int OFFSET = 0;
    public static HomeFragment sInstance;
    private Toolbar mToolbar;
    private List<Track> mtracks;
    private ItemClickRecyclerView mItemClickRecyclerView;
    private SuggestionPresenter mSuggestPresenter;
    private SuggestionAdapter mSuggestAdapter;

    public static HomeFragment getInstance() {
        if (sInstance == null) {
            sInstance = new HomeFragment();
        }
        return sInstance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initUiAndRegisterListener(view);
        initRecyclerGenres(view);
        initPresenter(GenreEntity.KIND_TREND);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_all_music:
                gotoGenresActivity(GenreEntity.GENRES_ALL_MUSIC);
                break;
            case R.id.image_all_audio:
                gotoGenresActivity(GenreEntity.GENRES_ALL_AUDIO);
                break;
            case R.id.image_ambient:
                gotoGenresActivity(GenreEntity.GENRES_AMBIENT);
                break;
            case R.id.image_classical:
                gotoGenresActivity(GenreEntity.GENRES_CLASSICAL);
                break;
            case R.id.image_country:
                gotoGenresActivity(GenreEntity.GENRES_COUNTRY);
                break;
            case R.id.image_rock:
                gotoGenresActivity(GenreEntity.GENRES_COUNTRY);
                break;
            case R.id.search_home:
                gotoSearchingActivity();
                break;
        }
    }

    private void gotoGenresActivity(String genre) {
        startActivity(GenreActivity.getIntent(getActivity().getApplicationContext(), genre));
    }

    private void gotoSearchingActivity() {
        startActivity(SearchingActivity.getIntent(getActivity().getApplicationContext()));
    }

    @Override
    public void onClick(View view, int position) {
    }

    @Override
    public void onShow(View view, int position) {
    }

    @Override
    public void loadSuggestionSuccess(List<Track> tracks) {
        mSuggestAdapter.setData(tracks);
    }

    @Override
    public void loadSuggestionFailure(String error) {
    }

    private void initUiAndRegisterListener(View view) {
        mToolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        view.findViewById(R.id.search_home).setOnClickListener(this);
        view.findViewById(R.id.image_all_music).setOnClickListener(this);
        view.findViewById(R.id.image_all_audio).setOnClickListener(this);
        view.findViewById(R.id.image_ambient).setOnClickListener(this);
        view.findViewById(R.id.image_classical).setOnClickListener(this);
        view.findViewById(R.id.image_country).setOnClickListener(this);
        view.findViewById(R.id.image_rock).setOnClickListener(this);
    }

    private void initPresenter(String keyTrending) {
        mSuggestPresenter = new SuggestionPresenter();
        mSuggestPresenter.setView(this);
        initData(keyTrending, LIMIT, OFFSET);
    }

    private void initData(String keyTrending, int limit, int offset) {
        mSuggestPresenter.handleLoadSuggestion(keyTrending, limit, offset);
    }

    private void initRecyclerGenres(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_suggested_song);
        mtracks = new ArrayList<>();
        mItemClickRecyclerView = this;
        LinearLayoutManager mLinearLayoutManager =
                new LinearLayoutManager(getActivity().getApplicationContext(),
                        LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        mSuggestAdapter = new SuggestionAdapter(mtracks, mItemClickRecyclerView);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setAdapter(mSuggestAdapter);
    }
}
