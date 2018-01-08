import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ReversiBoard extends GridPane {
    private int[][] board;
    private static final int FREE = 0;
    private static final int BLACKPLAYER = 1;
    private static final int WHITEPLAYER = 2;
    private Player firstPlayer;
    private Player secondPlayer;

    public ReversiBoard(int[][] board) {
        this.board = board;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiGame.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
    }

    public void draw() {
        this.getChildren().clear();
        int height = (int)this.getPrefHeight();
        int width = (int)this.getPrefWidth();

        int cellHeight = height / board.length;
        int cellWidth = width / board.length;

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
//                if(i % 2 == 0 || j % 2 == 1) {
//                    this.add(new Rectangle(cellWidth, cellHeight, Color.WHITE), j, i);
//                } else {
//                    this.add(new Rectangle(cellWidth, cellHeight, Color.BLACK), j, i);
//                }
                if(board[i][j] == FREE) {
                    this.add(new Rectangle(cellWidth, cellHeight, Color.WHITE), j, i);
                } else if(board[i][j] == BLACKPLAYER) {
                    this.add(new Rectangle(cellWidth, cellHeight, Color.BLACK), j, i);
                } else {
                    this.add(new Rectangle(cellWidth, cellHeight, Color.GRAY), j, i);
                }
            }
        }
    }
}