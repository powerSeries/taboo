package com.example.taboo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taboo.models.*;

import java.util.ArrayList;

public class ScoreMenu extends AppCompatActivity {
    TabooGameController gameController;
    TabooWordsController wordsController;
    boolean IsGameFinished;

    TeamModel firstTeam;
    boolean FirstTeamActive;

    TeamModel secondTeam;
    boolean SecondTeamActive;

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

        FirstTeamActive = false;
        SecondTeamActive = false;
        IsGameFinished = false;

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

    private void StartRound()
    {
        if(IsGameFinished)
        {
            String teamWinner = (firstTeam.TeamScore > secondTeam.TeamScore) ? firstTeam.TeamName : secondTeam.TeamName;
            Toast.makeText(getApplicationContext(), "Game has finished." + teamWinner + "has won!", Toast.LENGTH_LONG).show();
            ResetGame();
        }

        gameController.ActiveTeam = FindNextTeam();
        Intent switchToGame = new Intent(this, TabooGame.class);
        switchToGame.putExtra("TabooGameController", gameController);
        // Add data as needed below to TabooGame
        startActivityForResult(switchToGame, 1);
    }

    private void ResetGame()
    {
        playersPlayed = 0;
        firstTeam.Reset();
        secondTeam.Reset();
        FirstTeamActive = true;
        SecondTeamActive = false;
        IsGameFinished = false;
    }

    private TeamModel FindNextTeam()
    {
        if(playersPlayed % 2 == 0)
        {
            FirstTeamActive = true;
            SecondTeamActive = false;
            return firstTeam;
        }
        else
        {
            FirstTeamActive = false;
            SecondTeamActive = true;
            return secondTeam;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            if(resultCode == RESULT_OK)
            {
                TeamModel resultTeamModel = (TeamModel)data.getSerializableExtra("resultActiveTeam");
                UpdateScore(resultTeamModel);
                if(playersPlayed == playerAmount)
                {
                    IsGameFinished = true;
                }
            }
        }
    }

    private void UpdateScore(TeamModel resultModel)
    {
        if(FirstTeamActive)
        {
            firstTeam.UpdateScore(resultModel);
            RefillWords(firstTeam);
            DisplayScore();
        }

        if(SecondTeamActive)
        {
            secondTeam.UpdateScore(resultModel);
            RefillWords(secondTeam);
            DisplayScore();
        }
        playersPlayed++;
    }

    private void DisplayScore()
    {
        if(FirstTeamActive)
        {
            team1score.setText(Integer.toString(firstTeam.TeamScore));
        }

        if(SecondTeamActive)
        {
            team2score.setText(Integer.toString(secondTeam.TeamScore));
        }
    }

    private void RefillWords(TeamModel teamModel)
    {
        if(teamModel.currentActiveList.size() < 25)
        {
            int amountLeft = Math.abs(50 - teamModel.currentActiveList.size());
            teamModel.currentActiveList.addAll(wordsController.GetNumWords(amountLeft));
        }
    }

    private void InitializeTeams(Bundle bundle)
    {
        String firstTeamName = bundle.getString("Team_1_Name");
        firstTeam = new TeamModel(firstTeamName, 0);
        firstTeam.currentActiveList = wordsController.GetNumWords(50);
        team1Name = findViewById(R.id.score_Team1Name);
        team1Name.setText(firstTeam.TeamName);
        team1score = findViewById(R.id.team1_score);
        team1score.setText(Integer.toString(firstTeam.TeamScore));

        String secondTeamName = bundle.getString("Team_2_Name");
        secondTeam = new TeamModel(secondTeamName, 0);
        secondTeam.currentActiveList = wordsController.GetNumWords(50);
        team2Name = findViewById(R.id.score_Team2Name);
        team2Name.setText(secondTeam.TeamName);
        team2score = findViewById(R.id.team2_score);
        team2score.setText(Integer.toString(secondTeam.TeamScore));

        gameController = new TabooGameController();
    }
}