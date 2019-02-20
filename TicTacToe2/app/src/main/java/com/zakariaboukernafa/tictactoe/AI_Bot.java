package com.zakariaboukernafa.tictactoe;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AI_Bot extends Game implements View.OnClickListener {


    public boolean player1 = true;

    public int roundCount;

    private int player1Score;
    private int Ai_Score;

    private TextView textViewPlayer1;
    private TextView textViewAi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textViewPlayer1 = findViewById(R.id.Player1);
        textViewAi = findViewById(R.id.Player2);

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

            roundCount++;
            if (CheckWinner.checkForWin()) {
                if (player1) {
                    player1Wins();
                    return;
                } else {
                    AiWins();
                    return;
                }
            } else if (roundCount == 9) {
                draw();
                return;
            }
            player1 = !player1;
        }

        Ai_Move().setText("O");

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


        roundCount++;

        if (CheckWinner.checkForWin()) {
            if (player1) {
                player1Wins();
                return;
            } else {
                AiWins();
                return;
            }
        } else if (roundCount == 9) {
            draw();
            return;
        }

        player1 = !player1;


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

    private void AiWins() {
        Ai_Score++;
        Toast.makeText(this, "Ai wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
        Lose_Sound = MediaPlayer.create(this, R.raw.lose_sound);
        Lose_Sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
                Lose_Sound = null;
            }
        });
        Lose_Sound.start();
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

    private void updatePointsText() {
        textViewPlayer1.setText("Player 1: " + player1Score);
        textViewAi.setText("Ai : " + Ai_Score);
    }


    private void resetGame() {
        player1Score = 0;
        Ai_Score = 0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Score);
        outState.putInt("player2Points", Ai_Score);
        outState.putBoolean("player1Turn", player1);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Score = savedInstanceState.getInt("player1Points");
        Ai_Score = savedInstanceState.getInt("player2Points");
        player1 = savedInstanceState.getBoolean("player1Turn");
    }

    public static Button Ai_Move() {
        String[][] Move = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Move[i][j] = button[i][j].getText().toString();
            }
        }


// attack
        //diagonal to right11
        if (Move[0][0].equals(Move[1][1]) && (Move[0][0].equals("O"))
                && Move[2][2].equals("")) {
            return button[2][2];
        }
        if (Move[0][0].equals(Move[2][2]) && (Move[0][0].equals("O"))
                && Move[1][1].equals("")) {
            return button[1][1];
        }


        if (Move[1][1].equals(Move[2][2]) && (Move[1][1].equals("O"))
                && Move[0][0].equals("")) {
            return button[0][0];
        }


        //diagonal to left
        if (Move[0][2].equals(Move[1][1]) && (Move[0][2].equals("O"))
                && Move[2][0].equals("")) {
            return button[2][0];
        }
        if (Move[0][2].equals(Move[2][0]) && (Move[0][2].equals("O"))
                && Move[1][1].equals("")) {
            return button[1][1];
        }


        if (Move[1][1].equals(Move[2][0]) && (Move[1][1].equals("O"))
                && Move[0][2].equals("")) {
            return button[0][2];
        }


        //vertical
        if (Move[0][0].equals(Move[0][1]) && (Move[0][0].equals("O"))
                && Move[0][2].equals("")) {
            return button[0][2];
        }
        if (Move[0][0].equals(Move[0][2]) && (Move[0][0].equals("O"))
                && Move[0][1].equals("")) {
            return button[0][1];
        }
        if (Move[0][1].equals(Move[0][2]) && (Move[0][1].equals("O"))
                && Move[0][0].equals("")) {
            return button[0][0];
        }


        if (Move[1][0].equals(Move[1][1]) && (Move[1][0].equals("O"))
                && Move[1][2].equals("")) {
            return button[1][2];
        }
        if (Move[1][0].equals(Move[1][2]) && (Move[1][0].equals("O"))
                && Move[1][1].equals("")) {
            return button[1][1];
        }
        if (Move[1][1].equals(Move[1][2]) && (Move[1][1].equals("O"))
                && Move[1][0].equals("")) {
            return button[1][0];
        }


        if (Move[2][0].equals(Move[2][1]) && (Move[2][0].equals("O"))
                && Move[2][2].equals("")) {
            return button[2][2];
        }
        if (Move[2][0].equals(Move[2][2]) && (Move[2][0].equals("O"))
                && Move[2][1].equals("")) {
            return button[2][1];
        }
        if (Move[2][1].equals(Move[2][2]) && (Move[2][1].equals("O"))
                && Move[2][0].equals("")) {
            return button[2][0];
        }

        //horizontal

        if (Move[0][0].equals(Move[1][0]) && (Move[0][0].equals("O"))
                && Move[2][0].equals("")) {
            return button[2][0];
        }
        if (Move[0][0].equals(Move[2][0]) && (Move[0][0].equals("O"))
                && Move[1][0].equals("")) {
            return button[1][0];
        }
        if (Move[1][0].equals(Move[2][0]) && (Move[1][0].equals("O"))
                && Move[0][0].equals("")) {
            return button[0][0];
        }


        if (Move[0][1].equals(Move[1][1]) && (Move[0][1].equals("O"))
                && Move[2][1].equals("")) {
            return button[2][1];
        }
        if (Move[0][1].equals(Move[2][1]) && (Move[0][1].equals("O"))
                && Move[1][1].equals("")) {
            return button[1][1];
        }
        if (Move[1][1].equals(Move[2][1]) && (Move[1][1].equals("O"))
                && Move[0][1].equals("")) {
            return button[0][1];
        }


        if (Move[0][2].equals(Move[1][2]) && (Move[0][2].equals("O"))
                && Move[2][2].equals("")) {
            return button[2][2];
        }
        if (Move[0][2].equals(Move[2][2]) && (Move[0][2].equals("O"))
                && Move[1][2].equals("")) {
            return button[1][2];
        }
        if (Move[1][2].equals(Move[2][2]) && (Move[1][2].equals("O"))
                && Move[0][2].equals("")) {
            return button[0][2];
        }


