package com.example.myapplicatiom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.example.myapplicatiom.databinding.ActivityMain2Binding;
import com.example.myapplicatiom.databinding.ActivityMainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class MainActivity2 extends AppCompatActivity {

    EditText emailid, passid, tokenid;

    private ActivityMainBinding binding;
    ActivityMain2Binding bindView;
    private static final String FILE_NAME = "texto.txt";

    Button ButtonLogin, ButtonCreateUser, btnGoogle;

    int RC_SIGN_IN = 1;
    //String TAGgoggle = "Googlelogin";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();

        bindView = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(bindView.getRoot()); //(R.layout.avtiivity_main);

        bindView.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFile();
            }
        });
        bindView.btRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFile();
            }
        });
    }

    private void saveFile(){
        String textToSave = bindView.etFichero.getText().toString();
        //Clase que nos permite escribir un fichero como flujo de bytes
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fileOutputStream.write(textToSave.getBytes());
            Log.d("TAG1", "Fichero salvado en: " + getFilesDir() + "/" + FILE_NAME);
            fileOutputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fileOutputStream != null){
                try {
                    fileOutputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    private void readFile(){
        //Leer fichero con flujo de bytes
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            bufferedReader = new BufferedReader(inputStreamReader); //lee linea a linea
            String lineaTexto;
            StringBuilder stringBuilder = new StringBuilder();
            lineaTexto = bufferedReader.readLine();
            while(lineaTexto != null){
                stringBuilder.append(lineaTexto + "\n");
                lineaTexto = bufferedReader.readLine();
            }

            bindView.etFichero.setText(stringBuilder);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}