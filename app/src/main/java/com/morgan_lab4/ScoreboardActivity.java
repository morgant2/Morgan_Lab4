package com.morgan_lab4;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreboardActivity extends AppCompatActivity {

    private PersonDB db;
    private TextView tvScoreBoard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        db = new PersonDB(getApplicationContext());

        tvScoreBoard = (TextView) findViewById(R.id.tvPlayersStats);

        tvScoreBoard.setText(db.getAllPlayersStats());
    }


}
