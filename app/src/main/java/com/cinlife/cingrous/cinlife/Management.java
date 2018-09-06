package com.cinlife.cingrous.cinlife;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cinlife.cingrous.cinlife.model.Model_class;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Management extends BaseActivity {

    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ExpandableListView expandableListView;

    private int lastExpandedPosition = -1;
    private int from_mYear;
    private int from_mMonth;
    private int from_mDay;
    private TextView view_entry_on_text_view;

    Date date = new Date();
    @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy");
    String formattedDate = dateFormat.format(date.getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        setTitle(getString(R.string.management));

        expandableListView = findViewById(R.id.expandable_user_view_at_management_activity);

        mAuth = FirebaseAuth.getInstance();
        view_entry_on_text_view = findViewById(R.id.view_entry_on_text_view);
        view_entry_on_text_view.setText(formattedDate);

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
                Task<DocumentSnapshot> documentSnapshotTask = db.collection("Users").document(mAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Model_class model_class = new Model_class(document.getData());
                                view_profile(model_class);
                            }
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

        ImageView profilePictureDisplay = (ImageView) customView.findViewById(R.id.user_profile_picture_at_student_or_management_login);

        Picasso.with(this)
                .load(model_class.getProfilePicture())
                .transform(new CircleTransform())
                .into(profilePictureDisplay);

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
        TextView workerTypeDisplay = customView.findViewById(R.id.worker_type_of_the_current_user);
        workerTypeDisplay.setText(model_class.getWorker_type());
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

    public void view_entry_on(View view) {
        final HashMap<String, List<String>> expandableListDetail = new HashMap<>();

        final Calendar c = Calendar.getInstance();

        from_mYear = c.get(Calendar.YEAR);
        from_mMonth = c.get(Calendar.MONTH);
        from_mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {


                    @Override
                    public void onDateSet(DatePicker view, int years,
                                          int monthOfYear, int dayOfMonth) {

                        String date = dayOfMonth+"";
                        String month = ""+(monthOfYear + 1);
                        String year =  years+"";
                        String entry_on = date+"-"+month+"-"+year;
                        view_entry_on_text_view.setText(entry_on);
                        Toast.makeText(Management.this,entry_on,Toast.LENGTH_SHORT).show();

                        db.collection(year).document(month).collection(date).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            Map<String, Object> name_map;

                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                final List<String> expandableListContent = new ArrayList<>();
                                for(QueryDocumentSnapshot document : task.getResult()){
                                    Map<String, Object> entry = document.getData();
                                    String userId = document.getId();

                                    expandableListContent.clear();
                                    expandableListContent.add("In Time : "+entry.get("in_time"));
                                    expandableListContent.add("Out Time : "+entry.get("out_time"));
                                    expandableListContent.add("Activity : "+entry.get("activity"));

                                    db.collection("Users").document(userId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            if(task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    /*Model_class model_class = new Model_class(document.getData());
                                                    view_profile(model_class);*/

                                                    name_map = document.getData();
                                                    assert name_map != null;
                                                    Log.d("test", (String) name_map.get("name"));
                                                    String user_name = name_map.get("name").toString().trim();

                                                    expandableListDetail.put(user_name, expandableListContent);

                                                }
                                                List<String> expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
                                                //Log.d("Test 1", String.valueOf(expandableListDetail));

                                                ExpandableListAdapter expandableListAdapter = new CustomExpandableListAdapter(Management.this, expandableListTitle, expandableListDetail);
                                                expandableListView.setAdapter(expandableListAdapter);
                                            }

                                        }
                                    });

                                }

                            }
                        });
                        //paste here if error

                        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                            @Override
                            public void onGroupExpand(int groupPosition) {

                                if (lastExpandedPosition != -1
                                        && groupPosition != lastExpandedPosition) {
                                    expandableListView.collapseGroup(lastExpandedPosition);
                                }
                                lastExpandedPosition = groupPosition;

                            }
                        });

                        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

                            @Override
                            public void onGroupCollapse(int groupPosition) {

                            }
                        });

                        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                            @Override
                            public boolean onChildClick(ExpandableListView parent, View v,
                                                        int groupPosition, int childPosition, long id) {


                                return false;
                            }
                        });

                    }
                }, from_mYear, from_mMonth, from_mDay);
        datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
        datePickerDialog.setCancelable(false);
        datePickerDialog.show();

    }
}
