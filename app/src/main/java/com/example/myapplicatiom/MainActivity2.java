package com.example.myapplicatiom;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;

import com.example.myapplicatiom.charting.CustomView;
import com.example.myapplicatiom.databinding.ActivityMain2Binding;
import com.example.myapplicatiom.databinding.ActivityMainBinding;

import org.w3c.dom.Text;


public class MainActivity2 extends AppCompatActivity {

    ActivityMain2Binding bindView;
    CustomView sunset,sunrise;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        bindView = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(bindView.getRoot());

        bindView.sunriseCustomView.setSubtitle("5:31 AM");
        bindView.sunsetCustomView.setSubtitle("5:30 AM");


        //sunrise = findViewById(R.id.sunrise_custom_view);
        //sunrise.setSubtitle("5:31 AM");

        //sunset = findViewById(R.id.sunrise_custom_view);
        //sunset.setSubtitle("5:31 AM");

        //setContentView(R.layout.activity_main2);
        //getLayoutRes();
        //initRes();

    }

    private void initRes() {
       // customView = (CustomView) findViewById(R.id.)
    }

    private void getLayoutRes() {
    }
}