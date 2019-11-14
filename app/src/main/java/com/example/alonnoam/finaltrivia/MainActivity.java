package com.example.alonnoam.finaltrivia;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mButtonPlay;

    //back button kills activity and sends to first page instead of last question:
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Exiting Game")
                    .setMessage("Are you sure you want to leave?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonPlay = (Button)findViewById(R.id.ButtonPlay);

        ImageView myImage = (ImageView) findViewById(R.id.imageView3);
        myImage.setAlpha(127);



        //----------------------------play button listener:
        mButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.newgame);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { //sound completion listene
                    @Override
                    public void onCompletion(MediaPlayer mp)
                    {
                        mp.release();
                    } });
                mp.start(); //plays sound
                Intent k = new Intent(MainActivity.this, GameActivity.class);
                startActivity(k);
            }
        });



    }
}
