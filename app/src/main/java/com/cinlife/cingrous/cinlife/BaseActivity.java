package com.cinlife.cingrous.cinlife;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    public ProgressDialog showProgression(Context context,String message,String title){
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

    protected boolean isGPSEnabled(){
        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
        return manager.isProviderEnabled( LocationManager.GPS_PROVIDER);
    }


    public void showAlertDialog(String message, Context context,String title,String button_text){
        new AlertDialog.Builder(
                context)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(button_text, null)
                .show();
    }

}
