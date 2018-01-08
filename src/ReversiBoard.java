import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
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
//                    Circle circle = new Circle(cellHeight/2,Paint.valueOf("BLACK"));
//                    this.add(circle, j, i);
//                } else {
//                    Circle circle = new Circle(cellHeight/2,Paint.valueOf("WHITE"));
//                    this.add(circle, j, i);
//                }
                if(board[i][j] == FREE) {
                    Circle circle = new Circle(cellHeight/2, Paint.valueOf("#f4f4f4"));
                    this.add(circle, j, i);
                    //this.add(new Rectangle(cellWidth, cellHeight, Color.WHITE), j, i);
                } else if(board[i][j] == BLACKPLAYER) {
                    Circle circle = new Circle(cellHeight/2, Color.BLACK);
                    this.add(circle, j, i);
                    //this.add(new Rectangle(cellWidth, cellHeight, Color.BLACK), j, i);
                } else {
                    Circle circle = new Circle(cellHeight/2, Color.WHITE);
                    this.add(circle, j, i);
                    //this.add(new Rectangle(cellWidth, cellHeight, Color.GRAY), j, i);
                }
            }
        }
    }
}