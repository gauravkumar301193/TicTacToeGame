//package com.directi.training.tictactoe;

import java.io.*;
import java.net.*;


public class TicTacToe {

    private char[][] board = new char[3][3];
    private int moveCount = 0;
    private int currentPlayer = 1;

    public TicTacToe() {
        int location = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = Character.forDigit(++location, 10);
            }
        }
    }



    protected void switchPlayers() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
        moveCount++;
    }

    protected boolean placeMarker(int location) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == Character.forDigit(location, 10)) {
                    board[row][col] = (currentPlayer == 1) ? 'X' : 'O';
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean winner() {
        return checkRow() || checkCol() || checkDia1() || checkDia2();
    }

    private boolean checkDia2() {
        char first = board[0][2];
        for (int idx = 1; idx < 3; idx++) {
            if (first != board[idx][2 - idx]) {
                break;
            }
            if (idx == 2) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDia1() {
        char first = board[0][0];
        for (int idx = 1; idx < 3; idx++) {
            if (first != board[idx][idx]) {
                break;
            }
            if (idx == 2) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCol() {
        for (int col = 0; col < 3; col++) {
            char first = board[0][col];
            for (int row = 1; row < 3; row++) {
                if (first != board[row][col]) {
                    break;
                }
                if (row == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkRow() {
        for (int row = 0; row < 3; row++) {
            char first = board[row][0];
            for (int col = 1; col < 3; col++) {
                if (first != board[row][col]) {
                    break;
                }
                if (col == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    protected String getRules() {
        StringBuilder builder = new StringBuilder();
        builder.append("Players take turns marking a square. Only squares \r\n");
        builder.append("not already marked can be picked. Once a player has \r\n");
        builder.append("marked three squares in a row, they win! If all squares \r\n");
        builder.append("are marked and no three squares are the same, a tied game is declared.\r\n");
        builder.append("Have Fun! \r\n\r\n");
        return builder.toString();
    }

    protected String drawBoard() {
        StringBuilder builder = new StringBuilder("Game board: \r\n");
        for (int i = 0; i < 3; i++) {
            for (int i1 = 0; i1 < 3; i1++) {
                builder.append("[").append(board[i][i1]).append("]");
            }
            builder.append("\r\n");
        }
        return builder.toString();
    }

    boolean isOver() {
        return moveCount >= 9 || winner();
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }
}