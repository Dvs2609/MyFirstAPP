package com.example.myapplicatiom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    //private ItemData[] itemsData;
    //private ItemData item;

    List<ItemData> itemsData = Collections.emptyList();
    RecyclerView recycler;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //RECYCLER VIEW
        recycler = findViewById(R.id.recyclerID);

        List<ItemData> data = AsignarDatos();
/*
        for(int i = 0;i < 4;i++) {
            itemsData[i] = new  ItemData("dato: "+i+" ", R.id.image_item);
        }
*/
        ImageAdapter Adapter = new ImageAdapter(data, getApplication());
        recycler.setAdapter(Adapter);
        //recycler.seton
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //recycler.setLayoutManager(new GridLayoutManager(this,2));

    }

    private List<ItemData> AsignarDatos() {
        List<ItemData> data = new ArrayList<>();
        data.add(new ItemData("Hola", R.drawable.atosimage));
        data.add(new ItemData("Esta", R.drawable.atosimage));
        data.add(new ItemData("Es mi", R.drawable.atosimage));
        data.add(new ItemData("Aplicacion", R.drawable.atosimage));
        data.add(new ItemData("RecyclerView", R.drawable.atosimage));


        return data;
    }
}