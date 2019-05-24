package com.sun.music_64.screen.searchscreen;

import com.sun.music_64.data.model.Track;
import com.sun.music_64.data.source.remote.NetworkCallback;
import com.sun.music_64.data.source.remote.SearchingAPI;
import com.sun.music_64.utils.StringUtils;

import java.util.List;

public class SearchingPresenter implements SearchingContract.Presenter, NetworkCallback {

    private SearchingContract.View mView;
    private SearchingAPI mSearchAPI;

    public void setView(SearchingContract.View view) {
        this.mView = view;
    }

    @Override
    public void handleSearchTrack(String input) {
        mSearchAPI = new SearchingAPI(this);
        mSearchAPI.execute(StringUtils.initSearchApi(input));
    }

    @Override
    public void cancelAsync() {
        mSearchAPI.cancel(true);
    }

    @Override
    public void receiverSuccess(List<Track> tracks) {
        mView.searchTrackSuccess(tracks);
    }

    @Override
    public void receiverFail(String error) {
        mView.searchTrackFailure(error);
    }
}
