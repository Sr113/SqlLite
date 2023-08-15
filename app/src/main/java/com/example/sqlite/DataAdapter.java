package com.example.sqlite;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    ArrayList<StudentModal> studentModalArrayList;
    Context context;

    public DataAdapter(ArrayList<StudentModal> studentModalArrayList, Context context) {
        this.studentModalArrayList = studentModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentModal modal = studentModalArrayList.get(position);
        holder.RegNo.setText(modal.getRegNo());
        holder.name.setText(modal.getName());
        holder.email.setText(modal.getEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, UpdateActivity.class);
                i.putExtra("reg", modal.getRegNo());
                i.putExtra("name", modal.getName());
                i.putExtra("email", modal.getEmail());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentModalArrayList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView RegNo, name, email;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            RegNo = itemView.findViewById(R.id.idRegNo);
            name = itemView.findViewById(R.id.idName);
            email = itemView.findViewById(R.id.idEmail);
        }
    }
}