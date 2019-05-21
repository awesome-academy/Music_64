package com.sun.music_64.service;


interface LoadingTrackListener {
    void onChangeTrack();

    void onTrackState(boolean isPlaying);
}
