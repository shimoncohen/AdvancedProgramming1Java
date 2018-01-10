import com.sun.deploy.panel.ExceptionListDialog;
import com.sun.javafx.scene.layout.region.Margins;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReversiBoardController implements Initializable {
    public Label whiteScoreLabel;
    public Label blackScoreLabel;
    public Button start;
    public Label winnerLabel;
    public Label playingPlayer;
    @FXML
    private HBox root;
    private ReversiBoard reversiBoard;
    private Player first;
    private Player second;
    private Player current;
    private Player other;
    GameLogic logic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.first = new Player(Enum.type.blackPlayer);
        this.second = new Player(Enum.type.whitePlayer);
        this.current = first;
        this.other = second;
        this.playingPlayer.setText("First player");
        this.logic = new StandardGameLogic();
        reversiBoard = new ReversiBoard(4, this.logic);
        reversiBoard.setAlignment(Pos.CENTER);
        reversiBoard.setVisible(true);
        reversiBoard.setGridLinesVisible(true);
        reversiBoard.setPrefWidth(400);
        reversiBoard.setPrefHeight(400);
        reversiBoard.onMouseClickedProperty().setValue(e -> {
            this.first.setScore(this.logic.playerGrade(this.reversiBoard, Enum.type.blackPlayer));
            this.second.setScore(this.logic.playerGrade(this.reversiBoard, Enum.type.whitePlayer));
            blackScoreLabel.setText(this.first.getScore().toString());
            whiteScoreLabel.setText(this.second.getScore().toString());
            if(this.logic.isGameWon(this.reversiBoard)) {
                this.endGame();
                return;
            }
        });
        ReversiPiece[][] tempBoard = this.reversiBoard.getBoard();
        for(int i = 0; i < tempBoard.length; i++) {
            for (int j = 0; j < tempBoard[i].length; j++) {
                reversiBoard.getBoard()[i][j].onMouseClickedProperty().setValue(e -> {
                    ReversiPiece temp = (ReversiPiece) e.getSource();
                    ArrayList<Point> availableMovesInner = this.logic.availableMoves(reversiBoard, this.current.getType());
                    if(availableMovesInner.size() != 0) {
                        if (this.logic.validOption(reversiBoard, temp.getRow() + 1, temp.getCol() + 1, availableMovesInner)) {
                            this.logic.changeTiles(this.current.getType(), temp.getRow(), temp.getCol(), reversiBoard);
                            if (this.current.getType() == Enum.type.blackPlayer) {
                                ((Circle) e.getSource()).setFill(Color.BLACK);
                            } else if (this.current.getType() == Enum.type.whitePlayer) {
                                ((Circle) e.getSource()).setFill(Color.WHITE);
                            }
                            ((Circle) e.getSource()).setStroke(Color.BLACK);
                            if (temp.getType() == Enum.type.notDefined) {
                                temp.setType(this.current.getType());
                            }
                            if (playingPlayer.getText().compareTo("First player") == 0) {
                                this.playingPlayer.setText("Second player");
                                this.current = this.second;
                                this.other = this.first;
                            } else if (playingPlayer.getText().compareTo("Second player") == 0) {
                                this.playingPlayer.setText("First player");
                                this.current = this.first;
                                this.other = this.second;
                            }
                        }
                    }
                    availableMovesInner = this.logic.availableMoves(reversiBoard, this.current.getType());
                    if(availableMovesInner.size() == 0) {
                        if(this.current.getType() == Enum.type.blackPlayer) {
                            availableMovesInner = this.logic.availableMoves(this.reversiBoard,
                                    Enum.type.whitePlayer);
                            current = second;
                        } else if(this.current.getType() == Enum.type.whitePlayer) {
                            availableMovesInner = this.logic.availableMoves(this.reversiBoard,
                                    Enum.type.blackPlayer);
                            current = first;
                        }
                        if(availableMovesInner.size() == 0) {
                            endGame();
                            return;
                        }
                        Stage noMovesWindow = new Stage();
                        noMovesWindow.setTitle("No moves!");
                        Label noMovesLabel = new Label("No moves! turn goes to other player.");
                        noMovesLabel.setId("noMove");
                        VBox box = new VBox();
                        box.getChildren().add(noMovesLabel);
                        Scene scene = new Scene(box, 250, 40);
//                        this.reversiBoard.setDisable(true);
                        noMovesWindow.setScene(scene);
                        noMovesWindow.showAndWait();
                    }
                    showAvailableMoves();
                });
            }
        }
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
        showAvailableMoves();
    }

    @FXML
    public void endGame() {
        start.setDisable(true);
        reversiBoard.setDisable(true);
        winnerLabel.setVisible(true);
        if(Integer.valueOf(whiteScoreLabel.getText()) > Integer.valueOf(blackScoreLabel.getText())) {
            winnerLabel.setText("White player Wins!");
        } else if(Integer.valueOf(whiteScoreLabel.getText()) < Integer.valueOf(blackScoreLabel.getText())) {
            winnerLabel.setText("Black player Wins!");
        } else {
            winnerLabel.setText("Its a tie!");
        }
    }

    private void resetPiecesStroke() {
        for (int i = 0; i < this.reversiBoard.getBoard().length; i++) {
            for (int j = 0; j < this.reversiBoard.getBoard()[i].length; j++) {
                if (this.reversiBoard.getBoard()[i][j].getType() != Enum.type.notDefined) {
                    reversiBoard.getBoard()[i][j].setStroke(Color.BLACK);
                } else {
                    reversiBoard.getBoard()[i][j].setStroke(Color.GREEN);
                }
            }
        }
    }

    private void showAvailableMoves() {
        resetPiecesStroke();
        ArrayList<Point> options = this.logic.availableMoves(this.reversiBoard, current.getType());
        for(int i = 0; i < this.reversiBoard.getBoard().length; i++) {
            for (int j = 0; j < this.reversiBoard.getBoard()[i].length; j++) {
                for (int k = 0; k < options.size(); k++) {
                    if (options.get(k).getX() - 1 == i && options.get(k).getY() - 1 == j) {
                        reversiBoard.getBoard()[i][j].setStroke(Color.YELLOW);
                    }
                }
            }
        }
    }
}
