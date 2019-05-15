package com.sun.music_64.genrescreen;

import com.sun.music_64.data.model.Track;

import java.util.List;

public interface NetworkCallback {
    void receiverUsersSuccess(List<Track> tracks);
    void receiverUsersFail();
    void cancelAsync();
}
