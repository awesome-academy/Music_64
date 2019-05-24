package com.sun.music_64.screen.user.favorite;

import android.view.View;

public interface ItemclickRecyclerFavorite {
    void onClick(View view, int position);

    void onShow(View view, int position);

    void onHeartClick(View view, int position);
}
