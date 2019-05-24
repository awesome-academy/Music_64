package com.sun.music_64.screen.searchscreen;

import com.sun.music_64.data.model.Track;

import java.util.List;

public class SearchingContract {
    interface View {
        void searchTrackSuccess(List<Track> tracks);

        void searchTrackFailure(String error);
    }

    interface Presenter {
        void handleSearchTrack(String input);

        void cancelAsync();
    }
}
