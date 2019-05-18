package com.sun.music_64.screen.genrescreen;

import com.sun.music_64.data.model.Track;
import com.sun.music_64.data.source.remote.ConnectAPI;
import com.sun.music_64.data.source.remote.NetworkCallback;
import com.sun.music_64.utils.StringUtils;

import java.util.List;

public class GenrePresenter implements GenreContract.Presenter, NetworkCallback {

    private GenreContract.View mView;
    private ConnectAPI mConnectAPI;

    public void setView(GenreContract.View view) {
        this.mView = view;
    }

    @Override
    public void handleLoadGenres(String keyGenre, int limit, int offset) {
        mConnectAPI = new ConnectAPI(this);
        String genre = GenreEntity.GENRES_ALL_MUSIC;
        mConnectAPI.execute(StringUtils.initGenreApi(genre, keyGenre, limit, offset));
    }

    @Override
    public void cancelAsync() {
        mConnectAPI.cancel(true);
    }

    @Override
    public void receiverSuccess(List<Track> tracks) {
        mView.loadGenresSuccess(tracks);
    }

    @Override
    public void receiverFail(String error) {
        mView.loadGenresFailure(error);
    }
}
