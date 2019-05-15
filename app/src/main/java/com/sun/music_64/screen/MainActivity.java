package com.sun.music_64.screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.sun.music_64.R;
import com.sun.music_64.screen.home.HomeFragment;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView mBottomNavigationView;
    private FragmentManager mManager;

    public static Intent getIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mManager = getSupportFragmentManager();
        mManager.beginTransaction()
                .replace(R.id.frame_main, HomeFragment.getInstance())
                .commit();
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_home:
                mManager.beginTransaction().replace(R.id.frame_main, HomeFragment.getInstance())
                        .commit();
                break;
            case R.id.menu_user:
                break;
            default:
                break;
        }
        return true;
    }
}