// defensive
        //diagonal to right
        if (Move[0][0].equals(Move[1][1])
                && Move[2][2].equals("")) {
            return button[2][2];
        }
        if (Move[0][0].equals(Move[2][2])
                && Move[1][1].equals("")) {
            return button[1][1];
        }


        if (Move[1][1].equals(Move[2][2])
                && Move[0][0].equals("")) {
            return button[0][0];
        }


        //diagonal to left
        if (Move[0][2].equals(Move[1][1])
                && Move[2][0].equals("")) {
            return button[2][0];
        }
        if (Move[0][2].equals(Move[2][0])
                && Move[1][1].equals("")) {
            return button[1][1];
        }


        if (Move[1][1].equals(Move[2][0])
                && Move[0][2].equals("")) {
            return button[0][2];
        }


        //vertical
        if (Move[0][0].equals(Move[0][1])
                && Move[0][2].equals("")) {
            return button[0][2];
        }
        if (Move[0][0].equals(Move[0][2])
                && Move[0][1].equals("")) {
            return button[0][1];
        }
        if (Move[0][1].equals(Move[0][2])
                && Move[0][0].equals("")) {
            return button[0][0];
        }


        if (Move[1][0].equals(Move[1][1])
                && Move[1][2].equals("")) {
            return button[1][2];
        }
        if (Move[1][0].equals(Move[1][2])
                && Move[1][1].equals("")) {
            return button[1][1];
        }
        if (Move[1][1].equals(Move[1][2])
                && Move[1][0].equals("")) {
            return button[1][0];
        }


        if (Move[2][0].equals(Move[2][1])
                && Move[2][2].equals("")) {
            return button[2][2];
        }
        if (Move[2][0].equals(Move[2][2])
                && Move[2][1].equals("")) {
            return button[2][1];
        }
        if (Move[2][1].equals(Move[2][2])
                && Move[2][0].equals("")) {
            return button[2][0];
        }

        //horizontal

        if (Move[0][0].equals(Move[1][0])
                && Move[2][0].equals("")) {
            return button[2][0];
        }
        if (Move[0][0].equals(Move[2][0])
                && Move[1][0].equals("")) {
            return button[1][0];
        }
        if (Move[1][0].equals(Move[2][0])
                && Move[0][0].equals("")) {
            return button[0][0];
        }


        if (Move[0][1].equals(Move[1][1])
                && Move[2][1].equals("")) {
            return button[2][1];
        }
        if (Move[0][1].equals(Move[2][1])
                && Move[1][1].equals("")) {
            return button[1][1];
        }
        if (Move[1][1].equals(Move[2][1])
                && Move[0][1].equals("")) {
            return button[0][1];
        }


        if (Move[0][2].equals(Move[1][2])
                && Move[2][2].equals("")) {
            return button[2][2];
        }
        if (Move[0][2].equals(Move[2][2])
                && Move[1][2].equals("")) {
            return button[1][2];
        }
        if (Move[1][2].equals(Move[2][2])
                && Move[0][2].equals("")) {
            return button[0][2];
        }


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Move[2][1].equals("")) {
                    return button[2][1];
                } else if (Move[i][j].equals("")) {
                    return button[i][j];

                }
            }
        }

        return (button[1][1]);
    }


}



