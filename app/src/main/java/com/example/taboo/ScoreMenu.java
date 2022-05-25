package com.example.taboo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.taboo.models.TeamModel;

import java.util.ArrayList;

public class ScoreMenu extends AppCompatActivity {

    TextView team1Name;
    TextView team1score;
    TextView team2Name;
    TextView team2score;
    TextView numOfRounds;
    TextView numOfPlayer;

    Button btnStartGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();

        // Initialize two teams model
        ArrayList<TeamModel> Teams = new ArrayList<>();
        Teams.add(new TeamModel());
        Teams.add(new TeamModel());

        setContentView(R.layout.activity_score_menu);
    }
}