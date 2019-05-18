package com.sun.music_64.screen.genrescreen;

import android.support.annotation.StringDef;

@StringDef({
        GenreEntity.KIND_TREND,
        GenreEntity.GENRES_ALL_MUSIC,
        GenreEntity.GENRES_ALL_AUDIO,
        GenreEntity.GENRES_ROCK,
        GenreEntity.GENRES_AMBIENT,
        GenreEntity.GENRES_CLASSICAL,
        GenreEntity.GENRES_COUNTRY,
})
public @interface GenreEntity {
    String KIND_TREND = "trending";
    String GENRES_ALL_MUSIC = "soundcloud:genres:all-music";
    String GENRES_ALL_AUDIO = "soundcloud:genres:all-audio";
    String GENRES_ROCK = "soundcloud:genres:alternativerock";
    String GENRES_AMBIENT = "soundcloud:genres:ambient";
    String GENRES_CLASSICAL = "soundcloud:genres:classical";
    String GENRES_COUNTRY = "soundcloud:genres:country";
}
