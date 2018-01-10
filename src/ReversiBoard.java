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
import java.util.ArrayList;

public class ReversiBoard extends GridPane {
    private ReversiPiece[][] board;
//    private Board behindBoard;
    private int size;
    private GameLogic logic;
//    private static final int FREE = 0;
//    private static final int BLACKPLAYER = 1;
//    private static final int WHITEPLAYER = 2;

    public ReversiBoard(int size, GameLogic gameLogic) {
        this.size = size;
        this.logic = gameLogic;
        this.board = new ReversiPiece[size][size];
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiGame.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        for(int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = new ReversiPiece(this, i , j);
//                this.setBorder();
            }
        }
        this.board[size / 2 - 1][size / 2 - 1].setType(Enum.type.whitePlayer);
        this.board[size / 2 - 1][size / 2].setType(Enum.type.blackPlayer);
        this.board[size / 2][size / 2 - 1].setType(Enum.type.blackPlayer);
        this.board[size / 2][size / 2].setType(Enum.type.whitePlayer);
    }

    public int getSize() {
        return size;
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

    public void putTile(final int x, final int y, final Enum.type type) {
        // put a value in the cell according to type.
        if(type == Enum.type.blackPlayer) {
            this.board[x][y].setFill(Color.BLACK);
            this.board[x][y].setType(Enum.type.blackPlayer);
        } else if(type == Enum.type.whitePlayer) {
            this.board[x][y].setFill(Color.WHITE);
            this.board[x][y].setType(Enum.type.whitePlayer);
        } else {
            this.board[x][y].setFill(Paint.valueOf("#f4f4f4"));
            this.board[x][y].setType(Enum.type.notDefined);
        }
    }

    public void setBoard(ReversiPiece[][] board) {
        this.board = board;
    }

    public void draw() {
        this.getChildren().clear();
        int height = (int)this.getPrefHeight();
        int width = (int)this.getPrefWidth();
        this.setStyle("-fx-background-color: burlywood; -fx-grid-lines-visible: true");
        int cellHeight = height / this.board.length;
        int cellWidth = width / this.board.length;

        for(int i = 0; i < this.board.length; i++) {
            for(int j = 0; j < this.board[i].length; j++) {
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
                this.board[i][j].draw(cellWidth, cellHeight);
//                this.getChildren().add(board[i][j]);
//                board[i][j].onMouseClickedProperty().setValue(e -> {
//                    //this.board[1][1].draw(cellWidth,cellHeight);
//                    //circle.setFill(Color.BLACK);
//                });
            }
        }
    }

    public ReversiPiece[][] getBoard() {
        return board;
    }
}