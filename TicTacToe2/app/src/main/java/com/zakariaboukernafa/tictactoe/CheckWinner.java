package com.zakariaboukernafa.tictactoe;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class CheckWinner extends Game {




    public static boolean checkForWin()  {

        String[][] board = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = button[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1])
                    && board[i][0].equals(board[i][2])
                    && !board[i][0].equals("")) {

                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[0][i].equals(board[1][i])
                    && board[0][i].equals(board[2][i])
                    && !board[0][i].equals("")) {
                return true;
            }
        }

        if (board[0][0].equals(board[1][1])
                && board[0][0].equals(board[2][2])
                && !board[0][0].equals("")) {
            return true;
        }

        return board[0][2].equals(board[1][1])
                && board[0][2].equals(board[2][0])
                && !board[0][2].equals("");

    }
}
