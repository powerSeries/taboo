package com.example.taboo;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.taboo.models.TabooWord;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TabooWordsController
{
    private final Context mContext;

    public ArrayList<TabooWord> allTabooWords;
    public ArrayList<TabooWord> dupeWords;

    public TabooWordsController(Context context)
    {
        mContext = context;
        Initialize();
    }

    private void Initialize()
    {
        ProcessDupeWords();

        ProcessAllTabooWords();
    }

    private void ProcessAllTabooWords()
    {
        allTabooWords = GetTabooWordsFromFile("results.json");
    }

    private void ProcessDupeWords()
    {
        dupeWords = GetTabooWordsFromFile("dupe.json");
    }

    private ArrayList<TabooWord> GetTabooWordsFromFile(String filePath)
    {
        String lines = "";
        try
        {
            String line;
            ArrayList<TabooWord> fileWords = new ArrayList<>();

            // Obtain all the assets
            AssetManager assetManager = mContext.getAssets();

            // Get an input stream of the results.json which contains all the words used in taboo
            InputStream is = assetManager.open(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            // Read through all the lines
            while((line = reader.readLine()) != null)
            {
                lines += line;
            }

            // Convert lines to a JSON Array for ease of use
            JSONArray jsonItems = new JSONArray(lines);

            // Convert each Json Object to Taboo Word class
            Gson gson = new Gson();
            for(int i = 0; i < jsonItems.length(); i++)
            {
                TabooWord tWord;
                Object item = jsonItems.get(i);
                tWord = gson.fromJson(item.toString(), TabooWord.class);
                fileWords.add(tWord);
            }

            //Close calls
            reader.close();
            is.close();
            assetManager.close();

            return fileWords;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }
}
