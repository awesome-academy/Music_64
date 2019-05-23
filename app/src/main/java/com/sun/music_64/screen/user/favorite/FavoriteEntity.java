package com.sun.music_64.screen.user.favorite;
import android.annotation.SuppressLint;
import android.support.annotation.IntDef;

@SuppressLint("UniqueConstants")
@IntDef({
        FavoriteEntity.INDEX_DELETE,
        FavoriteEntity.INDEX_TAB_1,
        FavoriteEntity.INDEX_TAB_2,
        FavoriteEntity.INDEX_TAB_3,
})
public @interface FavoriteEntity {
     int INDEX_TAB_1 = 1;
     int INDEX_TAB_2 = 2;
     int INDEX_TAB_3 = 3;
     int INDEX_DELETE = 1;
}