package com.example.footballproj;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateGame extends AppCompatActivity {
    EditText team1_input, team2_input, goals1_input, goals2_input;
    Button update_btn , delete_btn;
    String id, team1, team2, goals1, goals2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_game);

        team1_input = findViewById(R.id.team1_input2);
        team2_input = findViewById(R.id.team2_input2);
        goals1_input = findViewById(R.id.goals1_input2);
        goals2_input = findViewById(R.id.goals2_input2);
        update_btn = findViewById(R.id.update_btn);
        delete_btn = findViewById(R.id.delete_btn);
        getIntentData();

        ActionBar action = getSupportActionBar();
        if (action != null) {
            action.setTitle(team1+" V "+team2);
        }

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isInputValid = Validation.isValid(team1_input.getText().toString().trim() ,
                        team2_input.getText().toString().trim() ,
                        goals1_input.getText().toString().trim() ,
                        goals2_input.getText().toString().trim());

                if(isInputValid.length() == 0)
                {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateGame.this);
                    myDB.updateData(id, team1_input.getText().toString(),
                            team2_input.getText().toString(),
                            goals1_input.getText().toString(),
                            goals2_input.getText().toString());
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), isInputValid , Toast.LENGTH_LONG).show();
                }


            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();

            }
        });

    }

    void getIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("team1")
                && getIntent().hasExtra("team2") && getIntent().hasExtra("goals1")
                && getIntent().hasExtra("goals2")) {
            id = getIntent().getStringExtra("id");
            team1 = getIntent().getStringExtra("team1");
            team2 = getIntent().getStringExtra("team2");
            goals1 = getIntent().getStringExtra("goals1");
            goals2 = getIntent().getStringExtra("goals2");

            team1_input.setText(team1);
            team2_input.setText(team2);
            goals1_input.setText(goals1);
            goals2_input.setText(goals2);

        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(team1 + " V "+ team2 );
        builder.setMessage("Are you sure you want to delete this game?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateGame.this);
                myDB.deleteGame(id);
                finish();
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();

    }
}