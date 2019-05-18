package com.sun.music_64.screen.genrescreen;

import com.sun.music_64.data.model.Track;

import java.util.List;

public interface GenreContract {
    interface View {
        void loadGenresSuccess(List<Track> tracks);

        void loadGenresFailure(String error);
    }

    interface Presenter {
        void handleLoadGenres(String keyGenre, int limit, int offset);

        void cancelAsync();
    }
}
