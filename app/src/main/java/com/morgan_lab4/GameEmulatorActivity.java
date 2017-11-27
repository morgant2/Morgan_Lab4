package com.morgan_lab4;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameEmulatorActivity extends AppCompatActivity {

    private TextView tvPlayerOneName;
    private TextView tvPlayerTwoName;
    private Button btnPlayerOneWins;
    private Button btnPlayerTwoWins;
    private Button btnTie;
    private PersonDB db;
    private Player playerOne;
    private Player playerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_emulator);
        db = new PersonDB(getApplicationContext());

        getViews();
        setOnClickListeners();
        setPlayers();
        setPlayerNames();

    }

    private void setPlayers() {
        SharedPreferences sp = getSharedPreferences(getString(R.string.preferences), Activity.MODE_PRIVATE);
        int playerOneID = sp.getInt(getString(R.string.player_one_selected), -1);
        int playerTwoID = sp.getInt(getString(R.string.player_two_selected), -1);

        if(playerOneID >= 0 && playerTwoID >= 0)
        {
            playerOne = db.getPlayerFromID(playerOneID);
            playerTwo = db.getPlayerFromID(playerTwoID);
        }
        else if(playerOneID < 0)
        {
            Intent intent = new Intent(GameEmulatorActivity.this, PlayerActivity.class);
            intent.putExtra(getString(R.string.selected_player_key), true);
            GameEmulatorActivity.this.startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(GameEmulatorActivity.this, PlayerActivity.class);
            intent.putExtra(getString(R.string.selected_player_key), false);
            GameEmulatorActivity.this.startActivity(intent);
        }
    }

    private void setPlayerNames() {
        tvPlayerOneName.setText(playerOne.Name);
        tvPlayerTwoName.setText(playerTwo.Name);
    }

    private void setOnClickListeners() {
        btnPlayerOneWins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.incrementStat(playerOne._id, getString(R.string.wins_field_name));
                db.incrementStat(playerTwo._id, getString(R.string.losses_field_name));
                viewScoreboard();
            }
        });

        btnPlayerTwoWins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.incrementStat(playerTwo._id, getString(R.string.wins_field_name));
                db.incrementStat(playerOne._id, getString(R.string.losses_field_name));
                viewScoreboard();
            }
        });

        btnTie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.incrementStat(playerTwo._id, getString(R.string.ties_field_name));
                db.incrementStat(playerOne._id, getString(R.string.ties_field_name));
                viewScoreboard();
            }
        });
    }

    private void viewScoreboard()
    {
        Intent intent = new Intent(GameEmulatorActivity.this, ScoreboardActivity.class);
        GameEmulatorActivity.this.startActivity(intent);
    }

    private void getViews() {
        tvPlayerOneName = (TextView) findViewById(R.id.tvPlayerOneName);
        tvPlayerTwoName = (TextView) findViewById(R.id.tvPlayerTwoName);

        btnPlayerOneWins = (Button) findViewById(R.id.btnPlayerOneWins);
        btnPlayerTwoWins = (Button) findViewById(R.id.btnPlayerTwoWins);
        btnTie = (Button) findViewById(R.id.btnTie);
    }
}
