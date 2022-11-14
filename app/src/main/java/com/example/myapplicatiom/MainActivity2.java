package com.example.myapplicatiom;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.os.Bundle;

import android.webkit.WebView;
import android.webkit.WebViewClient;



public class MainActivity2 extends AppCompatActivity {

    private WebView webview;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().hide();

        webview = (WebView) findViewById(R.id.WVid);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("http://www.google.com");
    }

}