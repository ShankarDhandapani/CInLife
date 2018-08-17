package com.cinlife.cingrous.cinlife;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

public class SplashActivity extends BaseActivity {

    static public final int REQUEST_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (isOnline()){
            if (isGPSEnabled()){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                }, 3000);
            }else {
                showAlertDialog("Please turn on Location!", SplashActivity.this,"","Try Again");
            }
        }else {
            showAlertDialog("Please Connect to internet!", SplashActivity.this,"","Try Again");
        }
    }
}
