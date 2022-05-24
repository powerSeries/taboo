package com.example.taboo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabooWordsController tWordController = new TabooWordsController();
        setContentView(R.layout.activity_main);
    }
}