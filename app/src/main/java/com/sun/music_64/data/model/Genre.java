package com.sun.music_64.data.model;

public class Genre {
    private String mName;
    private String mKey;
    private int mImageId;
    public Genre(String name, String key, int id) {
        mName = name;
        mKey = key;
        mImageId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public int getImageId() {
        return mImageId;
    }

    public void setImageId(int id) {
        mImageId = id;
    }
}
