package com.morgan_lab4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Intent intent = getIntent();
        Boolean isFirstPlayer = intent.getBooleanExtra(getString(R.string.selected_player_key), false);
        if(isFirstPlayer)
        {

        }
        else
        {

        }
    }
}
