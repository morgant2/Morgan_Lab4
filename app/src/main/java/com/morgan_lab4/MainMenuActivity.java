package com.morgan_lab4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    private Button btnNewGame;
    private Button btnViewScoreboard;
    private Button btnSelectPlayerOne;
    private Button btnSelectPlayerTwo;
    private Button btnAddPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        getButtons();
        setButtonEvents();
    }

    private void setButtonEvents() {
        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, GameEmulatorActivity.class);
                MainMenuActivity.this.startActivity(intent);

            }
        });

        btnViewScoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, ScoreboardActivity.class);
                MainMenuActivity.this.startActivity(intent);
            }
        });

        btnSelectPlayerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, PlayerActivity.class);
                intent.putExtra(getString(R.string.selected_player_key), getString(R.string.player_one));
                MainMenuActivity.this.startActivity(intent);
            }
        });

        btnSelectPlayerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, PlayerActivity.class);
                intent.putExtra(getString(R.string.selected_player_key), getString(R.string.player_two));
                MainMenuActivity.this.startActivity(intent);
            }
        });

        btnAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, ScoreboardActivity.class);
                MainMenuActivity.this.startActivity(intent);
            }
        });
    }

    private void getButtons() {
        btnNewGame = (Button) findViewById(R.id.btnNewGame);
        btnViewScoreboard = (Button) findViewById(R.id.btnViewScoreboard);
        btnSelectPlayerOne = (Button) findViewById(R.id.btnSelectPlayerOne);
        btnSelectPlayerTwo = (Button) findViewById(R.id.btnSelectPlayerTwo);
        btnAddPlayer = (Button) findViewById(R.id.btnAddPlayer);
    }
}
