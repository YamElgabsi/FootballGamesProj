package com.example.footballproj;

import static java.util.Collections.reverseOrder;
import static java.util.Collections.sort;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ScoreChart extends AppCompatActivity {



    private ListView teamsGoalsListView;
    private ArrayList<teamEntry> teamsGoalsArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_chart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //init database helper
        MyDatabaseHelper myDB = new MyDatabaseHelper(ScoreChart.this);

        //build teams goals list
        initTeamsGoalsArray(myDB);
        //init list view
        teamsGoalsListView = findViewById(R.id.teamsGoalsListView);
        ScoreChartAdapter adapter = new ScoreChartAdapter(
                teamsGoalsArray);
        teamsGoalsListView.setAdapter(adapter);
    }

    private void initTeamsGoalsArray(MyDatabaseHelper myDB) {
        int place = 1;
        teamsGoalsArray = new ArrayList<>();
        for (String teamName: myDB.getTeams()) {
            int goals = myDB.getTeamGoals(teamName);
            teamsGoalsArray.add(new teamEntry( teamName, goals));
            place++;


        }
        teamsGoalsArray.sort(reverseOrder());
    }
}