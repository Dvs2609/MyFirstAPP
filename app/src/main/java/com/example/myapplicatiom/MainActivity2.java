package com.example.myapplicatiom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity2 extends AppCompatActivity {
    Button button_image;
    FrameLayout frameLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        frameLayout = findViewById(R.id.frameLayout);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ImageFragment fragmentImage = new ImageFragment();
        transaction.replace(R.id.frameLayout, fragmentImage);
        transaction.commit();

        frameLayout.setVisibility(View.INVISIBLE);

        button_image = (Button) findViewById(R.id.button_image);
        button_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(frameLayout.getVisibility() == View.INVISIBLE){
                    frameLayout.setVisibility(View.VISIBLE);
                }else {
                    frameLayout.setVisibility((View.INVISIBLE));
                }
            }
        });
    }
}