package StyleAndMain;

import General.Enum;
import General.Player;
import General.ReversiPiece;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/***
 * the reversi board class. holds a matrix of reversi pieces and the size of the board.
 */
public class ReversiBoard extends GridPane {
    private ReversiPiece[][] board;
    private int size;

    /***
     * constructor.
     * @param size the size of the board.
     * @param firstColor the color of the first player.
     * @param secondColor the color of the second player.
     */
    public ReversiBoard(int size, Color firstColor, Color secondColor) {
        this.setGridLinesVisible(true);
        this.setId("reversiBoard");
        this.getStylesheets().add(getClass().getResource("../StyleAndMain/Reversi.css").toExternalForm());
        // setting the board size.
        this.size = size;
        // setting the board.
        this.board = new ReversiPiece[size][size];
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../StyleAndMain/ReversiGame.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        // adding the reversi pieces to the board.
        for(int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = new ReversiPiece(this, i , j);
            }
        }
        // initializing 4 pieces of start position.
        this.board[size / 2 - 1][size / 2 - 1].setFill(secondColor);
        this.board[size / 2 - 1][size / 2].setFill(firstColor);
        this.board[size / 2][size / 2 - 1].setFill(firstColor);
        this.board[size / 2][size / 2].setFill(secondColor);
        this.board[size / 2 - 1][size / 2 - 1].setType(Enum.type.whitePlayer);
        this.board[size / 2 - 1][size / 2].setType(Enum.type.blackPlayer);
        this.board[size / 2][size / 2 - 1].setType(Enum.type.blackPlayer);
        this.board[size / 2][size / 2].setType(Enum.type.whitePlayer);
    }

    /**
     *
     * @param x x coordinate of the board.
     * @param y y coordinate of the board.
     * @return returns a char that represents the type of the player.
     * ' ' for no piece, 'x' for black player and 'o' for white player.
     */
    public char checkCell(final int x, final int y) {
        //if the cell is out of the boards bounds.
        if (x < 0 || x >= this.size || y < 0 || y >= this.size) {
            return ' ';
        }
        // return the cells value.
        if (this.board[x][y].getType() == Enum.type.blackPlayer) {
            return 'x';
        } else if (this.board[x][y].getType() == Enum.type.whitePlayer) {
            return 'o';
        }
        return ' ';
    }

    /**
     * sets the tile type.
     * @param x the x coordinate of the tile.
     * @param y the y coordinate of the tile.
     * @param player the player's type.
     */
    public void putTile(final int x, final int y, final Player player) {
        // put a value in the cell according to type.
        if(player.getType() == Enum.type.blackPlayer) {
            this.board[x][y].setFill(Paint.valueOf(player.getColor().toString()));
            this.board[x][y].setType(Enum.type.blackPlayer);
        } else if(player.getType() == Enum.type.whitePlayer) {
            this.board[x][y].setFill(Paint.valueOf(player.getColor().toString()));
            this.board[x][y].setType(Enum.type.whitePlayer);
        } else {
            this.board[x][y].setFill(Color.GREEN);
            this.board[x][y].setType(Enum.type.notDefined);
        }
    }

    /*
     * function name: draw.
     * input: none.
     * output: none.
     * operation: draws the board, and the pieces.
     */
    public void draw() {
        this.getChildren().clear();
        int height = (int)this.getPrefHeight();
        int width = (int)this.getPrefWidth();
        this.setStyle("-fx-grid-lines-visible: true");
        int cellHeight = height / this.board.length;
        int cellWidth = width / this.board.length;

        for (ReversiPiece[] reversiPieces : this.board) {
            for (ReversiPiece reversiPiece : reversiPieces) {
                reversiPiece.draw(cellWidth, cellHeight);
            }
        }
    }

    /**
     * returns the size of the board.
     * @return the size of the board.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * returns the current board.
     * @return the current board.
     */
    public ReversiPiece[][] getBoard() {
        return this.board;
    }

    /**
     * sets the current board.
     * @param board the new board we want to set by.
     */
    public void setBoard(ReversiPiece[][] board) {
        this.board = board;
    }
}