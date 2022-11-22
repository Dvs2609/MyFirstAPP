package com.example.myapplicatiom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;




public class MainActivity2 extends AppCompatActivity {

    EditText txtnombre, txtapellido;
    Button buttonShared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();

        txtnombre = findViewById(R.id.Nombre_shared);
        txtapellido = findViewById(R.id.Apellido_Shared);
        buttonShared = findViewById(R.id.button_Shared);

        SharedPreferences datos = getSharedPreferences("datos", Context.MODE_PRIVATE);
        txtnombre.setText(datos.getString("nombre", ""));
        txtapellido.setText(datos.getString("apellido", ""));

        buttonShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardardatos(view);
            }
        });

    }

    public void guardardatos(View view){
        SharedPreferences datos = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = datos.edit();

        editor.putString("nombre", txtnombre.getText().toString());
        editor.putString("apellido", txtapellido.getText().toString());

        //editor.apply();
        editor.commit();
        Toast.makeText(this, "Guardado con exito", Toast.LENGTH_SHORT).show();
    }

}