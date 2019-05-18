package com.sun.music_64.utils;

import com.sun.music_64.BuildConfig;

public class Constants {
    public static final String BASE_GENRE_URL
            = "https://api-v2.soundcloud.com/charts?kind=%s&genre=%s&client_id=%s&limit=%d&offset=%d";
    public static final String BASE_SEARCH_URL
            = "http://api.soundcloud.com/tracks?q=%s&client_id=%s";
    public static final String BASE_DOWNLOAD_URL
            = "https://api.soundcloud.com/tracks/%d/download?client_id=%s";
    public static final String BASE_STREAM_URL
            = "https://api.soundcloud.com/tracks/%d/stream?client_id=%s";
    public static final String CLIENT_ID = BuildConfig.CLIENT_ID;
    public final static String URL_ID = "id";
    public final static String URL_TITLE = "title";
    public final static String URL_COLLECTION = "collection";
    public final static String URL_TRACK = "track";
    public final static String URL_USER = "user";
    public final static String URL_USER_NAME = "username";
    public final static String URL_DUARTION = "duration";
    public final static String URL_DOWNLOADABLE = "downloadable";
    public final static String URL_ARTWORK = "artwork_url";
    public final static String URL_AVATAR = "avatar_url";
    public final static String URL_USER_KEY = "User_Agent";
    public final static String URL_USER_VALUE = "my-rest-app-v0.1";
    public final static int URL_REQUEST_TIME = 3000;
    public final static int URL_REQUEST_CODE = 200;
    public final static String URL_REQUEST_METHOD = "GET";
}
