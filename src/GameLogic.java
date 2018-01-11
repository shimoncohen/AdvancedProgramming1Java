import java.util.ArrayList;

public interface GameLogic {
    /*
     * function name: validMove.
     * input: a board object, x and y values for a move, right and down to check the vector for valid move
     *        piece to know what pieces to look for, and iteration to know the amount of pieces turned.
     * output: 1 if valid move, else 0.
     * operation: checks a vector from a certain point for a valid move.
     */
    boolean validMove(ReversiBoard board, int x, int y, int right, int down, char piece, int iteration);
    /*
     * function name: flipTiles.
     * input: the players type, x and y values to know where to start flipping from, right and down to know the
     *        vectors direction, and the board.
     * output: none.
     * operation: flips all of the tiles in the vectors way that are valid moves.
     */
    void flipTiles(char type, int x, int y, int right, int down, ReversiBoard board, Player player);
    /*
     * function name: availableMoves.
     * input: type- the player type(black/white). board- the game board player - the player.
     * output: a vector of points indicating the available moves.
     * operation: searches the board for available moves for the current player.
     */
    ArrayList<Point> availableMoves(ReversiBoard board, Enum.type type1);
    /*
     * function name: validOption.
     * input: the players move.
     * output: true if optional move, false otherwise.
     * operation: checks if the move is an optional move and returns true if it is, false otherwise.
     */
    boolean validOption(ReversiBoard board, int x, int y, ArrayList<Point> options);
    /*
     * function name: changeTiles.
     * input: the player. x- the row. y- the column. board- the game board.
     * output: none.
     * operation: changes the tiles on the board according to the game logic.
     */
    void changeTiles(Player player, int x, int y, ReversiBoard board);
    /*
     * function name: gameWon.
     * input: none.
     * output: the playerType of the player who won, otherwise t for tie.
     * operation: returns the winning player's type, otherwise returns t for tie.
     */
    char gameWon(ReversiBoard board) ;
    /*
     * function name: isGameWon.
     * input: none.
     * output: true if board is full, false otherwise.
     * operation: returns true if board is full, false otherwise.
     */
    boolean isGameWon(ReversiBoard board);
    /*
     * function name: gameFinalMove.
     * input: the game board and the last move.
     * output: true if the game ends after given move, false otherwise.
     * operation: plays given move on board and returns true if the given move was the final move, false otherwise.
     */
    boolean gameFinalMove(ReversiBoard board, Enum.type pType, int x, int y);
    /*
     * function name: playerGrade.
     * input: the game board and the player type.
     * output: the given players number of pieces on the board.
     * operation: returns a given players score (number of pieces).
     */
    int playerGrade(ReversiBoard board, Enum.type type);
}
