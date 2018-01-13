import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class ReversiPiece extends Circle {
    private Enum.type type;
    private GridPane grid;
    private StackPane stackPane;
    private Rectangle rectangle;
    private int row;
    private int col;

    public ReversiPiece(GridPane grid, int row, int col) {
        this.grid = grid;
        this.row = row;
        this.col = col;
        this.setFill(Paint.valueOf("#f4f4f4"));
        this.type = Enum.type.notDefined;
        this.stackPane = new StackPane();
        this.rectangle = new Rectangle();
        this.rectangle.setStroke(Color.BLACK);
        this.rectangle.setFill(Color.GREEN);
        this.setFill(Color.GREEN);
    }

    public void draw(int cellWidth, int cellHeight) {
        int minVal = Math.min(cellHeight, cellWidth);
        this.setRadius(minVal / 2 - 9);
        this.rectangle.setWidth(cellWidth - 17);
        this.rectangle.setHeight(cellHeight - 15);

        if(this.getType() != Enum.type.notDefined) {
            this.setStroke(Color.BLACK);
        }
        this.stackPane.getChildren().removeAll(this.rectangle, this);
        this.stackPane.getChildren().addAll(this.rectangle, this);
        this.grid.getChildren().remove(this.stackPane);
        this.grid.add(this.stackPane, col, row);
//        this.grid.add(this, col, row);
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