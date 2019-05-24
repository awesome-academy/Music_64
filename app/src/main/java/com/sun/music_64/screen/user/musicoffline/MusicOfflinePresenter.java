package com.sun.music_64.screen.user.musicoffline;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.sun.music_64.data.model.Track;
import com.sun.music_64.data.sqlite.DBManagerTrack;
import com.sun.music_64.screen.user.favorite.FavoriteContract;

import java.util.ArrayList;

public class MusicOfflinePresenter implements MusicOffLineContract.Presenter {

    private MusicOffLineContract.View mView;

    public void setView(MusicOffLineContract.View view) {
        this.mView = view;
    }

    private static ArrayList<Track> getMultiDatas(Context context) {
        ArrayList<Track> musics = new ArrayList<>();

        ContentResolver resolver = context.getContentResolver();
        Cursor musicCursor = resolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                null);
        int musicColumnIndex;

        if (null != musicCursor && musicCursor.getCount() > 0) {
            for (musicCursor.moveToFirst(); !musicCursor.isAfterLast(); musicCursor
                    .moveToNext()) {
                Track.TrackBuilder trackBuilder = new Track.TrackBuilder();
                trackBuilder.setId(musicCursor.getInt(musicCursor
                        .getColumnIndex("_id")));
                musicColumnIndex = musicCursor
                        .getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);
                trackBuilder.setTitle(musicCursor.getString(musicColumnIndex));
                musicColumnIndex = musicCursor
                        .getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
                trackBuilder.setStreamUrl(musicCursor.getString(musicColumnIndex));
                musicColumnIndex = musicCursor
                        .getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST);
                trackBuilder.setArtist(musicCursor.getString(musicColumnIndex));
                musicColumnIndex = musicCursor
                        .getColumnIndex(MediaStore.Audio.AudioColumns.DURATION);
                trackBuilder.setDuration(musicCursor.getLong(musicColumnIndex));

                musics.add(trackBuilder.buildTrack());
            }
            musicCursor.close();
        }
        return musics;
    }

    @Override
    public void handleLoadTrackOffline(Context context) {
        mView.loadTrackOfflineSuccess(getMultiDatas(context));
    }
}
