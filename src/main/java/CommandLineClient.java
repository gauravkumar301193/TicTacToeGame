//package com.directi.training.tictactoe;

import java.io.*;
import java.net.*;

import static java.lang.System.out;

public class CommandLineClient {
    private String player1;
    private String player2;
    private final TicTacToe game = new TicTacToe();
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public CommandLineClient() {
        try {
            ServerSocket myServerSocket = new ServerSocket(6666);
            while (true) {
                Socket skt1 = myServerSocket.accept();


                Socket skt2 = myServerSocket.accept();
                new NewClientThread(skt1, skt2).start();

            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

  /*  protected String getPrompt() {
        String prompt = "";
        try {
            prompt = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prompt;
    }

*/

    public static void main(String[] args) {
        new CommandLineClient();
    }

}