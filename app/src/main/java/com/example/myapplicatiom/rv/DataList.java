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
import com.example.myapplicatiom.db.Persona;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class DataList extends AppCompatActivity {
    RecyclerView recyclerView;
    //ArrayList<String> personas, id, dni, name, surname, edad, direc;
    ArrayList<Persona> listaPersona;
    DbHelper sqLiteDatabase;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);

        sqLiteDatabase = new DbHelper(this);

        listaPersona = new ArrayList<>();

        /*id = new ArrayList<>();
        dni = new ArrayList<>();
        name = new ArrayList<>();
        surname = new ArrayList<>();
        edad = new ArrayList<>();
        direc = new ArrayList<>();*/

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //adapter = new RecyclerViewAdapter(this,id, dni, name, surname, edad, direc);
        MostrarDatos();

        adapter = new RecyclerViewAdapter(this, listaPersona);
        recyclerView.setAdapter(adapter);
        
    }

    private void MostrarDatos() {

        listaPersona = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.getAllData();

        if(cursor.getCount()== 0){
            Toast.makeText(DataList.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                Persona p = new Persona();
                p.setId(cursor.getString(0));
                p.setDni(cursor.getString(1));
                p.setNombre(cursor.getString(2));
                p.setApellido(cursor.getString(3));
                p.setEdad(cursor.getString(4));
                p.setDireccion(cursor.getString(5));
                listaPersona.add(p);
            }
        }

    }
}