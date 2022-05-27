package com.example.taboo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.taboo.models.TabooWord;

import java.util.ArrayList;

public class TabooGame extends AppCompatActivity {
    private TabooGameController GAME_CONTROLLER;

    private static final int MAX_AMOUNT_OF_FORBIDDEN_WORDS = 5;

    private TextView countdownText;
    private Button countdownButton;
    private CountDownTimer CDT;
    private long timeLeftInMS = 60000;  // 1-min in MS
    private boolean timerRunning;

    private TextView goalWord;
    private ArrayList<TextView> forbiddenWords;
    private boolean IsWordLoaded;

    private Button positiveBtn;
    private Button negativeBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taboo_game);

        GAME_CONTROLLER = (TabooGameController) getIntent().getSerializableExtra("TabooGameController");
        IsWordLoaded = false;
        countdownText = findViewById(R.id.gameTimer);
        countdownButton = findViewById(R.id.btn_stopNstart);
        countdownButton.setText("START");

        InitializeWordSection();

        countdownButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startStop();
            }
        });

        positiveBtn = findViewById(R.id.btn_positive);
        positiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add +1
                PosGoNext();
            }
        });

        negativeBtn = findViewById(R.id.btn_negative);
        negativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add -1
                NegGoNext();
            }
        });

        updateTimer();
    }

    private void InitializeWordSection()
    {
        goalWord = findViewById(R.id.goalWord);
        forbiddenWords = new ArrayList<>();
        forbiddenWords.add(findViewById(R.id.fw_1));
        forbiddenWords.add(findViewById(R.id.fw_2));
        forbiddenWords.add(findViewById(R.id.fw_3));
        forbiddenWords.add(findViewById(R.id.fw_4));
        forbiddenWords.add(findViewById(R.id.fw_5));
    }

    private void startStop()
    {
        if(timerRunning)
        {
            stopTimer();
        }
        else
        {
            startTimer();
        }
    }

    private void stopTimer()
    {
        CDT.cancel();
        countdownButton.setText("START");
        timerRunning = false;
    }

    private void updateTimer()
    {
        int mins = (int) timeLeftInMS / 60000;
        int secs = (int) timeLeftInMS % 60000 / 1000;
        String timeLeftText;
        timeLeftText = "" + mins;
        timeLeftText += ":";
        if(secs < 10) timeLeftText += "0";
        timeLeftText += secs;
        countdownText.setText(timeLeftText);

        if(mins == 0 && secs == 0)
        {
            CDT.onFinish();
        }
    }

    private void startTimer()
    {
        CDT = new CountDownTimer(timeLeftInMS,  1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                GameTickEvent(millisUntilFinished);
            }

            @Override
            public void onFinish()
            {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("resultActiveTeam", GAME_CONTROLLER.ActiveTeam);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        }.start();

        countdownButton.setText("PAUSE");
        timerRunning = true;
    }

    private void GameTickEvent(long millisUntilFinished)
    {
        timeLeftInMS = millisUntilFinished;
        updateTimer();
        if(!IsWordLoaded)
        {
            IsWordLoaded = true;
            TabooWord currentWord = GAME_CONTROLLER.popWord();
            LoadWordToScreen(currentWord);
        }
    }

    private void LoadWordToScreen(TabooWord word)
    {
        goalWord.setText(word.Word);
        for(int i = 0; i < word.ForbiddenWords.size(); i++)
        {
            TextView txtView = forbiddenWords.get(i);
            txtView.setText(word.ForbiddenWords.get(i));
        }
    }

    private void PosGoNext()
    {
        if(timerRunning)
        {
            GAME_CONTROLLER.ActiveTeam.TeamScore++;
            IsWordLoaded = false;
        }
    }

    private void NegGoNext()
    {
        if(timerRunning)
        {
            GAME_CONTROLLER.ActiveTeam.TeamScore--;
            GAME_CONTROLLER.ActiveTeam.NumOfSkips++;
            IsWordLoaded = false;
        }
    }
}