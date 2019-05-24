package com.sun.music_64.screen.user;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sun.music_64.R;
import com.sun.music_64.screen.user.favorite.FavoriteEntity;

public class UserFragment extends Fragment {

    public static UserFragment sInstance;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public static UserFragment getInstance() {
        if (sInstance == null) {
            sInstance = new UserFragment();
        }
        return sInstance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        init(view);
        createViewPagerInstance();
        createTabLayout();
        return view;
    }

    public void init(View view) {
        mTabLayout = view.findViewById(R.id.tab_user);
        mViewPager = view.findViewById(R.id.viewpager_user);
    }

    public void createTabLayout() {
        mTabLayout.setupWithViewPager(mViewPager);
        setIconTabStandard();
        setIconTabLoading();
    }

    public void createViewPagerInstance() {
        FragmentUserAdapter adapterFragmentViewPager = new FragmentUserAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(adapterFragmentViewPager);
    }

    @SuppressLint("ResourceAsColor")
    public void setIconTabStandard() {
        mTabLayout.getTabAt(FavoriteEntity.INDEX_TAB_1).setIcon(R.drawable.ic_favorite_black_24dp).select();
        mTabLayout.getTabAt(FavoriteEntity.INDEX_TAB_2).setIcon(R.drawable.ic_download);
        mTabLayout.getTabAt(FavoriteEntity.INDEX_TAB_3).setIcon(R.drawable.ic_phone_android_black_24dp);
    }

    public void setIconTabLoading() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(getActivity().getApplicationContext(), R.color.orange);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(getActivity().getApplicationContext(), R.color.color_black);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}
