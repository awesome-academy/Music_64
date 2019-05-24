package com.sun.music_64.service;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import com.sun.music_64.R;
import com.sun.music_64.data.model.Track;
import com.sun.music_64.mediaplayer.LoopType;
import com.sun.music_64.mediaplayer.PlayMediaManager;
import com.sun.music_64.mediaplayer.PlayMusicInterface;

import java.util.ArrayList;
import java.util.List;

public class PlayMusicService extends Service implements
        MediaPlayer.OnErrorListener,
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener,
        PlayMusicInterface {
    public static final String ACTION_PREVIOUS = "com.sun.music_64.ACTION_PREVIOUS";
    public static final String ACTION_PLAY_AND_PAUSE = "com.sun.music_64.ACTION_PLAY_PAUSE";
    public static final String ACTION_NEXT = "com.sun.music_64.ACTION_NEXT";
    private IBinder mIBinder;
    private PlayMediaManager mManager;
    private NotificationMusic mNotification;
    private List<LoadingTrackListener> mListener;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, PlayMusicService.class);
        return intent;
    }

    public void onCreate() {
        super.onCreate();
        mIBinder = new MyBinder();
        mManager = PlayMediaManager.getIntance(this);
        mListener = new ArrayList<>();
        mNotification = new NotificationMusic();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction() == null) {
            return START_STICKY;
        }
        switch (intent.getAction()) {
            case ACTION_PLAY_AND_PAUSE:
                if (isPlaying()) {
                    pause();
                } else {
                    start();
                }
                mNotification.updatePlayAndPauseState(isPlaying());
                break;
            case ACTION_PREVIOUS:
                previous();
                break;
            case ACTION_NEXT:
                next();
                break;
            default:
                break;
        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        mManager.release();
        super.onDestroy();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        switch (mManager.getLoopType()) {
            case LoopType.ZERO:
                if (mManager.isLastTrack()) {
                    stop();
                } else {
                    next();
                }
                break;
            case LoopType.ONE:
                start();
                break;
            case LoopType.ALL:
                next();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Toast.makeText(this, getString(R.string.error_play_track),
                Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mManager.start();
    }

    @Override
    public void create(Track track) {

    }

    @Override
    public void start() {
        mManager.start();
        Track track = mManager.getCurrentTrack();
        Notification notification = mNotification.initLayoutNotification(this, track);
        mNotification.updatePlayAndPauseState(isPlaying());
        startForeground(NotificationMusic.NOTIFICATION_ID, notification);
        updateTrackState();
    }

    @Override
    public void pause() {
        mManager.pause();
        stopForeground(true);
        updateTrackState();
    }

    @Override
    public void previous() {
        mManager.previous();
        updateTrackChange();
    }

    @Override
    public void next() {
        mManager.next();
        updateTrackChange();
    }

    @Override
    public long getDuration() {
        return mManager.getDuration();
    }

    @Override
    public long getCurrentDuration() {
        return mManager.getCurrentDuration();
    }

    @Override
    public void shuffle() {
        mManager.shuffle();
    }

    @Override
    public void unShuffle() {
        mManager.unShuffle();
    }

    @Override
    public boolean isPlaying() {
        return mManager.isPlaying();
    }

    public void setLoop(int type) {
        mManager.setLoopType(type);
    }

    public int getLoop() {
        return mManager.getLoopType();
    }

    @Override
    public void stop() {
        mManager.stop();
    }

    @Override
    public void seek(int position) {
        mManager.seek(position);
    }

    @Override
    public void addTrack(Track track) {
        mManager.addTrack(track);
    }

    @Override
    public void addTracks(List<Track> tracks) {
        mManager.addTracks(tracks);
    }

    @Override
    public void changeTrack(Track track) {
        mManager.changeTrack(track);
    }

    @Override
    public void release() {
        mManager.release();
    }

    public List<Track> getTracks() {
        return mManager.getTracks();
    }

    public void onFailure() {
        Toast.makeText(this, getString(R.string.error_play_track),
                Toast.LENGTH_SHORT).show();
    }

    private void updateTrackState() {
        mNotification.updatePlayAndPauseState(isPlaying());
        for (LoadingTrackListener listener : mListener) {
            listener.onTrackState(isPlaying());
        }
    }

    private void updateTrackChange() {
        mNotification.updatePlayAndPauseState(isPlaying());
        for (LoadingTrackListener listener : mListener) {
            listener.onChangeTrack(mManager.getCurrentTrack());
        }
    }

    public int getShuffle() {
        return mManager.getShuffleType();
    }

    public class MyBinder extends Binder {
        public PlayMusicService getService() {
            return PlayMusicService.this;
        }
    }

    public Track getCurrentTrack() {
        return mManager.getCurrentTrack();
    }

    public void addPlayMusicListener(LoadingTrackListener listener) {
        mListener.add(listener);
    }
}
