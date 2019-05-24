package com.sun.music_64.screen;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private static final long TIME_DELAY = 2000;
    private final static int CHECK_PER_ID = 69;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (checkAndRequestPermissions()) {
            nextToMain();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        int permission = 0;
        if (requestCode == CHECK_PER_ID) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED)
                    permission++;
            }
            if (permission == grantResults.length)
                nextToMain();
            else {
                finish();
            }
        }
    }

    private boolean checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int writePhoneStatePermissions = ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE");
            List<String> listPermissionsNeeded = new ArrayList();
            if (writePhoneStatePermissions != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add("android.permission.WRITE_EXTERNAL_STORAGE");
            }
            if (listPermissionsNeeded.isEmpty()) {
                return true;
            }
            ActivityCompat.requestPermissions(this, (String[]) listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), CHECK_PER_ID);
        }
        return false;
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
