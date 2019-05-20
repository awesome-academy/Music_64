package com.sun.music_64.mediaplayer;

import android.support.annotation.IntDef;

@IntDef({ShuffleType.OFF,
        ShuffleType.ON})
public @interface ShuffleType {
    int OFF = 0;
    int ON = 1;
}
