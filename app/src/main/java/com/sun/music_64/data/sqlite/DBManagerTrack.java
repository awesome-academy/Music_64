package com.sun.music_64.data.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sun.music_64.data.model.Track;

import java.util.ArrayList;
import java.util.List;

public class DBManagerTrack extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TrackDB";
    private static final String TABLE_NAME = "track";
    private static final String ID = "id";
    private static final String IDTRACK = "idtrack";
    private static final String TITLE = "title";
    private static final String DURATION = "duration";
    private static final String ARTIST = "artist";
    private static final String STREAMURL = "streamurl";
    private static final String ARTWORKURL = "artworkurl";
    private static Integer VERSION = 1;
    private static Integer ZERO = 0;
    private static Integer ONE = 1;
    private static Integer TWO = 2;
    private static Integer THREE = 3;
    private static Integer FOUR = 4;
    private static Integer FIVE = 5;
    private static Integer SIX = 6;
    private Context mContext;
    public static DBManagerTrack sIntance;
    private String[] allColumns = {ID, IDTRACK, TITLE, DURATION, ARTIST, STREAMURL, ARTWORKURL};

    public DBManagerTrack(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.mContext = context;
    }

    public static DBManagerTrack getIntance(Context context) {
        if (sIntance == null) {
            sIntance = new DBManagerTrack(context);
        }
        return sIntance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " integer primary key, " +
                IDTRACK + " integer, " +
                TITLE + " TEXT, " +
                DURATION + " integer, " +
                ARTIST + " TEXT, " +
                STREAMURL + " TEXT, " +
                ARTWORKURL + " TEXT )";
        sqLiteDatabase.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void addTrack(Track track) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(IDTRACK, track.getId());
        contentValues.put(TITLE, track.getTitle());
        contentValues.put(DURATION, track.getDuration());
        contentValues.put(ARTIST, track.getArtist());
        contentValues.put(STREAMURL, track.getStreamUrl());
        contentValues.put(ARTWORKURL, track.getArtworkUrl());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public List<Track> getAllTrack() {
        List<Track> tracks = new ArrayList<>();
        String selectQuary = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuary, null);

        if (cursor.moveToFirst()) {
            do {
                Track.TrackBuilder trackBuilder = new Track.TrackBuilder();
                trackBuilder.setId(cursor.getInt(ONE));
                trackBuilder.setTitle(cursor.getString(TWO));
                trackBuilder.setDuration(cursor.getInt(THREE));
                trackBuilder.setArtist(cursor.getString(FOUR));
                trackBuilder.setStreamUrl(cursor.getString(FIVE));
                trackBuilder.setArtworkUrl(cursor.getString(SIX));
                tracks.add(trackBuilder.buildTrack());
            }
            while (cursor.moveToNext());
        }
        db.close();
        return tracks;
    }

    public int deleteTrack(Track track) {
        SQLiteDatabase bd = this.getWritableDatabase();
        return bd.delete(TABLE_NAME, IDTRACK + "=?",
                new String[]{String.valueOf(track.getId())});
    }
}
