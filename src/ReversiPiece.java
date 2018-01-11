import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class ReversiPiece extends Circle {
    private Enum.type type;
    private GridPane grid;
    private int row;
    private int col;

    public ReversiPiece(GridPane grid, int row, int col) {
        this.grid = grid;
        this.row = row;
        this.col = col;
        this.setFill(Paint.valueOf("#f4f4f4"));
        this.type = Enum.type.notDefined;
    }

    public void draw(int cellWidth, int cellHeight) {
        this.setRadius(cellHeight / 2 - 4);

        if(this.type == Enum.type.notDefined) {
            this.setFill(Color.GREEN);
        }
        if(this.getType() != Enum.type.notDefined) {
            this.setStroke(Color.BLACK);
        }
        this.grid.add(this, col, row);
        this.grid.getChildren().remove(this);
        this.grid.add(this, col, row);
    }

    public void setType(Enum.type type) {
        this.type = type;
    }

    public Enum.type getType() {
        return type;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}