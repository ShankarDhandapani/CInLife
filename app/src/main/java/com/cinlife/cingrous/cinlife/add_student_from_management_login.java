package com.cinlife.cingrous.cinlife;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;

public class add_student_from_management_login extends AppCompatActivity {

    private int from_mYear, from_mMonth, from_mDay, to_mYear, to_mMonth, to_mDay;
    TextView date_from, date_to;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth mAuth;
    TextInputEditText new_user_name,new_user_address,new_user_phone_number,new_user_email,new_user_password,
            new_user_confirm_password,college_name,name_of_the_project;
    String name,address,phone_number,email_id,password,confirm_password,duration_from_date,duration_to_date,college,project_name, user_add_Uid;
    TextView new_user_duration_from_date,new_user_duration_to_date;

    RadioGroup gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_from_management_login);
        setTitle(getString(R.string.addUserTitle));

        mAuth = FirebaseAuth.getInstance();

        date_from = findViewById(R.id.date_from_from_add_details);
        date_to = findViewById(R.id.date_to_from_add_details);

        new_user_name = findViewById(R.id.name_of_the_student_yet_to_be_created);
        new_user_address = findViewById(R.id.address_of_the_new_student);
        new_user_phone_number = findViewById(R.id.phone_number_of_the_new_user);
        new_user_email = findViewById(R.id.email_id_of_new_user);
        new_user_password = findViewById(R.id.password_of_the_new_user);
        new_user_confirm_password = findViewById(R.id.confirm_password_of_the_new_user);
        new_user_duration_from_date = findViewById(R.id.date_from_from_add_details);
        new_user_duration_to_date = findViewById(R.id.date_to_from_add_details);
        college_name = findViewById(R.id.college_of_the_student_yet_to_be_created);
        name_of_the_project = findViewById(R.id.name_of_the_project_of_the_student_yet_to_be_created);

    }

    public void from_date_selector(View view) {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        from_mYear = c.get(Calendar.YEAR);
        from_mMonth = c.get(Calendar.MONTH);
        from_mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {


                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        String from_date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        date_from.setText(from_date);

                    }
                }, from_mYear, from_mMonth, from_mDay);
        datePickerDialog.show();
    }

    public void to_date_selector(View view) {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        to_mYear = c.get(Calendar.YEAR);
        to_mMonth = c.get(Calendar.MONTH);
        to_mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {


                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        String to_date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        date_to.setText(to_date);

                    }
                }, to_mYear, to_mMonth, to_mDay);
        datePickerDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_at_add_student_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.cancel_option_at_add_worker_activity:
                cancel_btn();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void cancel_btn() {
        new AlertDialog.Builder(
                add_student_from_management_login.this)
                .setTitle(R.string.cancel)
                .setMessage("Do you want to Cancel?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(add_student_from_management_login.this, Management.class));
                        finish();
                    }
                }).setNegativeButton("No", null)
                .show();
    }

    public void create_user_account(View view) {

        gender = findViewById(R.id.gender_at_add_student);

        // get selected radio button from radioGroup
        int selectedId = gender.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        final RadioButton radioSexButton = findViewById(selectedId);


        Toast.makeText(add_student_from_management_login.this, "Gender : "+gender,Toast.LENGTH_LONG).show();

        final DatabaseReference myRef = database.getReference();

        name = new_user_name.getText().toString().trim();
        address = new_user_address.getText().toString().trim();
        phone_number = new_user_phone_number.getText().toString().trim();
        email_id = new_user_email.getText().toString().trim();
        password = new_user_password.getText().toString().trim();
        confirm_password = new_user_confirm_password.getText().toString().trim();
        duration_from_date = new_user_duration_from_date.getText().toString().trim();
        duration_to_date = new_user_duration_to_date.getText().toString().trim();
        college = college_name.getText().toString().trim();
        project_name = name_of_the_project.getText().toString().trim();

            Toast.makeText(add_student_from_management_login.this, "Creating User ....", Toast.LENGTH_LONG).show();

            mAuth.createUserWithEmailAndPassword(email_id, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser auth = task.getResult().getUser();
                                user_add_Uid = auth.getUid();
                                // Sign in success, update UI with the signed-in user's information

                                myRef.child("Users").child(user_add_Uid).child("Name").setValue(name);
                                myRef.child("Users").child(user_add_Uid).child("Address").setValue(address);
                                myRef.child("Users").child(user_add_Uid).child("Phone Number").setValue(phone_number);
                                myRef.child("Users").child(user_add_Uid).child("Gender").setValue(radioSexButton.getText());
                                myRef.child("Users").child(user_add_Uid).child("Email Id").setValue(email_id);
                                myRef.child("Users").child(user_add_Uid).child("Name of the college").setValue(college);
                                myRef.child("Users").child(user_add_Uid).child("Name of the project").setValue(project_name);
                                myRef.child("Users").child(user_add_Uid).child("Duration").child("From").setValue(duration_from_date);
                                myRef.child("Users").child(user_add_Uid).child("Duration").child("To").setValue(duration_to_date);

                                new AlertDialog.Builder(
                                        add_student_from_management_login.this)
                                        .setTitle(R.string.success)
                                        .setMessage("Account for "+name+" is created successfully.")
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                startActivity(new Intent(add_student_from_management_login.this, Management.class));
                                                finish();
                                            }
                                        })
                                        .show();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(add_student_from_management_login.this, "User not created",
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });


    }
}
