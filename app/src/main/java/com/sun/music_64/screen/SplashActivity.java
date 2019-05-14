package com.sun.music_64.screen;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sun.music_64.R;

public class SplashActivity extends AppCompatActivity {
    private static final long TIME_DELAY = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nextToMain();
    }

    private void nextToMain() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(MainActivity.getIntent(SplashActivity.this));
            }
        }, TIME_DELAY);
    }

}
