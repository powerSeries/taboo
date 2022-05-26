package com.example.taboo;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.taboo.models.*;

import java.util.ArrayList;

public class ScoreMenu extends AppCompatActivity {
    TabooGameController gameController;
    TabooWordsController wordsController;
    ArrayList<TeamModel> allTeams;
    // Team 1 Name and Score
    TextView team1Name;
    TextView team1score;

    // Team 2 Name and Score
    TextView team2Name;
    TextView team2score;

    Button btnStartRound;

    int playersPlayed;
    int playerAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_menu);
        wordsController = new TabooWordsController(this);
        Bundle bundle = getIntent().getExtras();

        btnStartRound = findViewById(R.id.startRound);
        btnStartRound.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                StartRound();
            }
        });
        playerAmount = bundle.getInt("numOfPlayers");
        playersPlayed = 0;
        // Initialize two teams model
        InitializeTeams(bundle);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private void StartRound()
    {
        gameController.ActiveTeam = allTeams.get(playersPlayed % 2);
        Intent switchToGame = new Intent(this, TabooGame.class);
        switchToGame.putExtra("TabooGameController", gameController);
        // Add data as needed below to TabooGame
        startActivity(switchToGame);
    }

    private void InitializeTeams(Bundle bundle)
    {
        String firstTeamName = bundle.getString("Team_1_Name");
        TeamModel firstTeam = new TeamModel(firstTeamName, 0);
        firstTeam.currentActiveList = wordsController.GetNumWords(50);
        team1Name = findViewById(R.id.score_Team1Name);
        team1Name.setText(firstTeam.TeamName);
        team1score = findViewById(R.id.team1_score);
        team1score.setText(Integer.toString(firstTeam.TeamScore));

        String secondTeamName = bundle.getString("Team_2_Name");
        TeamModel secondTeam = new TeamModel(secondTeamName, 0);
        secondTeam.currentActiveList = wordsController.GetNumWords(50);
        team2Name = findViewById(R.id.score_Team2Name);
        team2Name.setText(secondTeam.TeamName);
        team2score = findViewById(R.id.team2_score);
        team2score.setText(Integer.toString(secondTeam.TeamScore));

        allTeams = new ArrayList<>();
        allTeams.add(firstTeam);
        allTeams.add(secondTeam);
        gameController = new TabooGameController();
    }
}