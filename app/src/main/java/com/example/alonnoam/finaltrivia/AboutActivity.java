package com.example.alonnoam.finaltrivia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    SharedPreferences shrd;
    SharedPreferences.Editor editor;

    private TextView gamesPlayedView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        gamesPlayedView = (TextView)findViewById(R.id.textViewGamesPlayed);

        //play again button creation and listener:
        Button mButtonBack = (Button)findViewById(R.id.buttonAgain);
        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        shrd = getSharedPreferences("savefile", Context.MODE_PRIVATE);
        int gameCount = Integer.parseInt(shrd.getString("gameCounter", "0"));   //counter gets value from sharedPreferances (or 0 if not exist)
        gamesPlayedView.setText("Games Played: "+gameCount);

    }
}
