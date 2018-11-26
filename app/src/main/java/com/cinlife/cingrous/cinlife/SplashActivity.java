package com.cinlife.cingrous.cinlife;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;
import java.util.Objects;

public class SplashActivity extends BaseActivity {
    private FirebaseFirestore myDBRef = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (isOnline()) {
            checkAndroidPermission();
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
                                    assert document != null;
                                    if (document.exists()) {
                                        Map<String, Object> data = document.getData();
                                        assert data != null;
                                        if (Objects.equals(data.get("worker_type"), "Manager")) {
                                            updateUI("manager");
                                        } else if (Objects.equals(data.get("worker_type"), "Employee")) {
                                            updateUI("worker");
                                        }
                                    }
                                }
                            }
                        });
                    } else {
                        updateUI(null);
                    }

                }
            }, 3000);
        } else {
            showAlertDialog("Please connect to the Internet.", SplashActivity.this, "", "Try Again");
        }
    }


    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }

    private void updateUI(String i) {
        if (i != null) {
            if (i.equals("manager")) {
                startActivity(new Intent(SplashActivity.this, Management.class));
            }
            if (i.equals("worker")) {
                startActivity(new Intent(SplashActivity.this, Student.class));
            }
        } else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }
        finish();
    }
}