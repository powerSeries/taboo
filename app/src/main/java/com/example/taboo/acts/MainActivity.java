package com.example.taboo.acts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.taboo.R;

public class MainActivity extends AppCompatActivity {
    Button sTeamMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
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