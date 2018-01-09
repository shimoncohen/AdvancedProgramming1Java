import java.util.ArrayList;

public class Game1 {
    //members
    final int NOMOVES = -1, END = -2, CLOSE = -3;
    GameLogic gameLogic;
    Board board;
    Player1 firstPlayer;
    Player1 secondPlayer;

    // constructor
    Game1(int boardSize, GameLogic newGameLogic, Player1 first, Player1 second) {
        this.gameLogic = newGameLogic;
        this.board = new Board(boardSize);
        this.firstPlayer = first;
        this.secondPlayer = second;
    }

    Game1(GameLogic newGameLogic, Player1 first, Player1 second) {
        this.gameLogic = newGameLogic;
        this.board = new Board();
        this.firstPlayer = first;
        this.secondPlayer = second;
    }

    public void assignTypes() {
        Player1 tempPlayer;
        // if the second player is not defined we will assign the first to black and the second to white
        if(this.secondPlayer.getType() == Enum.type.notDefined) {
            this.firstPlayer.assignType(Enum.type.blackPlayer);
            this.secondPlayer.assignType(Enum.type.whitePlayer);
            // if the second is assigned as black we will assign the second as black and the first as white
        } else if(this.secondPlayer.getType() == Enum.type.blackPlayer) {
            this.firstPlayer.assignType(Enum.type.whitePlayer);
            tempPlayer = this.firstPlayer;
            this.firstPlayer = this.secondPlayer;
            this.secondPlayer = tempPlayer;
            // if the second is assigned as white we will assign the first to black
        } else if(this.secondPlayer.getType() == Enum.type.whitePlayer) {
            this.firstPlayer.assignType(Enum.type.blackPlayer);
        }
    }

    public void runGame() {
        ArrayList<Point> options = new ArrayList<Point>();
        Printer printer = new ConsolePrinter();
        // represents the winner player
        char winner;
        assignTypes();
        //starts the game
        try {
            doOneTurn(options);
        } catch (Exception msg) {
            throw msg;
        }
        winner = this.gameLogic.gameWon(this.board);
        //printing as wiining message
        printer.printWinMessage(winner);
    }

    public void doOneTurn(ArrayList<Point> options) {
        Player1 current = this.firstPlayer, waitingPlayer = this.secondPlayer, tempPlayer;
        Printer printer = new ConsolePrinter();
        Enum.type playerType;
        int x = -10, y = -10;
        int[] temp;
        boolean valid;
        boolean end = false;
        //runs the players turns until there is a winner.
        while (true) {
            String xTest, yTest;
            playerType = current.getType();
            // in case of server game writes the opponent's move into the socket
            try {
                current.recieveOpponentsMove(x, y);
            } catch (Exception msg) {
                throw msg;
            }
            // in case the game ends
            if (end) {
                if (x == CLOSE && y == CLOSE) {
                    waitingPlayer.recieveOpponentsMove(CLOSE, CLOSE);
                    throw new RuntimeException("Game closed");
                } else {
                    waitingPlayer.recieveOpponentsMove(END, END);
                }
                break;
            }
            // returns the vector of the player's available moves
            options = gameLogic.availableMoves(this.board, playerType);
            //if the current player has no available moves.
            if (options.size() == 0) {
                printer.printNoMoves(playerType);
                //in case of no mor moves printing a message and switch between the players.
                tempPlayer = current;
                current = waitingPlayer;
                waitingPlayer = tempPlayer;
                continue;
            }
            // printing the board and which player is going to play.
            printer.printBoard(this.board);
            printer.printTurn(playerType);
            if (current.needPrint()) {
                //print all move options.
                printer.printPossibleMoves(options);
                // print a request for a move fro the player.
                printer.requestMove();
            }
            //let the player make a move.
            while (true) {
                Board copyBoard = new Board(this.board);
                try {
                    temp = current.makeMove(this.gameLogic, copyBoard, options);
                } catch (Exception msg) {
                    throw msg;
                }
                if (temp[0] == END && temp[1] == END) {
                    end = true;
                    x = -2;
                    y = -2;
                    break;
                } else if (temp[0] == CLOSE && temp[1] == CLOSE) {
                    end = true;
                    x = -3;
                    y = -3;
                    break;
                }
                x = temp[0] + 1;
                y = temp[1] + 1;
                //check if move is in board boundaries.
                valid = this.gameLogic.validOption(this.board, x, y, options);
                if (valid) {
                    break;
                } else {
                    printer.printInvalidMove();
                    continue;
                }
            }
            if (end) {
                continue;
            }
            x -= 1;
            y -= 1;
            printer.printMove(playerType, x + 1, y + 1);
            //flips the correct tiles according to the player and the players move.
            gameLogic.changeTiles(playerType, x, y, this.board);
            // check if the current move ends the game
            if (gameLogic.gameFinalMove(this.board, playerType, x, y)) {
                waitingPlayer.recieveOpponentsMove(x, y);
                end = true;
            }
            // switching between the players
            tempPlayer = current;
            current = waitingPlayer;
            waitingPlayer = tempPlayer;
        }
        printer.printBoard(this.board);
    }
}
