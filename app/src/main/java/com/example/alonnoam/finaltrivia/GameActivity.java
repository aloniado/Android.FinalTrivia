package com.example.alonnoam.finaltrivia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private QuestionLibrary mQuestionLibrary = new QuestionLibrary();

    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice0;
    private Button mButtonChoice1;
    private Button mButtonChoice2;

    private String mAnswer;
    private String CorrectWrong;

    private int QuestionAmountForGame = 5;        //how many questions will be in each game

    private int[] questionArrForGame = new int[QuestionAmountForGame];  //array of int indicating questions numbers
    private int[] answerArrForQuestion = new int[3];                    //order of answers
    private int mQuestionCount;                                         //number of answered questions this game
    private int mScore;

    //sharedPreferences for games played counter:
    SharedPreferences shrd;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mScoreView = (TextView)findViewById(R.id.scoreText);
        mQuestionView = (TextView)findViewById(R.id.question);
        mButtonChoice0 = (Button)findViewById(R.id.ch1);
        mButtonChoice1 = (Button)findViewById(R.id.ch2);
        mButtonChoice2 = (Button)findViewById(R.id.ch3);

        //-------------------------------------------------restoring data on screen rotation:
        if (savedInstanceState==null){
            //questionArrForGame = mQuestionLibrary.getQuestionNumbersArr(QuestionAmountForGame); //questionArrForGame is an array of ints containing numbers of questions to show
            questionArrForGame = getRandomIntegerArr(QuestionAmountForGame, 0, mQuestionLibrary.getTotalQuestionNumber()-1);
            mQuestionCount = 0;                     //number of answered questions this game
            mScore = 0;
        }
        else
        {
            int i;

            int[] tempQuestionArray = savedInstanceState.getIntArray("questionsArr");
            if (tempQuestionArray != null && tempQuestionArray.length == questionArrForGame.length){
                for (i=0 ; i<tempQuestionArray.length ; i++){
                    questionArrForGame[i] = tempQuestionArray[i];
                }
            }
            mQuestionCount = savedInstanceState.getInt("questionNumber");
            mScore = savedInstanceState.getInt("score");
        }

        //after restoring values, updating screen:
        updateScreen();

        //-------------------------------------------------------------------------button listeners:
        mButtonChoice0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonChoice0.getText() == mAnswer){
                    mScore++;
                    CorrectWrong = "Correct";
                    playSound(1);
                }
                else {
                    CorrectWrong = "Wrong";
                    playSound(0);
                }
                mQuestionCount++;
                Toast.makeText(GameActivity.this, CorrectWrong, Toast.LENGTH_SHORT).show();
                updateScreen();
            }
        });

        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mButtonChoice1.getText() == mAnswer){
                    mScore++;
                    CorrectWrong = "Correct";
                    playSound(1);
                }
                else {
                    CorrectWrong = "Wrong";
                    playSound(0);
                }
                mQuestionCount++;
                Toast.makeText(GameActivity.this, CorrectWrong, Toast.LENGTH_SHORT).show();
                updateScreen();
            }
        });

        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonChoice2.getText() == mAnswer){
                    mScore++;
                    CorrectWrong = "Correct";
                    playSound(1);
                }
                else {
                    CorrectWrong = "Wrong";
                    playSound(0);
                }
                mQuestionCount++;
                Toast.makeText(GameActivity.this, CorrectWrong, Toast.LENGTH_SHORT).show();
                updateScreen();
            }
        });

    }


    //-------------------------------------------------saving data on screen rotation:
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable("questionsArr", questionArrForGame);
        savedInstanceState.putInt("questionNumber", mQuestionCount);
        savedInstanceState.putInt("score", mScore);

        super.onSaveInstanceState(savedInstanceState);
    }
    //--------------------------------------------------------------------------------------methods:


    private void updateScreen(){
        if (mQuestionCount < QuestionAmountForGame) //checking if game is over (5 questions answered)
        {
            mQuestionView.setText(mQuestionLibrary.getQuestion(questionArrForGame[mQuestionCount]));

            answerArrForQuestion = getRandomIntegerArr(3,0,2);   //array of 3 integers with random nombers (0-2) for answer placement

            mButtonChoice0.setText(mQuestionLibrary.getChoice(questionArrForGame[mQuestionCount],answerArrForQuestion[0]));
            mButtonChoice1.setText(mQuestionLibrary.getChoice(questionArrForGame[mQuestionCount],answerArrForQuestion[1]));
            mButtonChoice2.setText(mQuestionLibrary.getChoice(questionArrForGame[mQuestionCount],answerArrForQuestion[2]));
            mScoreView.setText("Score: "+mScore);

            mAnswer = mQuestionLibrary.getAnswer(questionArrForGame[mQuestionCount]);
        }
        else
        {   //game finished:

            //increasing counter in sharedPreferences:
            shrd = getSharedPreferences("savefile", Context.MODE_PRIVATE);
            int gameCount = Integer.parseInt(shrd.getString("gameCounter", "0"));   //counter gets value from sharedPreferances (or 0 if not exist)
            gameCount++;

            editor = shrd.edit();
            editor.putString("gameCounter",gameCount+"");
            editor.apply();
            //------------------------------------------
            //sending user to finish activity:
            Intent fa = new Intent(GameActivity.this, FinishActivity.class);
            fa.putExtra("key",""+mScore);
            startActivity(fa);
        }

    }

    public int[] getRandomIntegerArr(int arrSize, int min, int max){    //creates an array of n random numbers values min-max

        Random rand = new Random();

        int i, j, appearances;
        int[] randArr = new int[arrSize];

        for (i=0 ; i<arrSize ; i++){
            randArr[i]=-1;
        }

        for (i = 0 ; i<arrSize ; i++){
            do {
                appearances=0;
                randArr[i]= min + rand.nextInt(max+1);

                for (j=0 ; j<arrSize ; j++){
                    if (randArr[j]==randArr[i]){
                        appearances++;
                    }
                }
            }
            while (appearances>1);
        }
        return randArr;
    }


    public int playSound(int ans){
        if (ans==1){
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.correctans);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp)
                {
                    mp.release();
                } });
            mp.start();
        }
        else if(ans==0){
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.falseans);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp)
                {
                    mp.release();
                } });
            mp.start();
        }
        return 1;
    }
}
