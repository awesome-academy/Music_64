package com.sun.music_64.data.model;

import java.io.Serializable;

public class Track implements Serializable {
    private int mId;
    private String mGenre;
    private String mTitle;
    private long mDuration;
    private String mArtist;
    private String mStreamUrl;
    private String mDownloadUrl;
    private String mArtworkUrl;
    private String mDescription;
    private boolean mIsDownloadable;
    private boolean mIsFavourite;

    public Track() {
    }

    public Track(int id, String title, String user) {
        mId = id;
        mTitle = title;
        mArtist = user;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String genre) {
        mGenre = genre;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public long getDuration() {
        return mDuration;
    }

    public void setDuration(long duration) {
        mDuration = duration;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public String getStreamUrl() {
        return mStreamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        mStreamUrl = streamUrl;
    }

    public String getDownloadUrl() {
        return mDownloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        mDownloadUrl = downloadUrl;
    }

    public String getArtworkUrl() {
        return mArtworkUrl;
    }

    public void setArtworkUrl(String artworkUrl) {
        mArtworkUrl = artworkUrl;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public boolean isDownload() {
        return mIsDownloadable;
    }

    public void setDownload(boolean download) {
        mIsDownloadable = download;
    }

    public boolean isFavourite() {
        return mIsFavourite;
    }

    public void setFavourite(boolean favourite) {
        mIsFavourite = favourite;
    }
}
