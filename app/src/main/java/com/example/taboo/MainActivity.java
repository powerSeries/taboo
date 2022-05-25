package com.example.taboo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button sTeamMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabooWordsController tabooWordsController = new TabooWordsController(this);
        sTeamMenu = findViewById(R.id.btn_teamMenu);
        sTeamMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivities();
            }
        });
    }

    private void switchActivities()
    {
        Intent switchActivityIntent = new Intent(this, TeamMenu.class);
        startActivity(switchActivityIntent);
    }
}