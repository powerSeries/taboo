package com.example.taboo;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.taboo.models.TeamModel;


public class ScoreMenu extends AppCompatActivity {
    // Team 1 Name and Score
    TextView team1Name;
    TextView team1score;

    // Team 2 Name and Score
    TextView team2Name;
    TextView team2score;

    Button btnStartRound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_menu);
        Bundle bundle = getIntent().getExtras();


        // Get Start Round button
        btnStartRound = findViewById(R.id.startRound);

        btnStartRound.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                StartRound();
            }
        });
        // Initialize two teams model
        InitializeTeams(bundle);
    }

    private void StartRound()
    {

    }

    private void InitializeTeams(Bundle bundle)
    {
        String firstTeamName = bundle.getString("Team_1_Name");
        TeamModel firstTeam = new TeamModel(firstTeamName, 0);
        team1Name = findViewById(R.id.score_Team1Name);
        team1Name.setText(firstTeam.TeamName);
        team1score = findViewById(R.id.team1_score);
        team1score.setText(Integer.toString(firstTeam.TeamScore));

        String secondTeamName = bundle.getString("Team_2_Name");
        TeamModel secondTeam = new TeamModel(secondTeamName, 0);
        team2Name = findViewById(R.id.score_Team2Name);
        team2Name.setText(secondTeam.TeamName);
        team2score = findViewById(R.id.team2_score);
        team2score.setText(Integer.toString(secondTeam.TeamScore));
    }
}