package com.example.footballproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddGame extends AppCompatActivity {
     EditText team1_input , team2_input , goals1_input , goals2_input;
     Button add_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);

        team1_input = findViewById(R.id.team1_input);
        team2_input = findViewById(R.id.team2_input);
        goals1_input = findViewById(R.id.goals1_input);
        goals2_input = findViewById(R.id.goals2_input);
        add_btn = findViewById(R.id.add_btn);


        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String isInputValid = Validation.isValid(team1_input.getText().toString().trim() ,
                        team2_input.getText().toString().trim() ,
                         goals1_input.getText().toString().trim() ,
                        goals2_input.getText().toString().trim());

                 if(isInputValid.length() == 0)
                 {
                     MyDatabaseHelper myDB = new MyDatabaseHelper(AddGame.this);
                     myDB.addGame(team1_input.getText().toString().trim() ,
                             team2_input.getText().toString().trim() ,
                             Integer.valueOf( goals1_input.getText().toString().trim()) ,
                             Integer.valueOf( goals2_input.getText().toString().trim()));
                     finish();
                 }
                 else {
                     Toast.makeText(getApplicationContext(), isInputValid , Toast.LENGTH_LONG).show();
                 }



            }
        });

    }


}