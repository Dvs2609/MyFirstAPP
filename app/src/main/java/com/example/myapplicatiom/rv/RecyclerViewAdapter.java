package com.example.myapplicatiom.rv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicatiom.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private ArrayList id_id, dni_id, name_id, surname_id, edad_id, direc_id;

    public RecyclerViewAdapter(Context context,ArrayList id_id, ArrayList dni_id, ArrayList name_id, ArrayList surname_id, ArrayList edad_id, ArrayList direc_id) {
        this.context = context;
        this.id_id = id_id;
        this.dni_id = dni_id;
        this.name_id = name_id;
        this.surname_id = surname_id;
        this.edad_id = edad_id;
        this.direc_id = direc_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layoutlist_rv,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id_id.setText(String.valueOf(id_id.get(position)));
        holder.dni_id.setText(String.valueOf(dni_id.get(position)));
        holder.name_id.setText(String.valueOf(name_id.get(position)));
        holder.surname_id.setText(String.valueOf(surname_id.get(position)));
        holder.edad_id.setText(String.valueOf(edad_id.get(position)));
        holder.direc_id.setText(String.valueOf(direc_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return id_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  id_id, dni_id, name_id, surname_id, edad_id, direc_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_id = itemView.findViewById(R.id.TVidShow);
            dni_id = itemView.findViewById(R.id.TVdniShow);
            name_id = itemView.findViewById(R.id.TVnameShow);
            surname_id = itemView.findViewById(R.id.TVsurnameShow);
            edad_id = itemView.findViewById(R.id.TVedadShow);
            direc_id = itemView.findViewById(R.id.TVdirecShow);
        }
    }
}
