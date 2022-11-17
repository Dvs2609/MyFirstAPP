package com.example.myapplicatiom.rv;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myapplicatiom.R;
import com.example.myapplicatiom.db.DbHelper;
import com.example.myapplicatiom.db.Persona;

import java.lang.reflect.Method;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.sortbyDesc:
                recyclerView = findViewById(R.id.recyclerview);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

                MostrarDatosOrdDesc();

                adapter = new RecyclerViewAdapter(this, listaPersona);
                recyclerView.setAdapter(adapter);
                return true;

            case R.id.sortbyASC:
                recyclerView = findViewById(R.id.recyclerview);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

                MostrarDatosOrdAsc();

                adapter = new RecyclerViewAdapter(this, listaPersona);
                recyclerView.setAdapter(adapter);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);

        sqLiteDatabase = new DbHelper(this);

        listaPersona = new ArrayList<>();

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
        //Cursor cursor = sqLiteDatabase.SortByAge();
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

    private void MostrarDatosOrdDesc() {

        listaPersona = new ArrayList<>();
        //Cursor cursor = sqLiteDatabase.getAllData();
        Cursor cursor = sqLiteDatabase.SortByAgeDESC();
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

    private void MostrarDatosOrdAsc() {

        listaPersona = new ArrayList<>();
        //Cursor cursor = sqLiteDatabase.getAllData();
        Cursor cursor = sqLiteDatabase.SortByAgeASC();
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