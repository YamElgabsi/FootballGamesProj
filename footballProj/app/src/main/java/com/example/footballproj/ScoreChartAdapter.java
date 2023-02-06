package com.example.footballproj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ScoreChartAdapter extends BaseAdapter {
    ArrayList<teamEntry> teamsList;

    public ScoreChartAdapter(ArrayList<teamEntry> teamsList) {
        this.teamsList = teamsList;
    }

    @Override
    public int getCount() {
        return teamsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_list_item,
                    parent, false);
        }

        TextView teamPlace = convertView.findViewById(R.id.teamsPlace);
        TextView teamName = convertView.findViewById(R.id.teamName);
        TextView teamGoals = convertView.findViewById(R.id.teamGoals);
        teamPlace.setText(String.valueOf(position+1));
        teamName.setText(teamsList.get(position).teamName);
        teamGoals.setText(String.valueOf(teamsList.get(position).goals));
        return convertView;
    }


}
