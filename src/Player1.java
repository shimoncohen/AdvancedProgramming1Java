import java.util.ArrayList;
public interface Player1 {
    /*
     * function name: assignType.
     * input: a type to assign as player type.
     * output: none.
     * operation: assigns the player the given type.
     */
    void assignType(Enum.type playerType1);
    /*
     * function name:getType.
     * input: none.
     * output: the players type.
     * operation: returns the players type.
     */
    Enum.type getType();
    /*
     * function name:makeMove.
     * input: none.
     * output: an array of ints.
     * operation: the player chooses a point on the board to put a piece on (from the available moves he has).
     */
    int[] makeMove(GameLogic gameLogic, Board board, ArrayList<Point> moves);
    /*
     * function name: recieveOpponentsMove.
     * input: the players move.
     * output: none.
     * operation: gives the opponents move to the player.
     */
    void recieveOpponentsMove(int x, int y);
    /*
     * function name: needPrint.
     * input: none.
     * output: bool - true or false.
     * operation: true if the player needs to print the possible moves (in AIPlayer and local),
     * otherwise returns false.
     */
    boolean needPrint();
}
