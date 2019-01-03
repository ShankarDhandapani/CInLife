package com.cinlife.cingrous.cinlife;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    public static final int PERMISSIONS_MULTIPLE_REQUEST = 123;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    public ProgressDialog showProgression(Context context, String message, String title) {
        ProgressDialog progressDoalog = new ProgressDialog(context);
        progressDoalog.setMessage(message);
        progressDoalog.setTitle(title);
        progressDoalog.setCancelable(false);
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        return progressDoalog;
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public void checkAndroidPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.INTERNET) + ContextCompat
                    .checkSelfPermission(getApplicationContext(),
                            Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.INTERNET}, PERMISSIONS_MULTIPLE_REQUEST);
            }
        }
    }

    public void showAlertDialog(String message, Context context, String title, String button_text) {
        new AlertDialog.Builder(
                context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton(button_text, null)
                .show();

    }

}
