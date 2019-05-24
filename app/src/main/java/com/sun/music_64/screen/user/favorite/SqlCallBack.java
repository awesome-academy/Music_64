package com.sun.music_64.screen.user.favorite;

import com.sun.music_64.data.model.Track;

import java.util.List;

public interface SqlCallBack {
    void receiverSuccess(List<Track> tracks);

    void receiverFail(String error);

}
