package com.sun.music_64.mediaplayer;

import android.support.annotation.IntDef;

@IntDef({
        LoopType.ZERO,
        LoopType.ONE,
        LoopType.ALL,
})
public @interface LoopType {
    int ZERO = 0;
    int ONE = 1;
    int ALL = 2;
}
