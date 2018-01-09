import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class ReversiPiece extends HBox {

    private GridPane grid;
    private int row;
    private int col;
    private int state;
    private Circle circle;
    //private ImageView iv;

    public ReversiPiece(GridPane grid, int row, int col) {
        this.grid = grid;
        this.row = row;
        this.col = col;
        this.circle = new Circle();
        this.circle.setFill(Paint.valueOf("#f4f4f4"));
        state = 0;
        // Load the pieces image
        //iv = new ImageView(getClass().getResource("minion.png").toExternalForm());
    }

    public void draw(int cellWidth, int cellHeight) {
//        grid.add(iv, col, row);
//        iv.setFitWidth(cellWidth);
//        iv.setFitHeight(cellHeight);
//        grid.getChildren().remove(iv);
//        grid.add(iv, col, row);
        if(this.circle.getFill() == Color.BLACK) {
            this.state = 1;
        } else if(this.circle.getFill() == Paint.valueOf("#f4f4f4")) {
            this.state = 0;
        } else if(this.circle.getFill() == Color.WHITE) {
            this.state = 2;
        }
        Circle circle;
        circle = new Circle(cellHeight / 2 - 4, Color.BLACK);
        this.circle = circle;
        if(this.state == 1) {
            circle.setFill(Color.BLACK);
        } else if(this.state == 0) {
            circle.setFill(Paint.valueOf("#f4f4f4"));
        } else if(this.state == 2) {
            circle.setFill(Color.WHITE);
        }
//        this.getChildren().add(this.circle);
        grid.add(circle, col, row);
//        this.circle.setRadius(cellHeight/2 - 4);
        grid.getChildren().remove(circle);
        grid.add(circle, col, row);
    }

    public int getState() {
        return this.state;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setState(int newState) {
        this.state = newState;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }
}
