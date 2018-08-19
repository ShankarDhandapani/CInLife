package com.cinlife.cingrous.cinlife;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class SplashActivity extends BaseActivity {

    static public final int REQUEST_LOCATION = 1;
    private FirebaseFirestore myDBRef = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (isOnline()){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    FirebaseUser currentuser = mAuth.getCurrentUser();
                    if (currentuser != null) {
                        myDBRef.collection("Users").document(currentuser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        Map<String, Object> data = document.getData();
                                        assert data != null;
                                        if (data.get("worker_type").equals("Manager")) {
                                            updateUI("manager");
                                        } else if (data.get("worker_type").equals("Employee")) {
                                            updateUI("worker");
                                        }
                                    }
                                }
                            }
                        });
                    }else {
                        updateUI(null);
                    }

                }
            }, 3000);
        }else {
            showAlertDialog("Please connect to the Internet.",SplashActivity.this,"","Try Again");
        }
    }
    private void updateUI(String i) {
        if (i != null){
            if (i.equals("manager")){
                startActivity(new Intent(SplashActivity.this, Management.class));
            }
            if (i.equals("worker")){
                startActivity(new Intent(SplashActivity.this, Student.class));
            }
        }
        else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }
        finish();
    }
}