// 315383133 shimon cohen
// 302228275 Nadav Spitzer

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * a class of the game tile (as a circle).
 * holds information about location and type.
 */
public class ReversiPiece extends Circle {
    private Enum.type type;
    private GridPane grid;
    private StackPane stackPane;
    private Rectangle rectangle;
    private int row;
    private int col;

    /**
     * constructor
     * @param grid
     * @param row the row of the piece on the board.
     * @param col the column of the piece on the board.
     */
    public ReversiPiece(GridPane grid, int row, int col) {
        // TODO is grid really necessary?
        this.grid = grid;
        // initialize the peice location.
        this.row = row;
        this.col = col;
        // initialize the piece color.
        this.setFill(Paint.valueOf("#f4f4f4"));
        // initialize the piece type.
        this.type = Enum.type.notDefined;
        this.stackPane = new StackPane();
        this.rectangle = new Rectangle();
        // set the background
        this.rectangle.setStroke(Color.BLACK);
        this.rectangle.setFill(Color.GREEN);
        this.setFill(Color.GREEN);
    }

    /***
     * drawing the tile on the board.
     * @param cellWidth the width of the cell.
     * @param cellHeight the height of the cell.
     */
    public void draw(int cellWidth, int cellHeight) {
        int minVal = Math.min(cellHeight, cellWidth);
        // setting the radius of the tile.
        this.setRadius(minVal / 2 - 2);
        this.rectangle.setWidth(cellWidth - 1);
        this.rectangle.setHeight(cellHeight - 1);

        if(this.getType() != Enum.type.notDefined) {
            this.setStroke(Color.BLACK);
        }
        this.stackPane.getChildren().removeAll(this.rectangle, this);
        this.stackPane.getChildren().addAll(this.rectangle, this);
        this.grid.getChildren().remove(this.stackPane);
        this.grid.add(this.stackPane, col, row);
//        this.grid.add(this, col, row);
    }

    /**
     * sets the type of the current tile.
     * @param type the new type of the tile.
     */
    public void setType(Enum.type type) {
        this.type = type;
    }

    /**
     * returns the type of the current tile.
     * @return the type of the tile.
     */
    public Enum.type getType() {
        return this.type;
    }

    /**
     * returns the row of the current tile.
     * @return the row of the tile.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * returns the column of the current tile.
     * @return the column of the tile.
     */
    public int getCol() {
        return this.col;
    }
}