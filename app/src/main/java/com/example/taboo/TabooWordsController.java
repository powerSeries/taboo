package com.example.taboo;

import android.content.Context;
import android.content.res.AssetManager;
import com.example.taboo.models.*;
import com.google.gson.Gson;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class TabooWordsController
{
    private final Context mContext;

    public ArrayList<TabooWord> allTabooWords;
    public ArrayList<TabooWord> dupeWords;

    public TabooWordsController(Context context)
    {
        mContext = context;
        allTabooWords = new ArrayList<>();
        dupeWords = new ArrayList<>();
        Initialize();
    }

    public TabooWord GetSingleWord()
    {
        int num = (int)(Math.random() * allTabooWords.size());
        for(TabooWord word : allTabooWords) if (--num < 0) return word;
        throw new AssertionError();
    }

    public ArrayList<TabooWord> GetNumWords(int amount)
    {
        ArrayList<TabooWord> randomWords = new ArrayList<>();
        for(int i = 0; i < amount; i++)
        {
            randomWords.add(GetSingleWord());
        }
        return randomWords;
    }

    private void Initialize()
    {
        //ProcessDupeWords();
        // TODO: Filter out dupe words later
        ProcessAllTabooWords();
        Collections.shuffle(allTabooWords);
    }

    private void ProcessAllTabooWords()
    {
        allTabooWords.addAll(GetTabooWordsFromFile("results.json"));
    }

    private void ProcessDupeWords()
    {
        dupeWords = GetTabooWordsFromFile("dupe.json");
    }

    private ArrayList<TabooWord> GetTabooWordsFromFile(String filePath)
    {
        StringBuilder lines = new StringBuilder();
        try
        {
            String line = "";
            ArrayList<TabooWord> fileWords = new ArrayList<>();

            // Obtain all the assets
            AssetManager assetManager = mContext.getAssets();

            // Get an input stream of the results.json which contains all the words used in taboo
            InputStream is = assetManager.open(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            // Read through all the lines
            while((line = reader.readLine()) != null)
            {
                lines.append(line);
            }

            // Convert lines to a JSON Array for ease of use
            JSONArray jsonItems = new JSONArray(lines.toString());

            // Convert each Json Object to Taboo Word class
            Gson gson = new Gson();
            TabooWord tWord = new TabooWord();
            for(int i = 0; i < jsonItems.length(); i++)
            {
                Object item = jsonItems.get(i);
                tWord = gson.fromJson(item.toString(), TabooWord.class);
                fileWords.add(tWord);
            }

            //Close calls
            reader.close();
            is.close();

            return fileWords;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }
}
