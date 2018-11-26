package com.cinlife.cingrous.cinlife;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cinlife.cingrous.cinlife.model.Model_class;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

public class MyUserDeatilsAdapter extends FirestoreRecyclerAdapter<Model_class, MyUserDeatilsAdapter.ViewHolder> {


    MyUserDeatilsAdapter(@NonNull FirestoreRecyclerOptions<Model_class> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Model_class model) {
        Picasso.with(holder.itemView.getContext())
                .load(model.getProfilePicture())
                .transform(new CircleTransform())
                .into(holder.imageView);

        holder.name.setText(model.getName());
        holder.email.setText(model.getEmail());
        holder.phoneno.setText(model.getPhone());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView name, email, phoneno;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.worker_profile_from_item);
            name = itemView.findViewById(R.id.worker_name_from_item);
            email = itemView.findViewById(R.id.worker_email_from_item);
            phoneno = itemView.findViewById(R.id.worker_phoneno_from_item);
        }
    }
}
