package com.sun.music_64.screen.home;

import com.sun.music_64.data.model.Track;
import com.sun.music_64.data.source.remote.ConnectAPI;
import com.sun.music_64.data.source.remote.NetworkCallback;
import com.sun.music_64.screen.genrescreen.GenreEntity;
import com.sun.music_64.utils.StringUtils;

import java.util.List;

public class SuggestionPresenter implements SuggestionContract.Presenter, NetworkCallback {

    private SuggestionContract.View mView;
    private ConnectAPI mConnectAPI;

    public void setView(SuggestionContract.View view) {
        this.mView = view;
    }

    @Override
    public void handleLoadSuggestion(String keyTrending, int limit, int offset) {
        mConnectAPI = new ConnectAPI(this);
        mConnectAPI.execute(StringUtils.initGenreApi(keyTrending,
                GenreEntity.GENRES_ALL_MUSIC, limit, offset));
    }

    @Override
    public void cancelAsync() {
        mConnectAPI.cancel(true);
    }

    @Override
    public void receiverSuccess(List<Track> tracks) {
        mView.loadSuggestionSuccess(tracks);
    }

    @Override
    public void receiverFail(String error) {
        mView.loadSuggestionFailure(error);
    }
}
