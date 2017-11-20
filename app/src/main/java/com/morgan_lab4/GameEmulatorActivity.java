package com.morgan_lab4;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_emulator);

        getViews();
        setOnClickListeners();
        setPlayerNames();
    }

    private void setPlayerNames() {
        tvPlayerOneName.setText("John Doe");
        tvPlayerTwoName.setText("Jane Doe");
    }

    private void setOnClickListeners() {
        btnPlayerOneWins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnPlayerTwoWins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnTie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void getViews() {
        tvPlayerOneName = (TextView) findViewById(R.id.tvPlayerOneName);
        tvPlayerTwoName = (TextView) findViewById(R.id.tvPlayerTwoName);

        btnPlayerOneWins = (Button) findViewById(R.id.btnSelectPlayerOne);
        btnPlayerTwoWins = (Button) findViewById(R.id.btnSelectPlayerTwo);
        btnTie = (Button) findViewById(R.id.btnTie);
    }
}
