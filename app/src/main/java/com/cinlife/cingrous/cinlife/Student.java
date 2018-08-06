package com.cinlife.cingrous.cinlife;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String user_add_Uid = user.getUid();
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        setTitle(getString(R.string.student));

        mAuth = FirebaseAuth.getInstance();


        findViewById(R.id.float_btn_qr_scan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IntentIntegrator(Student.this).setCaptureActivity(ScannerActivity.class).initiateScan();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_at_student_activity_from_login_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout_from_worker_dash:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        AlertDialog alertDialog = new AlertDialog.Builder(
                Student.this)
                .setTitle(R.string.logout)
                .setMessage("Are you sure.Do you want to Logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAuth.signOut();
                        startActivity(new Intent(Student.this, LoginActivity.class));
                        finish();
                    }
                }).setNegativeButton("No", null)
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //We will get scan results here
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        //check for null
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Scan Cancelled", Toast.LENGTH_LONG).show();
            } else {

                Date date = new Date();
                DateFormat timeFormat = new SimpleDateFormat("h:mm a");
                DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                final String formattedTime = timeFormat.format(date.getTime());
                final String formattedDate = dateFormat.format(date.getTime());
                //show dialogue with result
                if(result.getContents().equals("cingrous_in")){
                    myRef.child("User Log").child(formattedDate).child(user_add_Uid).child("In Time").setValue(formattedTime);

                    AlertDialog alertDialog = new AlertDialog.Builder(
                            Student.this)
                            .setTitle(R.string.welcome)
                            .setMessage("Welcome to Cingrous Labs.Time : "+formattedTime)
                            .setPositiveButton("Done",null)
                            .show();

                }
                if(result.getContents().equals("cingrous_out")){
                    LayoutInflater inflater = getLayoutInflater();
                    @SuppressLint("InflateParams") final View customView = inflater.inflate(R.layout.todays_task_activity, null);
                    final ViewGroup parent = (ViewGroup) customView.getParent();
                    final TextInputEditText activity_content = customView.findViewById(R.id.today_s_activity_content_at_out_qr_code_found);

                    AlertDialog.Builder alert = new AlertDialog.Builder(Student.this);
                    alert.setView(customView);
                    alert.setCancelable(true);
                    final AlertDialog dialog = alert.create();
                    dialog.show();

                    customView.findViewById(R.id.today_s_activity_submit_button_at_out_qr_code_found).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            myRef.child("User Log").child(formattedDate).child(user_add_Uid).child("Out Time").setValue(formattedTime);
                            myRef.child("User Log").child(formattedDate).child(user_add_Uid).child("Activity").setValue(activity_content.getText().toString().trim());
                            Toast.makeText(Student.this,"Your Entry submitted successfully.",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });

                }
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }



}
