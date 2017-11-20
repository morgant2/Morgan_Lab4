package com.morgan_lab4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPlayerActivity extends AppCompatActivity {

    private PersonDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        Button btnAddNewPlayer = (Button) findViewById(R.id.btnAddNewPlayer);

        db = new PersonDB(getApplicationContext());

        btnAddNewPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etNewPlayer = (EditText) findViewById(R.id.etAddPlayer);

                db.insertPerson(etNewPlayer.getText().toString());
            }
        });
    }
}
