package com.morgan_lab4;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
                SharedPreferences sp = getSharedPreferences(getString(R.string.preferences), Activity.MODE_PRIVATE);
                int playerOneID = sp.getInt(getString(R.string.player_one_selected), -1);
                int playerTwoID = sp.getInt(getString(R.string.player_two_selected), -1);

                if(playerOneID >= 0 && playerTwoID >= 0)
                {
                    Intent intent = new Intent(MainMenuActivity.this, ScoreboardActivity.class);
                    intent.putExtra(getString(R.string.player_one_selected), playerOneID);
                    intent.putExtra(getString(R.string.player_two_selected), playerTwoID);
                    MainMenuActivity.this.startActivity(intent);
                }
                else if(playerOneID < 0)
                {
                    Intent intent = new Intent(MainMenuActivity.this, PlayerActivity.class);
                    intent.putExtra(getString(R.string.selected_player_key), true);
                    MainMenuActivity.this.startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(MainMenuActivity.this, PlayerActivity.class);
                    intent.putExtra(getString(R.string.selected_player_key), false);
                    MainMenuActivity.this.startActivity(intent);
                }

            }
        });

        btnSelectPlayerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, PlayerActivity.class);
                intent.putExtra(getString(R.string.selected_player_key), true);
                MainMenuActivity.this.startActivity(intent);
            }
        });

        btnSelectPlayerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, PlayerActivity.class);
                intent.putExtra(getString(R.string.selected_player_key), false);
                MainMenuActivity.this.startActivity(intent);
            }
        });

        btnAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, AddPlayerActivity.class);
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
