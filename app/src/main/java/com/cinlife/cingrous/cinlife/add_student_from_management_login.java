package com.cinlife.cingrous.cinlife;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cinlife.cingrous.cinlife.model.Model_class;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class add_student_from_management_login extends BaseActivity {

    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;

    private int from_mYear, from_mMonth, from_mDay, to_mYear, to_mMonth, to_mDay;
    TextView date_from, date_to;

    FirebaseAuth mAuth;
    TextInputEditText new_user_name,new_user_address,new_user_phone_number,new_user_email,new_user_password,
            new_user_confirm_password,college_name,name_of_the_project;
    String name,address,phone_number,email_id,password,confirm_password,duration_from_date,duration_to_date,
            worker_type,college,project_name, user_add_Uid;
    TextView new_user_duration_from_date,new_user_duration_to_date;
    TextInputLayout new_user_confirm_password_layout;
    ImageButton mImageView;

    private StorageReference mStorageRef;

    RadioGroup gender,worker_Type;
    Bitmap imageBitmap;

    static final int REQUEST_IMAGE_CAPTURE = 1;


    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_from_management_login);
        setTitle(getString(R.string.addUserTitle));

        mStorageRef = FirebaseStorage.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();

        date_from = findViewById(R.id.date_from_from_add_details);
        date_to = findViewById(R.id.date_to_from_add_details);
        mImageView = findViewById(R.id.student_photo_at_add_worker_tab_in_manager_login);

        new_user_confirm_password_layout = findViewById(R.id.confirm_password_of_the_new_user_layout);
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
        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
        datePickerDialog.setCancelable(false);
        datePickerDialog.show();
    }

    public void to_date_selector(View view) {
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
        datePickerDialog.setCancelable(false);
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

    @SuppressLint("WrongViewCast")
    public void create_user_account(View view) {

        gender = findViewById(R.id.gender_at_add_student);
        int selectedId = gender.getCheckedRadioButtonId();
        final RadioButton radioSexButton = findViewById(selectedId);

        worker_Type = findViewById(R.id.worker_type_at_add_worker);
        int selectId = worker_Type.getCheckedRadioButtonId();
        final RadioButton workerType = findViewById(selectId);
        final String sampleType = workerType.getText().toString();

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



        if(password.equals(confirm_password)){
            mAuth.createUserWithEmailAndPassword(email_id, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser auth = task.getResult().getUser();
                                final FirebaseFirestore db = FirebaseFirestore.getInstance();
                                user_add_Uid = auth.getUid();
                                showProgression(add_student_from_management_login.this,"Uploading Image......","").show();

                                final StorageReference mountainImagesRef = mStorageRef.child("Profile Picture/"+user_add_Uid+".jpg");
                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
                                byte[] data = baos.toByteArray();
                                UploadTask uploadTask = mountainImagesRef.putBytes(data);
                                uploadTask.addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        new AlertDialog.Builder(
                                                add_student_from_management_login.this)
                                                .setMessage("Error in uploading Image.")
                                                .setNegativeButton("OK",null)
                                                .show();

                                    }
                                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        mountainImagesRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {

                                                showProgression(add_student_from_management_login.this,"Uploading Image......","").dismiss();
                                                showProgression(add_student_from_management_login.this,"Creating User......","").show();


                                                String Url = uri.toString().trim();

                                                Model_class  model_class = new Model_class(address, duration_from_date, duration_to_date
                                                        ,email_id, radioSexButton.getText().toString(), sampleType,name, college, project_name, phone_number,Url);

                                                db.collection("Users").document(user_add_Uid).set(model_class).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        showProgression(add_student_from_management_login.this,"Creating User......","").dismiss();
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
                                                    }
                                                });

                                            }
                                        });
                                    }
                                });
                            } else {
                                Toast.makeText(add_student_from_management_login.this, "User not created",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

        }else {
            new_user_confirm_password_layout.setError("Password does not match");

        }
    }


    public void open_camera(View view) {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CAMERA);

        }else{

            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            assert extras != null;
            imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }


}
