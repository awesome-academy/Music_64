package com.sun.music_64.screen.user.favorite;

import com.sun.music_64.data.model.Track;

import java.util.HashMap;

public class FavoriteList {
    private static FavoriteList instance;
    private HashMap<Track, Integer> favoritelist = new HashMap<>();

    private FavoriteList() {
    }

    public static FavoriteList getInstance() {
        if (instance == null) {
            instance = new FavoriteList();
        }
        return instance;
    }

    public void addTrack(Track track, Integer id) {
        favoritelist.put(track, id);
    }

    public HashMap<Track, Integer> getCart() {
        return favoritelist;
    }

    public void clearCart() {
        favoritelist.clear();
    }
}
