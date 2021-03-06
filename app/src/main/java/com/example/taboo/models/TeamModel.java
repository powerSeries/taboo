package com.example.taboo.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Queue;

public class TeamModel implements Serializable
{
    public String TeamName;
    public ArrayList<TabooWord> currentActiveList;
    public ArrayList<TabooWord> usedWords;
    public int TeamScore;
    public int NumOfSkips;

    public TeamModel(String name, int teamScore)
    {
        TeamName = name;
        TeamScore = teamScore;
        NumOfSkips = 0;
        usedWords = new ArrayList<>();
        currentActiveList = new ArrayList<>();
    }

    public void UpdateScore(TeamModel teamModel)
    {
        this.TeamScore = teamModel.TeamScore;
        this.NumOfSkips += teamModel.NumOfSkips;
    }

    public void Reset()
    {
        this.TeamScore = 0;
        this.NumOfSkips = 0;
    }
}
