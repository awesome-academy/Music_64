package com.sun.music_64.service;

import com.sun.music_64.data.model.Track;

interface LoadingTrackListener {
    void onChangeTrack(Track track);

    void onTrackState(boolean isPlaying);
}
