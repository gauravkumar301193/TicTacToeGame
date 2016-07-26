/**
 * Created by gaurav.kum on 26/07/16.
 */

import java.io.*;
import java.net.*;

import static java.lang.System.out;

public class NewClientThread extends Thread {
        private final Socket socket1, socket2;
        private String player1, player2;
        private final TicTacToe game = new TicTacToe();
        public NewClientThread(Socket skt1, Socket skt2) {
            this.socket1 = skt1;
            this.socket2 = skt2;
        }

        public void run() {
            BufferedReader in;
            PrintStream output;
            try {
                BufferedReader in1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
                PrintStream out1 = new PrintStream(socket1.getOutputStream());
                out1.println("Welcome! Tic Tac Toe is a two player game.");
                out1.print("Enter player one's name: ");
                player1 = in1.readLine();

                BufferedReader in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
                PrintStream out2 = new PrintStream(socket2.getOutputStream());

                out2.print("Enter player two's name: ");
                player2 = in2.readLine();


                out1.println();
                out1.println(game.getRules());
                out1.println();
                out1.println(game.drawBoard());
                out1.println();

                out2.println();
                out2.println(game.getRules());
                out2.println();
                out2.println(game.drawBoard());
                out2.println();



                while (!game.isOver()) {
                    boolean validPick = false;


                    if (game.getCurrentPlayer() == 1) {
                        in = in1;
                        output = out1;
                    } else {
                        in = in2;
                        output = out2;
                    }

                    while (!validPick) {
                        output.print("It is " + currentPlayerName() + "'s turn. Pick a square: ");

                        try {
                            int pick = Integer.parseInt(in.readLine());
                            validPick = game.placeMarker(pick);
                        } catch (NumberFormatException e) {
                            //Do nothing here,
                        }

                        if (!validPick) {
                            output.println("Square can not be selected. Retry");
                        }
                    }
                    output.println();
                    output.println(game.drawBoard());
                    output.println();

                    if (!game.isOver())
                        game.switchPlayers();

                }

                if (game.winner()) {
                    out.println("Game Over - " + currentPlayerName() + " WINS!!!");
                    //myServerSocket.close();

                } else {
                    out.println("Game Over - Draw");
                    //myServerSocket.close();
                }
            }
            catch(IOException e )
            {
                e.printStackTrace();
            }

        }
    public String currentPlayerName() {
        return game.getCurrentPlayer() == 1 ? player1 : player2;
    }

}
