package com.example.myapplicatiom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicatiom.rf.APIservice;
import com.example.myapplicatiom.rf.APIutils;
import com.example.myapplicatiom.rf.RetrofitClass;
import com.example.myapplicatiom.rv.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    RecyclerViewAdapter adapter;
    TextView name;

    private APIservice mService;
    List<Character> listaPersona = new ArrayList<>();
    private static final String BASE_URL = "https://breakingbadapi.com/api/characters?limit=5";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.RecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        APIservice apiservice = RetrofitClass.getClient().create(APIservice.class);

        Call<List<Character>> call = apiservice.getCharacterLimit5();
        call.enqueue(new Callback<List<Character>>() {
            @Override
            public void onResponse(Call<List<Character>> call, Response<List<Character>> response) {
                //List<Character> characters = response.body().getListcharacter();
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {

                    listaPersona = response.body();
                    //listaPersona.addAll(response.body());
                }
                adapter = new RecyclerViewAdapter(getApplicationContext(), listaPersona);
                recyclerView.setAdapter(adapter);

            }
            @Override
            public void onFailure(Call<List<Character>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
            }
        });



        //loadAnswers();

    }



    /*private void loadAnswers() {
        APIservice.getAnswers().enqueue(new Callback<List<Character>>() {
            @Override
            public void onResponse(Call<List<Character>> call, Response<List<Character>> response) {

                List<Character> posts = response.body();
                name = findViewById(R.id.TVidShow);

                for (Character post : posts){
                    String nombre = post.getName();
                    name.append(nombre);
                }

            }

            @Override
            public void onFailure(Call<List<Character>> call, Throwable t) {

            }


        });
    }*/

}