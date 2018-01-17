// 315383133 shimon cohen
// 302228275 Nadav Spitzer
package Interfaces;

import General.Enum;
import General.Point;

import java.util.ArrayList;

public interface Printer {
    /*
     * function name: printGameOpenning.
     * input: none.
     * output: none.
     * operation: prints the game openning messages.
     */
    void printGameOpenning();
    /*
     * function name: connectedToServerMessage.
     * input: none.
     * output: none.
     * operation: prints a message that client connected to the server.
     */
    void connectedToServerMessage();
    /*
     * function name: waitingForConnectionMessage.
     * input: none.
     * output: none.
     * operation: prints a message when waiting for opponent to connect to server.
     */
    void waitingForConnectionMessage();
    /*
     * function name: failedConnectingToServer.
     * input: Exception message.
     * output: none.
     * operation: prints a message that client failed to connected to the server.
     */
    void failedMessage(Exception msg);
    /*
     * function name: printInvalidGameMode.
     * input: none.
     * output: none.
     * operation: prints a message that the users game mode choice is invalid.
     */
    void printInvalidGameMode();
    /*
     * function name: boardSizeMessage.
     * input: none.
     * output: none.
     * operation: print a message asking the user to enter a valid board size.
     */
    void boardSizeMessage();
    /*
     * function name: waitingMessage.
     * input: none.
     * output: none.
     * operation: prints a message when waiting for opponent.
     */
    void waitingMessage();
    /*
     * function name: printTurn.
     * input: a char indicating the current player.
     * output: none.
     * operation: prints a message that its a certain player turn.
     */
    void printTurn(Enum.type playerType);
    /*
     * function name: requestMove.
     * input: none.
     * output: none.
     * operation: prints a message asking the player to enter a move.
     */
    void requestMove();
    /*
     * function name: printPoint.
     * input: the point to print.
     * output: none.
     * operation: prints a given point.
     */
    void printPoint(int point[]);
    /*
     * function name: printMove.
     * input: the player who made the move and the x and y values of the move.
     * output: none.
     * operation: print the move the player entered.
     */
    void printMove(Enum.type playerType, int x, int y);
    /*
     * function name: printInvalidMove.
     * input: none.
     * output: none.
     * operation: prints a message indicating the player entered an invalid move.
     */
    void printInvalidMove();
    /*
     * function name: printPossibleMoves.
     * input: a list of the players possible moves as points.
     * output: none.
     * operation: prints all of the points in the list.
     */
    void printPossibleMoves(ArrayList<Point> possibleMoves);
    /*
     * function name: printNoMoves.
     * input: none.
     * output: none.
     * operation: prints a message if player has no moves.
     */
    void printNoMoves(Enum.type playerType);
    /*
     * function name: printOpponentHasNoMoves.
     * input: none.
     * output: none.
     * operation: prints a message if opponent has no moves.
     */
    void printOpponentHasNoMoves();
    /*
     * function name: printWinMessage.
     * input: the player that won.
     * output: none.
     * operation: print a win message according to the player who won.
     */
    void printWinMessage(char winner);
    /*
     * function name: printClientMenu.
     * input: none.
     * output: none.
     * operation: prints the server clients menu.
     */
    void printClientMenu();
    /*
     * function name: gameDeniedMessage.
     * input: String message.
     * output: none.
     * operation: prints the message sent from the server.
     */
    void gameDeniedMessage(String msg);
    /*
     * function name: gameNotOption.
     * input: none.
     * output: none.
     * operation: prints a message indicating that the game mode chosen isn't an option.
     */
    void gameNotOption();
    /*
     * function name: printGamesList.
     * input: the size of the game list and the list itself to print (as a string).
     * output: none.
     * operation: prints the list of available games sent from the server.
     *            if the list is empty then print an appropriate message.
     */
    void printGamesList(int sizeOfList,String list);
}
