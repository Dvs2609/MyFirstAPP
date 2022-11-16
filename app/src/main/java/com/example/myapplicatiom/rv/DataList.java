package com.example.myapplicatiom.rv;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplicatiom.R;
import com.example.myapplicatiom.db.DbHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class DataList extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> id, dni, name, surname, edad, direc;
    DbHelper sqLiteDatabase;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);

        sqLiteDatabase = new DbHelper(this);

        id = new ArrayList<>();
        dni = new ArrayList<>();
        name = new ArrayList<>();
        surname = new ArrayList<>();
        edad = new ArrayList<>();
        direc = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new RecyclerViewAdapter(this,id, dni, name, surname, edad, direc);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MostrarDatos();


    }

    private void MostrarDatos() {
        Cursor cursor = sqLiteDatabase.getAllData();
        if(cursor.getCount()== 0){
            Toast.makeText(DataList.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                id.add(cursor.getString(0));
                dni.add(cursor.getString(1));
                name.add(cursor.getString(2));
                surname.add(cursor.getString(3));
                edad.add(cursor.getString(4));
                direc.add(cursor.getString(5));
            }
        }
    }
}