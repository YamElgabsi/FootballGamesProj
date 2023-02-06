package com.example.footballproj;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final Context context;
    Activity activity;
    private final ArrayList game_id;
    private final ArrayList team1;
    private final ArrayList team2;
    private final ArrayList goals1;
    private final ArrayList goals2;

    public MyAdapter(Activity activity, Context context, ArrayList game_id,
                     ArrayList team1, ArrayList team2,
                     ArrayList goals1, ArrayList goals2) {
        this.activity = activity;
        this.context = context;
        this.game_id = game_id;
        this.team1 = team1;
        this.team2 = team2;
        this.goals1 = goals1;
        this.goals2 = goals2;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.game_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.game_id_txt.setText(String.valueOf(game_id.get(position)));
        holder.team1_txt.setText(String.valueOf(team1.get(position)));
        holder.team2_txt.setText(String.valueOf(team2.get(position)));
        holder.goals1_txt.setText(String.valueOf(goals1.get(position)));
        holder.goals2_txt.setText(String.valueOf(goals2.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateGame.class);

                intent.putExtra("id", String.valueOf(game_id.get(position)));
                intent.putExtra("team1", String.valueOf(team1.get(position)));
                intent.putExtra("team2", String.valueOf(team2.get(position)));
                intent.putExtra("goals1", String.valueOf(goals1.get(position)));
                intent.putExtra("goals2", String.valueOf(goals2.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount () {
        return game_id.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        final TextView game_id_txt;
        final TextView team1_txt;
        final TextView team2_txt;
        final TextView goals1_txt;
        final TextView goals2_txt;
        final LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            game_id_txt = itemView.findViewById(R.id.game_id_txt);
            team1_txt = itemView.findViewById(R.id.team1_txt);
            team2_txt = itemView.findViewById(R.id.team2_txt);
            goals1_txt = itemView.findViewById(R.id.goals1_txt);
            goals2_txt = itemView.findViewById(R.id.goals2_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
    }
