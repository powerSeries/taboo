package com.example.taboo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TeamMenu extends AppCompatActivity {
    String DEFAULT_TEAM_NAME = "Name";

    EditText team1Name;
    String teamName_1;

    EditText team2Name;
    String teamName_2;

    EditText numOfPlayers;
    int numPlayers;

    EditText numOfRounds;
    int numRounds;

    Button btnStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_menu);

        btnStartGame = (Button) findViewById(R.id.btn_startGame);
        btnStartGame.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                StartGame();
            }
        });
    }

    private void StartGame()
    {
        if(!IsInputValid())
            return;

        Intent switchToScoreMenu = new Intent(this, ScoreMenu.class);
        Bundle bundle = new Bundle();
        bundle.putString("Team_1_Name", teamName_1);
        bundle.putString("Team_2_Name", teamName_2);
        bundle.putInt("numOfPlayers", numPlayers);
        bundle.putInt("numOfRounds", numRounds);
        switchToScoreMenu.putExtras(bundle);
        startActivity(switchToScoreMenu);
    }

    private boolean IsInputValid()
    {
        team1Name = findViewById(R.id.tb_team1_name);
        teamName_1 = team1Name.getText().toString();
        if(teamName_1.equals(DEFAULT_TEAM_NAME) || teamName_1.equals(""))
        {
            team1Name.setError("Please enter a valid team name.");
            return false;
        }

        team2Name = findViewById(R.id.tb_team2_name);
        teamName_2 = team2Name.getText().toString();
        if(teamName_2.equals(DEFAULT_TEAM_NAME) || teamName_2.equals(""))
        {
            team2Name.setError("Please enter valid team name.");
            return false;
        }

        numOfPlayers = findViewById(R.id.tb_NumOfPlayers);
        if(numOfPlayers.getText().toString().equals(""))
        {
            numOfPlayers.setError("Please enter value. DO NOT LEAVE EMPTY");
            return false;
        }
        numPlayers = Integer.parseInt(numOfPlayers.getText().toString());
        if(numPlayers == 0)
        {
            numOfPlayers.setError("Please enter a valid amount of players");
            return false;
        }

        numOfRounds = findViewById(R.id.tb_NumOfRounds);
        if(numOfRounds.getText().toString().equals(""))
        {
            numOfRounds.setError("Please enter value. DO NOT LEAVE EMPTY");
            return false;
        }
        numRounds = Integer.parseInt(numOfRounds.getText().toString());
        if(numRounds == 0)
        {
            numOfRounds.setError("Please enter a valid amount of rounds");
            return false;
        }

        return true;
    }
}