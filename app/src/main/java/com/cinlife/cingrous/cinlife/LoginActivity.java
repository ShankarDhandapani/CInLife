package com.cinlife.cingrous.cinlife;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email_from_login, password_from_login;
    String email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        email_from_login = findViewById(R.id.et_email_from_login);
        password_from_login = findViewById(R.id.et_pass_from_login);

        findViewById(R.id.btn_login_from_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = email_from_login.getText().toString().trim();
                pass = password_from_login.getText().toString().trim();

                if (!email.equals("") || !pass.equals("")) {
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

                }else {
                    Toast.makeText(LoginActivity.this, "Email and Password can't be empty", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentuser = mAuth.getCurrentUser();
        if(currentuser != null)
            updateUI(currentuser);
    }

    private void updateUI(FirebaseUser currentUser) {
        String Uid = currentUser.getUid();
        if (Uid.equals("1HeXJ0snBoW1kfuKOUsKr5OnTQD3")){
            Intent intent1 = new Intent(LoginActivity.this, Management.class);
            startActivity(intent1);
            finish();
            Toast.makeText(LoginActivity.this, "Management Login", Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent2 = new Intent(LoginActivity.this, Student.class);
            startActivity(intent2);
            finish();
        }
        finish();
    }
}
