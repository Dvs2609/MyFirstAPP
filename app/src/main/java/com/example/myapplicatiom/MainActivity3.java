package com.example.myapplicatiom;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    private String title;
    private int image;
    TextView titleTV;
    ImageView imageIV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        title = getIntent().getStringExtra("title");
        image = getIntent().getIntExtra("image", 0);

        titleTV = findViewById(R.id.textView3);
        imageIV = findViewById(R.id.imageActivity3);

        titleTV.setText(title);
        imageIV.setImageResource(image);

    }
}