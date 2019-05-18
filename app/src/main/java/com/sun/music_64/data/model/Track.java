package com.sun.music_64.data.model;

import java.io.Serializable;

public class Track implements Serializable {

    private int mId;
    private String mTitle;
    private long mDuration;
    private String mArtist;
    private String mStreamUrl;
    private String mDownloadUrl;
    private String mArtworkUrl;
    private boolean mIsDownloadable;


    public Track(TrackBuilder trackBuilder) {
        mId = trackBuilder.mId;
        mTitle = trackBuilder.mTitle;
        mDuration = trackBuilder.mDuration;
        mArtist = trackBuilder.mArtist;
        mStreamUrl = trackBuilder.mStreamUrl;
        mDownloadUrl = trackBuilder.mDownloadUrl;
        mArtworkUrl = trackBuilder.mArtworkUrl;
        mIsDownloadable = trackBuilder.mIsDownloadable;
    }

    public static class TrackBuilder {
        private int mId;
        private String mTitle;
        private long mDuration;
        private String mArtist;
        private String mStreamUrl;
        private String mDownloadUrl;
        private String mArtworkUrl;
        private boolean mIsDownloadable;

        public void setId(int id) {
            mId = id;
        }

        public void setTitle(String title) {
            mTitle = title;
        }

        public void setDuration(long duration) {
            mDuration = duration;
        }

        public void setArtist(String artist) {
            mArtist = artist;
        }

        public void setStreamUrl(String streamUrl) {
            mStreamUrl = streamUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            mDownloadUrl = downloadUrl;
        }

        public void setArtworkUrl(String artworkUrl) {
            mArtworkUrl = artworkUrl;
        }

        public void setDownload(boolean download) {
            mIsDownloadable = download;
        }

        public Track buildTrack() {
            return new Track(this);
        }

    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public long getDuration() {
        return mDuration;
    }

    public String getArtist() {
        return mArtist;
    }

    public String getStreamUrl() {
        return mStreamUrl;
    }

    public String getDownloadUrl() {
        return mDownloadUrl;
    }

    public String getArtworkUrl() {
        return mArtworkUrl;
    }

    public boolean isDownloadable() {
        return mIsDownloadable;

    }
}
