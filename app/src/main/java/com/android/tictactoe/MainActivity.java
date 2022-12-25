package com.android.tictactoe;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.audiofx.AudioEffect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    // 0 is yellow and 1 is red 2:Empty
    int activePlayer=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int [][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    String winner; int moves=0;

    boolean gameActive=true;
    public void dropIn(View view)
    {
        ImageView counter=(ImageView) view;
        int tappedCounter= Integer.parseInt(counter.getTag().toString());
        MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.arcade_bleep_sound);
        mediaPlayer.start();
        if(gameState[tappedCounter]==2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    //someone won
                    gameActive = false;
                    if (activePlayer == 1) {
                        winner = "Yellow";

                    } else {
                        winner = "Red";
                    }
                    if(winner=="Red" || winner=="Yellow")
                    {
                        mediaPlayer.stop();
                    }
                    Button playAgainButton = (Button) findViewById(R.id.button);
                    TextView text = (TextView) findViewById(R.id.textView);
                    text.setText(winner + " Has Won!");
                    playAgainButton.setVisibility(View.VISIBLE);
                    text.setVisibility(View.VISIBLE);
                }
            }
        }

    }
    public void playAgain(View view)
    {
        Intent t= new Intent(MainActivity.this,MainActivity.class);
        startActivity(t);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}