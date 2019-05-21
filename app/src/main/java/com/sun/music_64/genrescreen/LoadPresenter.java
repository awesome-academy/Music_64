package com.sun.music_64.genrescreen;

import com.sun.music_64.data.model.Track;
import com.sun.music_64.utils.Constants;
import com.sun.music_64.utils.StringUtils;

import java.util.List;

public class LoadPresenter implements LoadContract.Presenter, NetworkCallback {

    private LoadContract.View mView;
    private ConnectAPI mRemoteDataSource;

    public void setView(LoadContract.View view) {
        this.mView = view;
    }

    @Override
    public void handleLoadGenre(String keygenre,int limit,int offset) {
        mRemoteDataSource = new ConnectAPI(this);
        mRemoteDataSource.execute(StringUtils.initGenreApi(Constants.KIND_TREND,Constants.GENRES_ALL_MUSIC,limit,offset));
    }

    @Override
    public void cancelAsync() {
        mRemoteDataSource.cancel(true);
    }

    @Override
    public void receiverUsersSuccess(List<Track> tracks) {
        mView.loadUserSuccess(tracks);
    }

    @Override
    public void receiverUsersFail() {
        mView.loadUserFailure("error load ");
    }
}
