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

    public TabooGameController(ArrayList<TabooWord> allWords, ArrayList<TabooWord> allDupeWords)
    {
        duplicateWords = allDupeWords;
        currentActiveList = allWords;
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
        // Get a random number from 0 - size of active list
        int num = (int)(Math.random() * currentActiveList.size());

        // Get the word at that index: num
        TabooWord currentWord = currentActiveList.get(num);

        // Remove the word at index: num
        currentActiveList.remove(num);

        return currentWord;
    }
}
