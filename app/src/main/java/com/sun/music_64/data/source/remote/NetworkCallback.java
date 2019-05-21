package com.sun.music_64.data.source.remote;

import com.sun.music_64.data.model.Track;

import java.util.List;

public interface NetworkCallback {
    void receiverSuccess(List<Track> tracks);

    void receiverFail(String error);

    void cancelAsync();
}
