public class Game {
    private GameLogic gameLogic;
    private ReversiBoard board;
    private Player1 firstPlayer;
    private Player1 secondPlayer;

    // constructor
    Game(int boardSize, GameLogic newGameLogic, Player1 first, Player1 second) {
        this.gameLogic = newGameLogic;
        this.board = new ReversiBoard(boardSize, newGameLogic);
        this.firstPlayer = first;
        this.secondPlayer = second;
    }

    public static void run() {

    }
}
