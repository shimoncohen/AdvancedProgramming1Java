import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleRole;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ReversiBoardController implements Initializable {
    @FXML
    private HBox root;
    private ReversiPiece[][] board = new ReversiPiece[4][4];
    private ReversiBoard reversiBoard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reversiBoard = new ReversiBoard(board);
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
