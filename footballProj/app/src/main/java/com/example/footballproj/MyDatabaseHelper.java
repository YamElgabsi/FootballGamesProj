package com.example.footballproj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;

    private static final String DATABASE_NAME = "FootballTable.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_table";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TEAM1 = "team_one";
    private static final String COLUMN_TEAM2 = "team_two";
    private static final String COLUMN_GOALS1= "goals_one";
    private static final String COLUMN_GOALS2= "goals_two";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TEAM1 + " TEXT, " +
                        COLUMN_TEAM2 + " TEXT, " +
                        COLUMN_GOALS1 + " INTEGER, " +
                        COLUMN_GOALS2 + " INTEGER);";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query , null );
        }
        return cursor;
    }

    int getTeamGoals(String team_name){
        SQLiteDatabase db = this.getReadableDatabase();
        int answer = 0;
        String query = String.format("SELECT _id, %s FROM %s WHERE %s LIKE ? UNION " +
                                     "SELECT _id, %s FROM %s WHERE %s LIKE ?", COLUMN_GOALS1, TABLE_NAME, COLUMN_TEAM1,
                                                                             COLUMN_GOALS2, TABLE_NAME, COLUMN_TEAM2);
        Cursor cursor = db.rawQuery(query,new String[]{team_name, team_name});
        if(cursor.moveToFirst())
        {
            while(!cursor.isAfterLast())
            {
                answer += cursor.getInt(1);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return answer;
    }

    ArrayList<String> getTeams(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> teams = new ArrayList<>();
        String query = String.format("SELECT DISTINCT %s FROM %s UNION " +
                        "SELECT DISTINCT %s FROM %s", COLUMN_TEAM1, TABLE_NAME,
                COLUMN_TEAM2, TABLE_NAME);
        Cursor cursor = db.rawQuery(query,new String[]{});
        if(cursor.moveToFirst())
        {
            while(!cursor.isAfterLast())
            {
                teams.add(cursor.getString(0));
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return teams;
    }

    void addGame(String team1 , String team2 , int goals1 , int goals2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TEAM1 , team1);
        cv.put(COLUMN_TEAM2 , team2);
        cv.put(COLUMN_GOALS1 , goals1);
        cv.put(COLUMN_GOALS2 , goals2);

        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1)
            Toast.makeText(context , "Failed to insert" , Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context , "succeed to insert" , Toast.LENGTH_SHORT).show();

    }



    void updateData(String row_id , String team1 , String team2 , String goals1 , String goals2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TEAM1 , team1);
        cv.put(COLUMN_TEAM2 , team2);
        cv.put(COLUMN_GOALS1 , goals1);
        cv.put(COLUMN_GOALS2 , goals2);

        long result = db.update(TABLE_NAME ,cv , "_id=?" , new String[]{row_id});
        if(result == -1)
            Toast.makeText(context , "Failed to update" , Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context , "succeed to update" , Toast.LENGTH_SHORT).show();


    }

    void deleteGame(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?",new String[]{row_id});

        if(result == -1)
            Toast.makeText(context , "Failed to delete" , Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context , "succeed to delete" , Toast.LENGTH_SHORT).show();
    }



}
