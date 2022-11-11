package com.example.myapplicatiom;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolderImage> {

    //private ItemData[] itemsData;
    List<ItemData> itemsData = Collections.emptyList();
    Context context;


    public ImageAdapter(List<ItemData> data, Application application) {
        this.itemsData = data;
        this.context = application;
    }
    /*
    public ImageAdapter(ItemData[] itemsData) {
        this.itemsData = itemsData;
    }*/

    @NonNull
    @Override
    public ViewHolderImage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_image, null);


        return new ViewHolderImage(view);

    }

    @Override
    //Establece comunicación entre nuestro adaptador y la clase ViewHolderImage
    public void onBindViewHolder(@NonNull ViewHolderImage holder, int position) {

        TextView text;
        ImageView image;

        //holder.asignarDatos(listDatos.get(position));
        holder.txtView.setText(itemsData.get(position).getTitle());
        holder.imgView.setImageResource(itemsData.get(position).getImage());

        holder.itemView.setOnClickListener(view -> {

            Intent intent = new Intent(context, MainActivity3.class);
            intent.putExtra("title", itemsData.get(position).getTitle());
            intent.putExtra("image", itemsData.get(position).getImage());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //Solución error
            //FLAG_ACTIVITY_CLEAR_TOP limpia todas las activities
            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }

    public class ViewHolderImage extends RecyclerView.ViewHolder {

        public TextView txtView;
        public ImageView imgView;


        public ViewHolderImage(@NonNull View itemView) {
            super(itemView);
            txtView = itemView.findViewById(R.id.text_item);
            imgView = itemView.findViewById(R.id.image_item);
        }


    }
}