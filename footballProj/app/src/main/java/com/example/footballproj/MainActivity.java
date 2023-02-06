package com.example.footballproj;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_btn;
    MyDatabaseHelper myDB;
    ArrayList<String> game_id , team1 , team2 , goals1 , goals2;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView);
        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , AddGame.class);
                //startActivity(intent);
                MainActivity.this.startActivityForResult(intent, 1);

            }
        });



        myDB = new MyDatabaseHelper(MainActivity.this);
        game_id = new ArrayList<>();
        team1 = new ArrayList<>();
        team2 = new ArrayList<>();
        goals1 = new ArrayList<>();
        goals2 = new ArrayList<>();

        addDataToArr();
        myAdapter = new MyAdapter(MainActivity.this, this , game_id , team1 , team2 , goals1 , goals2);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent = new Intent(MainActivity.this , ScoreChart.class);
        startActivity(intent);
        return true;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
            recreate();
    }

    void addDataToArr(){
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0)
            Toast.makeText(this, "No data" , Toast.LENGTH_SHORT).show();
        else
        {
            while(cursor.moveToNext())
            {
                game_id.add(cursor.getString(0));
                team1.add(cursor.getString(1));
                team2.add(cursor.getString(2));
                goals1.add(cursor.getString(3));
                goals2.add(cursor.getString(4));
            }
        }
    }
}