import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.*;

import java.awt.*;

public class ReversiBoard extends GridPane {
    private ReversiPiece[][] board;
    private static final int FREE = 0;
    private static final int BLACKPLAYER = 1;
    private static final int WHITEPLAYER = 2;
    private Player firstPlayer;
    private Player secondPlayer;

    public ReversiBoard(ReversiPiece[][] board1) {
        this.board = board1;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiGame.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new ReversiPiece(this, i , j);
            }
        }

//        int height = (int)this.getPrefHeight();
//        int width = (int)this.getPrefWidth();
//
//        int cellHeight = height / board.length;
//        int cellWidth = width / board.length;
//
//        for(int i = 0; i < board.length; i++) {
//            for(int j = 0; j < board[i].length; j++) {
////                if(i % 2 == 0 || j % 2 == 1) {
////                    Circle circle = new Circle(cellHeight/2,Paint.valueOf("BLACK"));
////                    this.add(circle, j, i);
////                } else {
////                    Circle circle = new Circle(cellHeight/2,Paint.valueOf("WHITE"));
////                    this.add(circle, j, i);
////                }
//                //board[i][j].draw(cellWidth,cellHeight);
//                HBox box = new HBox();
//                box.maxWidth(cellWidth);
//                box.setMinWidth(cellWidth);
//                box.maxHeight(cellHeight);
//                box.setMinHeight(cellHeight);
//                box.setAlignment(Pos.CENTER);
//                Circle circle;
//                if(board[i][j].getState() == FREE) {
//                    board[i][j].setCircle(Color.valueOf("#f4f4f4"));
//                    circle = new Circle(cellHeight/2 - 4, Paint.valueOf("#f4f4f4"));
//                    //this.add(new Rectangle(cellWidth, cellHeight, Color.WHITE), j, i);
//                } else if(board[i][j].getState() == BLACKPLAYER) {
//                    board[i][j].setCircle(Color.BLACK);
//                    circle = new Circle(cellHeight/2 - 4, Color.BLACK);
//                    //this.add(new Rectangle(cellWidth, cellHeight, Color.BLACK), j, i);
//                } else {
//                    board[i][j].setCircle(Color.WHITE);
//                    circle = new Circle(cellHeight/2 - 4, Color.WHITE);
//                    //this.add(new Rectangle(cellWidth, cellHeight, Color.GRAY), j, i);
//                }
//                //box.getChildren().add(board[i][j].getCircle());
//                box.getChildren().add(circle);
//                this.add(box, j, i);
////                board[i][j].onMouseClickedProperty().setValue(e -> {
////                    //this.board[1][1].draw(cellWidth,cellHeight);
////                    //circle.setFill(Color.BLACK);
////                });
//                box.onMouseClickedProperty().setValue(e -> {
//                    //((ReversiPiece)box.getChildren().get(0)).draw(cellWidth, cellHeight);
//                    circle.setFill(Color.BLACK);
//                });
//            }
//        }
    }

    public void setBoard(ReversiPiece[][] board) {
        this.board = board;
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
//                board[i][j].draw(cellWidth,cellHeight);
//                HBox box = new HBox();
//                box.maxWidth(cellWidth);
//                box.setMinWidth(cellWidth);
//                box.maxHeight(cellHeight);
//                box.setMinHeight(cellHeight);
//                box.setAlignment(Pos.CENTER);
//                Circle circle;
//                if(board[i][j].getState() == FREE) {
////                    board[i][j].setCircle(Color.valueOf("#f4f4f4"));
////                    circle = new Circle(cellHeight/2 - 4, Paint.valueOf("#f4f4f4"));
////                    board[i][j].setCircle(circle);
//                    //this.add(new Rectangle(cellWidth, cellHeight, Color.WHITE), j, i);
//                } else if(board[i][j].getState() == BLACKPLAYER) {
////                    circle = new Circle(cellHeight/2 - 4, Color.BLACK);
////                    board[i][j].setCircle(circle);
//                    //this.add(new Rectangle(cellWidth, cellHeight, Color.BLACK), j, i);
//                } else {
////                    board[i][j].setCircle(Color.WHITE);
////                    circle = new Circle(cellHeight/2 - 4, Color.WHITE);
////                    board[i][j].setCircle(circle);
//                    //this.add(new Rectangle(cellWidth, cellHeight, Color.GRAY), j, i);
//                }
//                box.getChildren().add(board[i][j].getCircle());
//                box.getChildren().add(circle);
//                this.add(box, j, i);
                board[i][j].draw(cellWidth, cellHeight);
//                this.getChildren().add(board[i][j]);
//                board[i][j].onMouseClickedProperty().setValue(e -> {
//                    //this.board[1][1].draw(cellWidth,cellHeight);
//                    //circle.setFill(Color.BLACK);
//                });
                board[i][j].getCircle().onMouseClickedProperty().setValue(e -> {
//                    ((ReversiPiece)this.getChildren().get(0)).setState(1);
//                    Object object = ((Circle)e.getSource()).getParent();
                    ((Circle)e.getSource()).setFill(Color.BLACK);
//                    circle.setFill(Color.BLACK);
//                    circle.setFill(Color.BLACK);
                });
            }
        }
    }
}