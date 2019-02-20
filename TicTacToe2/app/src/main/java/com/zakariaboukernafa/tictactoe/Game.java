package com.zakariaboukernafa.tictactoe;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Game extends MainActivity implements View.OnClickListener {

    public static Button[][] button = new Button[3][3];

    public boolean player1 = true;
    public int roundCount;
    private int player1Score;
    private int player2Score;
    MediaPlayer Click_sound, Win_sound, Lose_Sound, Draw_Sound;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textViewPlayer1 = findViewById(R.id.Player1);
        textViewPlayer2 = findViewById(R.id.Player2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "Button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                button[i][j] = findViewById(resID);
                button[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.Reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });


    }

    @Override
    public void onClick(View v) {

        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (player1) {
            ((Button) v).setText("X");

            Click_sound = MediaPlayer.create(this, R.raw.click);
            Click_sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.reset();
                    mp.release();
                    Click_sound = null;
                }
            });
            Click_sound.start();
        } else {
            ((Button) v).setText("O");
            Click_sound = MediaPlayer.create(this, R.raw.click);
            Click_sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.reset();
                    mp.release();
                    Click_sound = null;
                }
            });
            Click_sound.start();

        }

        roundCount++;

        if (CheckWinner.checkForWin()) {
            if (player1) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1 = !player1;
        }


    }


    private void player1Wins() {
        player1Score++;
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
        Win_sound = MediaPlayer.create(this, R.raw.win_sound);
        Win_sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
                Win_sound = null;
            }
        });
        Win_sound.start();

    }

    private void player2Wins() {
        player2Score++;
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
        Win_sound = MediaPlayer.create(this, R.raw.win_sound);
        Win_sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
                Win_sound = null;
            }
        });
        Win_sound.start();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
        Draw_Sound = MediaPlayer.create(this, R.raw.draw);
        Draw_Sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
                Draw_Sound = null;
            }
        });
        Draw_Sound.start();
    }

    private void updatePointsText() {
        textViewPlayer1.setText("Player 1: " + player1Score);
        textViewPlayer2.setText("Player 2: " + player2Score);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                button[i][j].setText("");
            }
        }

        roundCount = 0;
        player1 = true;
    }

    private void resetGame() {
        player1Score = 0;
        player2Score = 0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Score);
        outState.putInt("player2Points", player2Score);
        outState.putBoolean("player1Turn", player1);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Score = savedInstanceState.getInt("player1Points");
        player2Score = savedInstanceState.getInt("player2Points");
        player1 = savedInstanceState.getBoolean("player1Turn");
    }
}
