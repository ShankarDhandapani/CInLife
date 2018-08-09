package com.cinlife.cingrous.cinlife;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cinlife.cingrous.cinlife.model.Model_class;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class Management extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        setTitle(getString(R.string.management));

        mAuth = FirebaseAuth.getInstance();

        FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName("Management").build();
                    user.updateProfile(profileUpdates);
                }
            }
        };

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_at_management_activity_from_login_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout_from_worker_dash:
                logout();
                return true;
            case R.id.add_student:
                startActivity(new Intent(Management.this,add_student_from_management_login.class));
                return true;
            case R.id.profile_from_management_dash:
                db.collection(mAuth.getUid()).document("profile").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Model_class model_class = documentSnapshot.toObject(Model_class.class);
                        if(model_class != null){
                            view_profile(model_class);
                        }else {
                            AlertDialog alertDialog = new AlertDialog.Builder(
                                    Management.this)
                                    .setMessage("Something Went Wrong !!!")
                                    .setPositiveButton("OK",null)
                                    .show();
                        }
                    }
                });
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void view_profile(Model_class model_class) {

        LayoutInflater inflater = getLayoutInflater();
        @SuppressLint("InflateParams") final View customView = inflater.inflate(R.layout.activity_profile_view,null);
        final ViewGroup parent = (ViewGroup) customView.getParent();
        //final TextInputEditText activity_content = customView.findViewById(R.id.today_s_activity_content_at_out_qr_code_found);

        AlertDialog.Builder alert = new AlertDialog.Builder(Management.this);
        alert.setView(customView);
        alert.setCancelable(true);
        final AlertDialog dialog = alert.create();
        dialog.show();

        TextView addressDisplay = customView.findViewById(R.id.address_of_the_current_user);
        addressDisplay.setText(model_class.getAddress());
        TextView fromDurationDisplay = customView.findViewById(R.id.from_date_of_the_current_user);
        fromDurationDisplay.setText(model_class.getFrom_duration());
        TextView toDurationDisplay = customView.findViewById(R.id.to_date_of_the_current_user);
        toDurationDisplay.setText(model_class.getTo_duration());
        TextView emailDisplay = customView.findViewById(R.id.Email_id_of_the_current_user);
        emailDisplay.setText(model_class.getEmail());
        TextView genderDisplay = customView.findViewById(R.id.gender_of_the_current_user);
        genderDisplay.setText(model_class.getGender());
        TextView nameDisplay = customView.findViewById(R.id.Name_of_the_current_user);
        nameDisplay.setText(model_class.getName());
        TextView collegeDisplay = customView.findViewById(R.id.name_of_the_college_of_the_current_user);
        collegeDisplay.setText(model_class.getCollege());
        TextView projectDisplay = customView.findViewById(R.id.name_of_the_project_of_the_current_user);
        projectDisplay.setText(model_class.getProject());
        TextView phoneNumberDisplay = customView.findViewById(R.id.phone_number_of_the_current_user);
        phoneNumberDisplay.setText(model_class.getPhone());
    }

    private void logout() {
        AlertDialog alertDialog = new AlertDialog.Builder(
                Management.this)
                .setTitle(R.string.logout)
                .setMessage("Are you sure.Do you want to Logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAuth.signOut();
                        startActivity(new Intent(Management.this, LoginActivity.class));
                        finish();
                    }
                }).setNegativeButton("No", null)
                .show();
    }




}
