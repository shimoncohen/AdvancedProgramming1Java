import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ReversiBoard extends GridPane {
    private ReversiPiece[][] board;
    private int size;
    private Color firstColor;
    private Color secondColor;

    public ReversiBoard(int size, Color firstColor, Color secondColor) {
        this.firstColor = firstColor;
        this.secondColor = secondColor;
        this.setStyle("-fx-border: 2px; -fx-border-color: black");
        this.setGridLinesVisible(true);
        this.setId("reversiBoard");
        this.getStylesheets().add(getClass().getResource("Reversi.css").toExternalForm());
        this.size = size;
        this.board = new ReversiPiece[size][size];
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiGame.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        for(int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = new ReversiPiece(this, i , j);
            }
        }

        this.board[size / 2 - 1][size / 2 - 1].setFill(secondColor);
        this.board[size / 2 - 1][size / 2].setFill(firstColor);
        this.board[size / 2][size / 2 - 1].setFill(firstColor);
        this.board[size / 2][size / 2].setFill(secondColor);
        this.board[size / 2 - 1][size / 2 - 1].setType(Enum.type.whitePlayer);
        this.board[size / 2 - 1][size / 2].setType(Enum.type.blackPlayer);
        this.board[size / 2][size / 2 - 1].setType(Enum.type.blackPlayer);
        this.board[size / 2][size / 2].setType(Enum.type.whitePlayer);
    }

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

    public void draw() {
        this.getChildren().clear();
        int height = (int)this.getPrefHeight();
        int width = (int)this.getPrefWidth();
        this.setStyle("-fx-grid-lines-visible: true");
        int cellHeight = height / this.board.length;
        int cellWidth = width / this.board.length;

        for(int i = 0; i < this.board.length; i++) {
            for(int j = 0; j < this.board[i].length; j++) {
                this.board[i][j].draw(cellWidth, cellHeight);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public ReversiPiece[][] getBoard() {
        return board;
    }

    public void setBoard(ReversiPiece[][] board) {
        this.board = board;
    }
}