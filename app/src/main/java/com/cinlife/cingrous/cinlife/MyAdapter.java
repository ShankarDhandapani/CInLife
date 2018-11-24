package com.cinlife.cingrous.cinlife;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cinlife.cingrous.cinlife.model.Employee_Entry;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

class MyAdapter extends FirestoreRecyclerAdapter<Employee_Entry, MyAdapter.ViewHolder>{
    public MyAdapter(FirestoreRecyclerOptions<Employee_Entry> options)  {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Employee_Entry model) {
        holder.in_time.setText(model.getIn_time());
        holder.out_time.setText(model.getOut_time());
        holder.activity.setText(model.getActivity());
        holder.employee_name.setText(model.getName());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_card, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView in_time, out_time, activity, employee_name;

        public ViewHolder(View itemView) {
            super(itemView);
            employee_name = itemView.findViewById(R.id.employee_Id_at_each_card);
            in_time = itemView.findViewById(R.id.in_time_at_each_card_view);
            out_time = itemView.findViewById(R.id.out_time_at_each_card_view);
            activity = itemView.findViewById(R.id.activity_at_each_card_view);
        }
    }
}
