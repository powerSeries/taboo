package com.example.taboo.controllers;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.example.taboo.models.*;

public class TabooGameController implements Serializable
{
    public TeamModel ActiveTeam;
    private ArrayList<TabooWord> duplicateWords;
    private ArrayList<TabooWord> currentActiveList;

    public TabooGameController()
    {
        duplicateWords = new ArrayList<>();
        currentActiveList = new ArrayList<>();
    }

    public TabooWord popWord()
    {
        boolean IsWordSelected = false;
        TabooWord word = new TabooWord();
        while(!IsWordSelected)
        {
            word = PickOneRandom();
            if(word != null)
                IsWordSelected = true;
        }
        return word;
    }

    private TabooWord PickOneRandom()
    {
        int num = (int)(Math.random() * ActiveTeam.currentActiveList.size());
        for(TabooWord word : ActiveTeam.currentActiveList)
            if (--num < 0 )
            {
                ActiveTeam.currentActiveList.remove(word);
                if(!ActiveTeam.usedWords.contains((word)))
                    ActiveTeam.usedWords.add(word);

                return word;
            }

        throw new AssertionError();
    }
}
