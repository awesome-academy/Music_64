package com.sun.music_64.screen.mainplay;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sun.music_64.R;
import com.sun.music_64.data.model.Track;
import com.sun.music_64.mediaplayer.LoopType;
import com.sun.music_64.mediaplayer.ShuffleType;
import com.sun.music_64.service.LoadingTrackListener;
import com.sun.music_64.service.PlayMusicService;
import com.sun.music_64.utils.TimeConverters;

public class MainPlayActivity extends AppCompatActivity implements MainPlayContract.View,
        View.OnClickListener, LoadingTrackListener,
        SeekBar.OnSeekBarChangeListener {
    private static final String SHARE_VIA = "share via";
    private TextView mTextTrackName;
    private TextView mTextTrackArtist;
    private ImageView mImageArtwork;
    private ImageView mImageFavourite;
    private ImageView mImageDownload;
    private TextView mTextCurrentDuration;
    private TextView mTextDuration;
    private SeekBar mSeekbar;
    private ImageView mImageShuffle;
    private ImageView mImagePLayAndPause;
    private ImageView mImageLoop;
    private MainPlayPresenter mMainPlayPresenter;
    private PlayMusicService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_play);
        initViews();
        registerListener();
        initPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = PlayMusicService.getIntent(this);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            PlayMusicService.MyBinder myBinder = (PlayMusicService.MyBinder) service;
            mService = myBinder.getService();
            mService.addPlayMusicListener(MainPlayActivity.this);
            updateTrack(mService.getCurrentTrack());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            unbindService(mConnection);
        }
    };

    private void updateTrack(Track track) {
        mTextTrackName.setText(track.getTitle());
        mTextTrackArtist.setText(track.getArtist());
        mTextDuration.setText(TimeConverters.convertMilisecondToFormatTime(track.getDuration()));
        mSeekbar.setMax((int) track.getDuration());
        long currentDuration = mService.getCurrentDuration();
        mSeekbar.setProgress((int) currentDuration);
        mTextCurrentDuration.setText(TimeConverters.convertMilisecondToFormatTime(currentDuration));
        Glide.with(mImageArtwork)
                .load(track.getArtworkUrl())
                .apply(new RequestOptions().circleCrop())
                .into(mImageArtwork);
        displayImageDownload(track.isDownloadable());
        displayShuffleState(mService.getShuffle());
        displayLoopState(mService.getLoop());
        displayMediaPlayerState(mService.isPlaying());
    }

    private void displayImageDownload(boolean isDownloadable) {
        if (isDownloadable) {
            mImageDownload.setImageResource(R.drawable.ic_downloaded);
        } else {
            mImageDownload.setImageResource(R.drawable.ic_download);
        }
    }


    private void displayMediaPlayerState(boolean isPlaying) {
        if (isPlaying) {
            mImagePLayAndPause.setImageResource(R.drawable.ic_pause);
        } else {
            mImagePLayAndPause.setImageResource(R.drawable.ic_play_arrow);
        }
    }

    private void displayLoopState(int loopState) {
        if (loopState == LoopType.ZERO) {
            mImageLoop.setImageResource(R.drawable.ic_not_loop);
        } else if (loopState == LoopType.ONE) {
            mImageLoop.setImageResource(R.drawable.ic_loop_one);
        } else {
            mImageLoop.setImageResource(R.drawable.ic_loop);
        }
    }

    private void displayShuffleState(int shuffleType) {
        if (shuffleType == ShuffleType.OFF) {
            mImageLoop.setImageResource(R.drawable.ic_not_shuffle);
        } else {
            mImageLoop.setImageResource(R.drawable.ic_shuffle);
        }
    }

    @Override
    protected void onStop() {
        unbindService(mConnection);
        super.onStop();
    }

    private void initViews() {
        findViewById(R.id.image_back).setOnClickListener(this);
        findViewById(R.id.image_menu).setOnClickListener(this);
        findViewById(R.id.image_share).setOnClickListener(this);
        findViewById(R.id.image_previous).setOnClickListener(this);
        findViewById(R.id.image_next).setOnClickListener(this);
        mTextTrackName = findViewById(R.id.text_title);
        mTextTrackArtist = findViewById(R.id.text_artist);
        mImageArtwork = findViewById(R.id.image_artwork);
        mImageFavourite = findViewById(R.id.image_favorite);
        mImageDownload = findViewById(R.id.image_download);
        mTextCurrentDuration = findViewById(R.id.text_current_duration);
        mSeekbar = findViewById(R.id.seek_bar_play_music);
        mTextDuration = findViewById(R.id.text_duration);
        mImageShuffle = findViewById(R.id.image_shuffle);
        mImagePLayAndPause = findViewById(R.id.image_play);
        mImageLoop = findViewById(R.id.image_loop);
    }

    private void registerListener() {
        mImageFavourite.setOnClickListener(this);
        mImageDownload.setOnClickListener(this);
        mImageShuffle.setOnClickListener(this);
        mImagePLayAndPause.setOnClickListener(this);
        mImageLoop.setOnClickListener(this);
        mSeekbar.setOnSeekBarChangeListener(this);
    }

    private void initPresenter() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                onBackPressed();
                break;
            case R.id.image_menu:
                break;
            case R.id.image_favorite:
                break;
            case R.id.image_download:
                break;
            case R.id.image_share:
                handleShare();
                break;
            case R.id.image_shuffle:
                handleShuffle(mService.getShuffle());
                break;
            case R.id.image_previous:
                mService.previous();
                break;
            case R.id.image_play:
                handlePlay(mService.isPlaying());
                break;
            case R.id.image_next:
                mService.next();
                break;
            case R.id.image_loop:
                handleLoop(mService.getLoop());
                break;
        }
    }

    private void handleShare() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = mService.getCurrentTrack().getStreamUrl();
        String shareSubject = mService.getCurrentTrack().getTitle();
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                shareSubject);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, SHARE_VIA));
    }

    private void handleShuffle(int shuffleType) {
        if (shuffleType == ShuffleType.OFF) {
            mService.shuffle();
        } else {
            mService.unShuffle();
        }
        displayShuffleState(shuffleType);
    }

    private void handlePlay(boolean isPlaying) {
        if (isPlaying) {
            mService.pause();
        } else {
            mService.start();
        }
        displayMediaPlayerState(isPlaying);
    }

    private void handleLoop(int loopType) {
        if (loopType == LoopType.ZERO) {
            mService.setLoop(LoopType.ALL);
        } else if (loopType == LoopType.ONE) {
            mService.setLoop(LoopType.ZERO);
        } else {
            mService.setLoop(LoopType.ONE);
        }
        displayLoopState(loopType);
    }

    @Override
    public void onChangeTrack(Track track) {
        updateTrack(track);
    }

    @Override
    public void onTrackState(boolean isPlaying) {
        displayMediaPlayerState(isPlaying);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mService.seek(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
