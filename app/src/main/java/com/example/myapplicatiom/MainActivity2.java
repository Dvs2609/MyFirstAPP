package com.example.myapplicatiom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplicatiom.db.DbHelper;
import com.example.myapplicatiom.db.database;
import com.example.myapplicatiom.rv.DataList;


public class MainActivity2 extends AppCompatActivity {

    EditText dni, name,surname,edad , direc;
    Button insert, show, deleteall, deletebydni;
    DbHelper sqLiteDatabase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().hide();

        dni = findViewById(R.id.newdni);
        name = findViewById(R.id.newname);
        surname = findViewById(R.id.newsurname);
        edad = findViewById(R.id.newedad);
        direc = findViewById(R.id.newdirec);
        insert = findViewById(R.id.buttonNew);
        show = findViewById(R.id.buttonShow);
        deleteall = findViewById(R.id.buttonDeleteAll);
        deletebydni = findViewById(R.id.buttonDeleteByDNI);

        sqLiteDatabase = new DbHelper(this);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, DataList.class));
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String dnitxt = dni.getText().toString();
                String nametxt = name.getText().toString();
                String surnametxt = surname.getText().toString();
                String edadtxt = edad.getText().toString();
                String directxt = direc.getText().toString();

                Boolean checkInsertData = sqLiteDatabase.insertData(dnitxt, nametxt, surnametxt, edadtxt, directxt);
                if(checkInsertData == true){
                    Toast.makeText(MainActivity2.this, "Objeto creado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity2.this, "Error al crear el objeto", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deletebydni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String dnitxt = dni.getText().toString();
                //sqLiteDatabase.deleteAllData();
                Boolean checkDeleteData = sqLiteDatabase.deleteData(dnitxt);
                if(checkDeleteData == true){
                    Toast.makeText(MainActivity2.this, "Campo con dni = " + dnitxt + " eliminado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity2.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        deleteall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alert();
                //sqLiteDatabase.deleteAllData();
            }
        });


/*
        SQLiteDatabase db = sqLiteDatabase.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(database.FeedEntry.COLUMN_NAME_DNI, "dni");
        values.put(database.FeedEntry.COLUMN_NAME_NOMBRE, "nombre");
        values.put(database.FeedEntry.COLUMN_NAME_APELLIDO, "apellido");
        values.put(database.FeedEntry.COLUMN_NAME_EDAD, "edad");
        values.put(database.FeedEntry.COLUMN_NAME_DIRECCION, "dirección");
        //Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(database.FeedEntry.TABLE_NAME, null, values);
*/
    }

    private void Alert(){
        AlertDialog alerta;

        alerta = new AlertDialog.Builder(this).create();

        alerta.setTitle("Mensaje de Confirmación");
        alerta.setMessage("¿Estás seguro de que quieres eliminar toda la tabla?");

        alerta.setButton(Dialog.BUTTON_NEGATIVE,"CANCELAR",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //finish();
            }
        });
        alerta.setButton(Dialog.BUTTON_POSITIVE,"ELIMINAR",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Integer checkDeleteAllData = sqLiteDatabase.deleteAllData();;
                if(checkDeleteAllData == 0){
                    Toast.makeText(MainActivity2.this, "Tabla Eliminada", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity2.this, "Error al eliminar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        alerta.show();
    }

}