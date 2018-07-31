package com.cinlife.cingrous.cinlife;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class add_student_from_management_login extends AppCompatActivity {

    private int from_mYear, from_mMonth, from_mDay, to_mYear, to_mMonth, to_mDay;
    TextView date_from, date_to;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Users");
    EditText new_user_name,new_user_address,new_user_phone_number,new_user_email,new_uesr_password,
            new_user_confirm_password,new_user_duration_from_date,new_user_duration_to_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_from_management_login);

        date_from = findViewById(R.id.date_from_from_add_details);
        date_to = findViewById(R.id.date_to_from_add_details);

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
        myRef.setValue("Hello");
        Toast.makeText(add_student_from_management_login.this, "Creating User ....", Toast.LENGTH_LONG).show();

    }
}
