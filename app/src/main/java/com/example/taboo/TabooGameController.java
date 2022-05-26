package com.example.taboo;

import java.io.Serializable;
import java.util.ArrayList;

import com.example.taboo.models.*;

public class TabooGameController implements Serializable
{
    public TeamModel ActiveTeam;

    public ArrayList<String> dupeList;

    public TabooGameController()
    {
        dupeList = new ArrayList<>();
    }

    public TabooWord popWord()
    {
        boolean IsWordSelected = false;

        TabooWord word = new TabooWord();
        while(!IsWordSelected)
        {
            word = PickOneRandom();
            if(!dupeList.contains(word.Word))
            {
                dupeList.add(word.Word);
                IsWordSelected = true;
            }
        }

        return word;
    }

    private TabooWord PickOneRandom()
    {
        int num = (int)(Math.random() * ActiveTeam.currentActiveList.size());
        for(TabooWord word : ActiveTeam.currentActiveList) if (--num < 0 ) return word;
        throw new AssertionError();
    }
}
