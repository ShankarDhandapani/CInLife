package com.cinlife.cingrous.cinlife;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cinlife.cingrous.cinlife.model.Model_class;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class StudentDetailsActivity extends BaseActivity {

    private MyUserDeatilsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        setTitle("Users Profile");

        /*ProgressDialog progressDialog = showProgression(StudentDetailsActivity.this, "Fetching Users..", "Loading" );
        progressDialog.show();

        RecyclerView recyclerView = findViewById(R.id.recyclerview_from_worker_details);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(StudentDetailsActivity.this));

        Query query = FirebaseFirestore.getInstance()
                .collection("Users")
                .orderBy("name");

        FirestoreRecyclerOptions<Model_class> options = new FirestoreRecyclerOptions.Builder<Model_class>()
                .setQuery(query, Model_class.class)
                .build();

        adapter = new MyUserDeatilsAdapter(options);
        progressDialog.dismiss();
        recyclerView.setAdapter(adapter);
        adapter.startListening();
*/
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(StudentDetailsActivity.this, Management.class));
        finish();
    }
}
