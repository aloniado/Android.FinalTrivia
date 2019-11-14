package com.example.alonnoam.finaltrivia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {

    private TextView mUpperMessage;
    private TextView mPointsShow;
    private TextView mBottomMessage;
    private String value;

    private int sound;

    private Button mButtonBack;
    private Button mButtonAbout;
    private Button mButtonShare;

    //back button kills activity and sends to first page instead of last question:
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);


        Bundle extras = getIntent().getExtras();    //getting score sent from game activity
        value = extras.getString("key");

        if (savedInstanceState==null){
            sound=1;
        }
        else {
            sound = savedInstanceState.getInt("sound");
        }


        //About button creation and listener:
        Button mButtonAbout = (Button)findViewById(R.id.buttonAbout);
        mButtonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
            }
        });

        //play again button creation and listener:
        Button mButtonBack = (Button)findViewById(R.id.buttonAgain);
        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        //share score button:
        Button mButtonShare = (Button)findViewById(R.id.buttonShare);
        mButtonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "I've just scored "+value+" points on FinalTrivia!\nThink you can beat me?");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });



        mUpperMessage  = (TextView)findViewById(R.id.textViewUpperMesage);
        mPointsShow  = (TextView)findViewById(R.id.textViewPoints);
        mBottomMessage  = (TextView)findViewById(R.id.textViewBottomMessage);

        //sound:
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.end);
        if (sound==1){
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp)
                {
                    mp.release();
                } });
            mp.start();
            sound=0;
        }



        if (extras != null) {   //extras are values sent from previous activity

            //updating points message:
            mPointsShow.setText(""+value+" Points");

            String UpMess = "";
            String DownMess = "";
            int valueInt;
            valueInt = Integer.parseInt(value); //valueInt is an integer holding score

            //updating messages strings:
            switch (valueInt) {
                case 5:
                    UpMess = "WOW";
                    DownMess = "perfect score!";
                    break;
                case 4:
                    UpMess = "VERY NICE";
                    DownMess = "you are amazing";
                    break;
                case 3:
                    UpMess = "NICE";
                    DownMess = "you know stuff";
                    break;
                case 2:
                    UpMess = "MEH";
                    DownMess = "not very good";
                    break;
                case 1:
                    UpMess = "OH NO";
                    DownMess = "maybe try again?";
                    mPointsShow.setText(""+value+" Point");
                    break;
                case 0:
                    UpMess = "EMBARRASSING";
                    DownMess = "was that on purpase?";
                    break;
                default:

            }

            //updating messages on screen:
            mUpperMessage.setText(UpMess);
            mBottomMessage.setText(DownMess);

        }


    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable("sound", sound);

        super.onSaveInstanceState(savedInstanceState);
    }
}
