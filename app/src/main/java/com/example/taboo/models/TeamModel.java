package com.example.taboo.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Queue;

public class TeamModel implements Serializable
{
    public String TeamName;
    public ArrayList<TabooWord> currentActiveList;
    public ArrayList<String> usedWords;
    public int TeamScore;
    public int NumOfSkips;

    public TeamModel(String name, int teamScore)
    {
        TeamName = name;
        TeamScore = teamScore;
        NumOfSkips = 0;
        currentActiveList = new ArrayList<>();
    }
}
