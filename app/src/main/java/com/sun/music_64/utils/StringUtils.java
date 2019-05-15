package com.sun.music_64.utils;

import com.sun.music_64.BuildConfig;

public class StringUtils {
    public static String initGenreApi(String kind, String genre, int limit, int offset) {
        return String.format(Constants.BASE_GENRE_URL
                , kind
                , genre
                , BuildConfig.CLIENT_ID
                , limit
                , offset);
    }

    public static String initSearchApi(String keySearch, int limit, int offset) {
        return String.format(Constants.BASE_SEARCH_URL
                , keySearch
                , BuildConfig.CLIENT_ID
                , limit
                , offset);
    }

    public static String initDownloadUrl(int trackId) {
        return String.format(Constants.BASE_DOWNLOAD_URL
                , trackId
                , BuildConfig.CLIENT_ID);
    }

    public static String initStreamUrl(int trackId) {
        return String.format(Constants.BASE_STREAM_URL
                , trackId
                , BuildConfig.CLIENT_ID);
    }

    private static StringBuilder mStringBuider = new StringBuilder();

    public static String addString(String ...strings) {
        for (int i = 0; i < strings.length; i++) {
            mStringBuider.append(strings[i]);
        }
        return mStringBuider.toString();
    }
}
