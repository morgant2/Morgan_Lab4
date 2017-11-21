package com.morgan_lab4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PlayerActivity extends AppCompatActivity {
    private PersonDB db;
    private ListView lvSelectablePlayers;
    private boolean isFirstPlayer;
    private TextView tvSelectPlayer;
    private ArrayList<Player> players;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Intent intent = getIntent();
        isFirstPlayer = intent.getBooleanExtra(getString(R.string.selected_player_key), false);
        db = new PersonDB(getApplicationContext());
        lvSelectablePlayers = (ListView) findViewById(R.id.lvSelectablePlayers);
        tvSelectPlayer = (TextView) findViewById(R.id.tvSelectPlayer);

        setListViewNames();

        if(isFirstPlayer)
        {
            tvSelectPlayer.setText("Select Player 1");
        }
        else
        {
            tvSelectPlayer.setText("Select Player 2");
        }

        lvSelectablePlayers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int selectedPlayerID = players.get(i)._id;

                Intent intent = new Intent(PlayerActivity.this, MainMenuActivity.class);

                if (isFirstPlayer)
                    intent.putExtra(getString(R.string.player_one_selected), selectedPlayerID);
                else
                    intent.putExtra(getString(R.string.player_two_selected), selectedPlayerID);

                PlayerActivity.this.startActivity(intent);
            }
        });

    }

    private void setListViewNames() {
        players = db.getAllPlayers();
        List<String> playerNames = new ArrayList<String>();

        for(Player player : players)
        {
            playerNames.add(player.Name);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, playerNames);
        lvSelectablePlayers.setAdapter(adapter);
    }
}
