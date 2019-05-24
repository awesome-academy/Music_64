package com.sun.music_64.screen.nowplaying;

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);

    void onItemMoved();
}
