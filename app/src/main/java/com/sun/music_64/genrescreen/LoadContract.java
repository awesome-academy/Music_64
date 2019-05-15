package com.sun.music_64.genrescreen;

import com.sun.music_64.data.model.Track;

import java.util.List;

public interface LoadContract {

        interface View {
            void loadUserSuccess(List<Track> tracks);
            void loadUserFailure(String error);
        }

        interface Presenter {
            void handleLoadGenre(String keygenre,int limit,int offset);
            void cancelAsync();
        }
    }
