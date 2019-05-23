package com.sun.music_64.screen.user.favorite;

import com.sun.music_64.data.model.Track;

import java.util.List;

public interface FavoriteContract {
    interface View {

        void addTrackSuccess();

        void addTrackFaill();


        void loadTrackSuccess(List<Track> tracks);

        void loadTrackFailure(String error);

        void deleteTrackSuccess();

        void deleteTrackFailt();
    }

    interface Presenter {
        void handleAddTrack(Track track);

        void handleLoadTrack();

        void handledeleteTrack(Track track);
    }
}
