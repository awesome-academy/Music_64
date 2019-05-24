package com.sun.music_64.screen.nowplaying;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.sun.music_64.R;
import com.sun.music_64.data.model.Track;
import com.sun.music_64.service.LoadingTrackListener;
import com.sun.music_64.service.PlayMusicService;

import java.util.List;

public class PlayingListActivity extends AppCompatActivity
        implements LoadingTrackListener, PlayingListAdapter.TrackClickListener {
    private static final int OFFSET = 0;
    private static final String PLAYING_LIST = "Playing list";
    private RecyclerView mRecyclerTracks;
    private Toolbar mToolbar;
    private PlayMusicService mService;
    private ServiceConnection mConnection;
    private PlayingListAdapter mListAdapter;
    private List<Track> mTracks;
    private LinearLayoutManager mLayoutManager;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_list);
        initView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initView() {
        mRecyclerTracks = findViewById(R.id.recycle_playing_list);
        mToolbar = findViewById(R.id.tool_bar);
        mToolbar.setTitle(PLAYING_LIST);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerTracks.setLayoutManager(mLayoutManager);
        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                PlayMusicService.MyBinder myBinder = (PlayMusicService.MyBinder) service;
                mService = myBinder.getService();
                mService.addPlayMusicListener(PlayingListActivity.this);
                bindData(mService.getTracks());
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        Intent intent = PlayMusicService.getIntent(this);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    private void bindData(List<Track> tracks) {
        mTracks = tracks;
        mListAdapter = new PlayingListAdapter(mTracks, this);
        mListAdapter.setTrackPlaying(mService.getCurrentTrack());
        mLayoutManager.scrollToPosition(mService.getTracks()
                .indexOf(mService.getCurrentTrack()));
        mRecyclerTracks.setAdapter(mListAdapter);
        mListAdapter.notifyDataSetChanged();

        ItemTouchHelper.Callback callback = new MyItemTouchCallack(mListAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerTracks);
    }

    @Override
    public void onTrackClick(Track track) {
        mService.changeTrack(track);
    }

    @Override
    public void onRemoveTrackClick(Track track) {
        int position = mTracks.indexOf(track);
        mTracks.remove(track);
        mListAdapter.notifyItemRemoved(position);
        mService.removeTrack(track);
    }

    @Override
    public void onChangeTrack(Track track) {
        mLayoutManager.scrollToPositionWithOffset(mService.getTracks()
                .indexOf(mService.getCurrentTrack()), OFFSET);
        mListAdapter.setTrackPlaying(mService.getCurrentTrack());
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onTrackState(boolean isPlaying) {

    }
}
