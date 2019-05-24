package com.sun.music_64.screen.user.favorite;

import android.content.Context;

import com.sun.music_64.data.model.Track;
import com.sun.music_64.data.sqlite.DBManagerTrack;

import java.util.List;

public class FavoritePresenter implements FavoriteContract.Presenter {

    private final static int RESULT_DELETE = 1;
    private FavoriteContract.View mView;
    private DBManagerTrack mDBManager;

    public void setView(FavoriteContract.View view, Context context) {
        this.mView = view;
        initDB(context);
    }

    private void initDB(Context context) {
        mDBManager = DBManagerTrack.getIntance(context);
    }

    @Override
    public void handleAddTrack(Track track) {
        mDBManager.addTrack(track);
    }

    @Override
    public void handleLoadTrack() {
        mView.loadTrackSuccess(mDBManager.getAllTrack());
    }

    @Override
    public void handledeleteTrack(Track track) {
        if (mDBManager.deleteTrack(track) >= RESULT_DELETE) {
            mView.deleteTrackSuccess();
        } else {
            mView.deleteTrackFailt();
        }
    }
}
