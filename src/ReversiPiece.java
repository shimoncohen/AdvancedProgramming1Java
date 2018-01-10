import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class ReversiPiece extends Circle {
    private Enum.type type;
    private GridPane grid;
    private int row;
    private int col;
    private int state;
    //private ImageView iv;

    public ReversiPiece(GridPane grid, int row, int col) {
        this.grid = grid;
        this.row = row;
        this.col = col;
        //this.circle = new Circle();
        this.setFill(Paint.valueOf("#f4f4f4"));
        this.type = Enum.type.notDefined;
        // Load the pieces image
        //iv = new ImageView(getClass().getResource("minion.png").toExternalForm());
    }

    public void draw(int cellWidth, int cellHeight) {
//        grid.add(iv, col, row);
//        iv.setFitWidth(cellWidth);
//        iv.setFitHeight(cellHeight);
//        grid.getChildren().remove(iv);
//        grid.add(iv, col, row);
//        if(this.getFill() == Color.BLACK) {
//            this.state = 1;
//        } else if(this.getFill() == Paint.valueOf("#f4f4f4")) {
//            this.state = 0;
//        } else if(this.getFill() == Color.WHITE) {
//            this.state = 2;
//        }
//        this.setFill(Color.BLACK);
        this.setRadius(cellHeight / 2 - 4);
        if(this.type == Enum.type.blackPlayer) {
            this.setFill(Color.BLACK);
            this.setStroke(Color.BLACK);
        } else if(this.type == Enum.type.notDefined) {
//            this.setFill(Paint.valueOf("#f4f4f4"));
            this.setFill(Color.GREEN);
        } else if(this.type == Enum.type.whitePlayer) {
            this.setFill(Color.WHITE);
            this.setStroke(Color.BLACK);
        }
//        this.getChildren().add(this.circle);
        grid.add(this, col, row);
//        this.circle.setRadius(cellHeight/2 - 4);
        grid.getChildren().remove(this);
        grid.add(this, col, row);
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
