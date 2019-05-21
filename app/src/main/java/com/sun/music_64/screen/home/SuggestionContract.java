package com.sun.music_64.screen.home;

import com.sun.music_64.data.model.Track;

import java.util.List;

public interface SuggestionContract {
    interface View {
        void loadSuggestionSuccess(List<Track> tracks);

        void loadSuggestionFailure(String error);
    }

    interface Presenter {
        void handleLoadSuggestion(String keyGenre, int limit, int offset);

        void cancelAsync();
    }
}
