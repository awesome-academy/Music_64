package com.sun.music_64.screen.user.musicoffline;

import android.content.Context;

import com.sun.music_64.data.model.Track;

import java.util.List;

public interface MusicOffLineContract {
    interface View {
        void loadTrackOfflineSuccess(List<Track> tracks);
        void loadTrackOfflineFailure(String error);
    }

    interface Presenter {
        void handleLoadTrackOffline(Context context);
    }
}
