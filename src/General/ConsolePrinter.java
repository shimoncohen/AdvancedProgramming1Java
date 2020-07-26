package General;

import Interfaces.Printer;

import java.util.ArrayList;

/***
 * A printer when using console.
 */
public class ConsolePrinter implements Printer {
    public void printGameOpenning() {
        System.out.println("Welcome to Reversi!" + "Choose an opponent type:");
        System.out.println("1. a human local player" + "\n" + "2. an AI player" + "\n" + "3. a remote player");
    }

    public void connectedToServerMessage() {
        System.out.println("Connected to server");
    }

    public void waitingForConnectionMessage() {
        System.out.println("Waiting for opponent to join...");
    }

    public void failedMessage(Exception msg) {
        System.out.println(msg);
    }

    public void printInvalidGameMode() {
        System.out.println("Invalid option.");
        System.out.println("which mode would you like to play?");
        System.out.println("1. a human local player" + "2. an AI player" + "3. a remote player");
    }

    public void boardSizeMessage() {
        System.out.println("Please insert a valid board size (must be greater than or equal to four):");
    }

    public void waitingMessage() {
        System.out.println("Waiting for opponents move...");
    }

    public void printTurn(Enum.type playerType) {
        if(playerType == Enum.type.blackPlayer) {
            System.out.println("X: it's your move.");
        } else {
            System.out.println("O: it's your move.");
        }
    }

    public void requestMove() {
        System.out.println("Please enter your move row col: ");
    }

    public void printMove(Enum.type playerType, int x, int y) {
        if(playerType == Enum.type.blackPlayer) {
            System.out.println("X played " + x + " " + y);
        } else {
            System.out.println("O played " + x + " " + y);
        }
    }

    public void printPoint(int[] point) {
        System.out.println(point[0] + " " + point[1]);
    }

    public void printInvalidMove() {
        System.out.println("invalid move!");
    }

    public void printPossibleMoves(ArrayList<Point> possibleMoves) {
        System.out.println("Your possible moves: ");
        // go over the vector and print the points it contains.
        for (int k = 0; k < possibleMoves.size(); k++) {
            if(k != 0) {
                System.out.print(",");
            }
            System.out.print("(" + possibleMoves.get(k).getX() + "," + possibleMoves.get(k).getY() + ")");
        }
        System.out.println();
    }

    public void printNoMoves(Enum.type playerType) {
        if(playerType == Enum.type.blackPlayer) {
            System.out.println("X has no available moves! " + "Turn goes to opponent.");
        } else if(playerType == Enum.type.whitePlayer) {
            System.out.println("O has no available moves! " + "Turn goes to opponent.");
        }
    }

    public void printOpponentHasNoMoves() {
        System.out.println("Opponent has no available moves!");
    }

    public void printWinMessage(char winner) {
        // if the char is t then the game ended in a tie.
        if(winner == 't') {
            System.out.println("it's a tie!");
            // else print the winner message.
        } else {
            System.out.println(winner + " wins!");
        }
    }

    public void printClientMenu() {
        System.out.println("please choose operation number. If you choose 1 or 3, also enter name of game: ");
        System.out.println("0. cancel");
        System.out.println("1.'start' to start a new game.");
        System.out.println("2.'list' to view the list of waiting games.");
        System.out.println("3.'join' to join a waiting game.");
    }

    public void gameDeniedMessage(String msg) {
        if(msg.compareTo("NotExist") == 0) {
            System.out.println("Error! The game doesn't exist.");
        } else if(msg.compareTo("AlreadyExist") == 0) {
            System.out.println("Error! The game already exists.");
        }
    }

    public void gameNotOption() {
       System.out.println("Invalid option! Please choose again.");
    }

    public void printGamesList(int sizeOfList, String list) {
        // i list is empty
        if (sizeOfList == 0) {
            System.out.println("List is empty. There are not waiting games.");
        } else {
            System.out.println("List of available games:");
            for (int i = 0; i < sizeOfList; i++) {
                System.out.println(list.charAt(i));
            }
            System.out.print("\n");
        }
    }
}
