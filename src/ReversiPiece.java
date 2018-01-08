import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ReversiPiece {

    private GridPane grid;
    private int row;
    private int col;
    private int state;
    private ImageView iv;

    public ReversiPiece(GridPane grid, int row, int col) {
        this.grid = grid;
        this.row = row;
        this.col = col;
        state = 0;
        // Load the pieces image
        iv = new ImageView(getClass().getResource("minion.png").toExternalForm());
    }

    public void draw(int cellWidth, int cellHeight) {
        grid.add(iv, col, row);
        iv.setFitWidth(cellWidth);
        iv.setFitHeight(cellHeight);
        grid.getChildren().remove(iv);
        grid.add(iv, col, row);
    }

    public int getState() {
        return this.state;
    }

    public void setState(int newState) {
        this.state = newState;
    }
}
