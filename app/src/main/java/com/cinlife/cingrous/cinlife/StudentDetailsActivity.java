package com.cinlife.cingrous.cinlife;

import android.content.Intent;
import android.os.Bundle;

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
