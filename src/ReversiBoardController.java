import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ReversiBoardController implements Initializable {
    @FXML
    private HBox root;
    //private ReversiPiece[][] board = new ReversiPiece[4][4];
    private ReversiBoard reversiBoard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reversiBoard = new ReversiBoard(4, new StandardGameLogic());
        reversiBoard.setAlignment(Pos.CENTER);
//        for(int i = 0; i < this.reversiBoard.getSize(); i++ ) {
//            ColumnConstraints columnConstraints = new ColumnConstraints(30);
//            this.reversiBoard.getColumnConstraints().add(columnConstraints);
//            RowConstraints rowConstraints = new RowConstraints(30);
//            this.reversiBoard.getRowConstraints().add(rowConstraints);
//        }
//        reversiBoard.setStyle("-fx-grid-lines-visible: true");
//        reversiBoard.setBoard(board);
        reversiBoard.setVisible(true);
        reversiBoard.setGridLinesVisible(true);
        reversiBoard.setPrefWidth(400);
        reversiBoard.setPrefHeight(400);
        root.getChildren().add(0, reversiBoard);
        root.widthProperty().addListener((observable, oldVal, newVal) -> {
            double boardNewWidth = newVal.doubleValue() - 120;
            reversiBoard.setPrefWidth(boardNewWidth);
            reversiBoard.draw();
        });
        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            reversiBoard.setPrefHeight(newValue.doubleValue());
            reversiBoard.draw();
        });
        reversiBoard.setDisable(true);
    }

    @FXML
    public void startGame() {
        reversiBoard.setDisable(false);
    }

    @FXML
    public void endGame() {
        reversiBoard.setDisable(true);
    }
}
