package General;

import StyleAndMain.ReversiBoard;
import Interfaces.GameLogic;
import java.util.ArrayList;

/**
 * the class of the standard game logic.
 */
public class StandardGameLogic implements GameLogic {
    public ArrayList<Point> availableMoves(ReversiBoard board, Enum.type type1) {
        char a, o;
        ArrayList<Point> options = new ArrayList<>();
        if(type1 == Enum.type.blackPlayer) {
            //other players piece, to search for valid move.
            a = 'o';
            o = 'x';
        } else {
            a = 'x';
            o = 'o';
        }
        //go over the board cells.
        for(int i = 0; i < board.getSize(); i++) {
            for(int k = 0; k < board.getSize(); k++) {
                //if the cell is empty.
                if (board.checkCell(i, k) == ' ') {
                    boolean exists = false;
                    //check all adjacent cells.
                    for(int j = -1; j <= 1; j++) {
                        for(int s = -1; s <= 1; s++) {
                            //if the adjacent cell has the other players piece and its a valid move.
                            if (board.checkCell(i + j, k + s) == a && validMove(board, i + j, k + s, j, s, o, 0)) {
                                exists = true;
                            }
                        }
                    }
                    if(exists) {
                        //add the cell as a valid move.
                        options.add(new Point(i + 1, k + 1));
                    }
                }
            }
        }
        return options;
    }

    public boolean validOption(ReversiBoard board, int x, int y, ArrayList<Point> options) {
        if(isGameWon(board)) {
            return true;
        }
        if (x > 0 && y > 0 && x <= board.getSize() && y <= board.getSize()) {
            //go over all of the possible moves.
            for (Point option : options) {
                //if the move is a possible move.
                if (x == option.getX() && y == option.getY()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void changeTiles(Player player, int x, int y, ReversiBoard board) {
        char o;
        board.putTile(x, y, player);
        if(player.getType() == Enum.type.blackPlayer) {
            //players piece, to search for valid flips.
            o = 'x';
        } else {
            o = 'o';
        }
        //go over the board cells.
        for(int i = -1; i <= 1; i++) {
            for(int k = -1; k <= 1; k++) {
                //if the cell is a valid move.
                if ((i != 0 || k != 0) && validMove(board, x, y, i, k, o, 0)) {
                    //flip all of the tiles for each valid move.
                    flipTiles(o, x + i, y + k, i, k, board, player);
                }
            }
        }
    }

    public char gameWon(ReversiBoard board) {
        int blackPieces = 0, whitePieces = 0;
        //counts the black and white pieces on the board.
        for(int i = 0; i < board.getSize(); i++) {
            for(int k = 0; k < board.getSize(); k++) {
                if(board.checkCell(i, k) == 'x') {
                    blackPieces++;
                } else if(board.checkCell(i, k) == 'o'){
                    whitePieces++;
                }
            }
        }
        //declares the winner depending by the amount of pieces each player has on the board.
        if(blackPieces > whitePieces) {
            return 'X';
        } else if(whitePieces > blackPieces) {
            return 'O';
        } else {
            return 't';
        }
    }

    public boolean isGameWon(ReversiBoard board) {
        //check if board is full.
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.checkCell(i, j) == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean gameFinalMove(ReversiBoard board, Enum.type pType, int x, int y) {
        if (x == -2 && y == -2) {
            return true;
        }
        ArrayList<Point> moves = availableMoves(board, pType);
        if(moves.isEmpty()) {
            if(pType == Enum.type.blackPlayer) {
                moves = availableMoves(board, Enum.type.whitePlayer);
            } else {
                moves = availableMoves(board, Enum.type.blackPlayer);
            }
            if(moves.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean validMove(ReversiBoard board, int x, int y, int right, int down, char piece, int iteration) {
        if(x > board.getSize() || y > board.getSize() || x < 0 || y < 0 || board.checkCell(x, y) == ' ') {
            return false;
        }
        if(iteration > 0 && board.checkCell(x, y) == piece) {
            return true;
        }
        //continue checking the board in the same direction.
        return validMove(board, x + right, y + down, right, down, piece, iteration + 1);
    }

    public void flipTiles(char type, int x, int y, int right, int down, ReversiBoard board, Player player) {
        if(board.checkCell(x, y) == type || board.checkCell(x, y) == ' '
                || (x > board.getSize() || y > board.getSize() || x < 0 || y < 0)) {
            return;
        }
        if(type == 'x') {
            board.putTile(x, y, player);
        } else if(type == 'o') {
            board.putTile(x, y, player);
        }
        //flip all of the valid move tiles.
        flipTiles(type, x + right, y + down, right, down, board, player);
    }

    public int playerGrade(ReversiBoard board, Enum.type type) {
        int count = 0;
        // players symbol
        char symbol = ' ';
        // check players type
        if(type == Enum.type.blackPlayer) {
            symbol = 'x';
        } else if(type == Enum.type.whitePlayer) {
            symbol = 'o';
        }
        // go over board and count players pieces
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.checkCell(i, j) == symbol) {
                    count++;
                }
            }
        }
        return count;
    }
}