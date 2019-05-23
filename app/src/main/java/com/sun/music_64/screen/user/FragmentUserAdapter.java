package com.sun.music_64.screen.user;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sun.music_64.screen.user.FragmentUserItem;

public class FragmentUserAdapter extends FragmentPagerAdapter {
    private final static int COUNT_FRAGMENT = 3;

    public FragmentUserAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return FragmentUserItem.newInstance(i + 1);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public int getCount() {
        return COUNT_FRAGMENT;
    }
}
