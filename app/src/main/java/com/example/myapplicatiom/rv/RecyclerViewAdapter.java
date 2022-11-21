package com.example.myapplicatiom.rv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplicatiom.Character;
import com.example.myapplicatiom.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    List<Character> listaPersona;


    public RecyclerViewAdapter(Context context, List<Character> listaPersona) {
        this.context = context;
        this.listaPersona = listaPersona;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layoutlist_rv,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(listaPersona.get(position).getImg()).into(holder.img);
        //holder.img.ima(String.valueOf(listaPersona.get(position).getImg()));
        holder.name.setText(String.valueOf(listaPersona.get(position).getName()));
        holder.birth.setText(String.valueOf(listaPersona.get(position).getBirthday()));
        holder.nickname.setText(String.valueOf(listaPersona.get(position).getNickname()));
    }

    @Override
    public int getItemCount() {
        return listaPersona.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, birth, nickname;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.TVidShow);
            birth = itemView.findViewById(R.id.TVbirthShow);
            nickname = itemView.findViewById(R.id.TVNickShow);

        }
    }

}
