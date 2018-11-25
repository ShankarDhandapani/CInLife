package com.cinlife.cingrous.cinlife;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;
import java.util.Objects;

public class LoginActivity extends BaseActivity {

    RelativeLayout myLayout;
    AnimationDrawable animationDrawable;

    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText email_from_login, password_from_login;
    String email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myLayout = findViewById(R.id.myLayout);
        animationDrawable = (AnimationDrawable) myLayout.getBackground();
        animationDrawable.setEnterFadeDuration(3000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();

        mAuth = FirebaseAuth.getInstance();

        email_from_login = findViewById(R.id.et_email_from_login);
        password_from_login = findViewById(R.id.et_pass_from_login);

        findViewById(R.id.btn_login_from_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showProgression(LoginActivity.this, "Logging In......", "").show();


                email = email_from_login.getText().toString().trim();
                pass = password_from_login.getText().toString().trim();

                if (!email.equals("") && !pass.equals("")) {

                    mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                assert user != null;
                                updateUI(user);
                            } else {
                                Toast.makeText(LoginActivity.this, "Authentication Failed!!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(LoginActivity.this, "Email and Password can't be empty", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentuser = mAuth.getCurrentUser();
        if (currentuser != null)
            updateUI(currentuser);
    }

    private void updateUI(FirebaseUser currentUser) {
        String Uid = currentUser.getUid();
        db.collection("Users").document(Uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    assert document != null;
                    if (document.exists()) {
                        Map<String, Object> data = document.getData();
                        assert data != null;
                        if (Objects.equals(data.get("worker_type"), "Manager")) {
                            startActivity(new Intent(LoginActivity.this, Management.class));
                            finish();
                            Toast.makeText(LoginActivity.this, "Management Login", Toast.LENGTH_LONG).show();
                        }
                        if (Objects.equals(data.get("worker_type"), "Employee")) {
                            startActivity(new Intent(LoginActivity.this, Student.class));
                            finish();
                            Toast.makeText(LoginActivity.this, "Worker Login", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
        showProgression(LoginActivity.this, "Logging In......", "").dismiss();
    }
}
