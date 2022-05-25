package com.example.taboo.models;

public class TeamModel
{
    public String TeamName;
    public int TeamScore;
    public int NumOfSkips;

    public TeamModel(String name, int teamScore)
    {
        TeamName = name;
        TeamScore = teamScore;
        NumOfSkips = 0;
    }
}
