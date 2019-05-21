package com.sun.music_64.mediaplayer;

import com.sun.music_64.data.model.Track;

import java.util.List;

public interface PlayMusicInterface {
    void create(Track track);

    void start();

    void pause();

    void previous();

    void next();

    long getDuration();

    long getCurrentDuration();

    void shuffle();

    void unShuffle();

    boolean isPlaying();

    void stop();

    void seek(int position);

    void addTrack(Track track);

    void addTracks(List<Track> tracks);

    void changeTrack(Track track);

    void release();
}
