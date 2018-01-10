import com.sun.javafx.scene.layout.region.Margins;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.awt.*;
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
    //private ReversiPiece[][] board = new ReversiPiece[4][4];
    private ReversiBoard reversiBoard;
    private Player first;
    private Player second;
    private Player current;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.first = new Player(Enum.type.blackPlayer);
        this.second = new Player(Enum.type.whitePlayer);
        this.current = first;
        this.playingPlayer.setText("First player");
        StandardGameLogic standardGameLogic = new StandardGameLogic();
        reversiBoard = new ReversiBoard(4, standardGameLogic);
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
        reversiBoard.onMouseClickedProperty().setValue(e -> {
            Integer blackScore;
            Integer whiteScore;
            blackScore = standardGameLogic.playerGrade(this.reversiBoard, Enum.type.whitePlayer);
            whiteScore = standardGameLogic.playerGrade(this.reversiBoard, Enum.type.blackPlayer);
            blackScoreLabel.setText(blackScore.toString());
            whiteScoreLabel.setText(whiteScore.toString());
            if(standardGameLogic.isGameWon(this.reversiBoard)) {
                this.endGame();
                return;
            }
            resetPiecesStroke();
            ArrayList<Point> options = standardGameLogic.availableMoves(this.reversiBoard, current.getType());
            for(int i = 0; i < this.reversiBoard.getBoard().length; i++) {
                for (int j = 0; j < this.reversiBoard.getBoard()[i].length; j++) {
                    for (int k = 0; k < options.size(); k++) {
                        if (options.get(k).getX() - 1 == i && options.get(k).getY() - 1 == j) {
                            reversiBoard.getBoard()[i][j].setStroke(Color.YELLOW);
                        }
                    }
                }
            }
        });
        ReversiPiece[][] tempBoard = this.reversiBoard.getBoard();
        for(int i = 0; i < tempBoard.length; i++) {
            for (int j = 0; j < tempBoard[i].length; j++) {
                reversiBoard.getBoard()[i][j].onMouseClickedProperty().setValue(e -> {
//                    ((ReversiPiece)this.getChildren().get(0)).setState(1);
//                    Object object = ((Circle)e.getSource()).getParent();
                    ReversiPiece temp = (ReversiPiece) e.getSource();
                    ArrayList<Point> availableMovesInner = standardGameLogic.availableMoves(reversiBoard, this.current.getType());
                    if(availableMovesInner.size() != 0) {
                        if (standardGameLogic.validOption(reversiBoard, temp.getRow() + 1, temp.getCol() + 1, availableMovesInner)) {
                            //if (temp.getType() == Enum.type.notDefined) {
                            standardGameLogic.changeTiles(this.current.getType(), temp.getRow(), temp.getCol(), reversiBoard);
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
                            } else if (playingPlayer.getText().compareTo("Second player") == 0) {
                                this.playingPlayer.setText("First player");
                                this.current = this.first;
                            }
                            //}
                        }
                    } else {
                        if(this.current.getType() == Enum.type.blackPlayer) {
                            availableMovesInner = standardGameLogic.availableMoves(this.reversiBoard,
                                    Enum.type.blackPlayer);
                            current = second;
                        } else if(this.current.getType() == Enum.type.whitePlayer) {
                            availableMovesInner = standardGameLogic.availableMoves(this.reversiBoard,
                                    Enum.type.whitePlayer);
                            current = first;
                        }
                        if(availableMovesInner.size() == 0) {
                            endGame();
                            return;
                        }
                    }
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
                    reversiBoard.getBoard()[i][j].setStroke(Color.BURLYWOOD);
                }
            }
        }
    }
}
