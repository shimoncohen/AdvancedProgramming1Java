import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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
    public Button end;
    public Label winnerLabel;
    public Label playingPlayer;
    public Button closeButton;
    @FXML
    private HBox root;
    private ReversiBoard reversiBoard;
    private Player first;
    private Player second;
    private Player current;
    private GameLogic logic;

    // load from file settings
    private Color firstColor;
    private Color secondColor;

    private final int PREFSIZECHANGE = 120;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> info = SettingsWindow.loadSettings();
        this.firstColor = Color.valueOf(info.get(1));
        this.secondColor = Color.valueOf(info.get(2));
        if(info.get(0).compareTo("BlackPlayer") == 0) {
            this.first = new Player(Enum.type.blackPlayer, this.firstColor);
            this.second = new Player(Enum.type.whitePlayer, this.secondColor);
        } else {
            this.first = new Player(Enum.type.whitePlayer, this.secondColor);
            this.second = new Player(Enum.type.blackPlayer, this.firstColor);
        }
        this.current = first;
        this.playingPlayer.setText("First player");
        this.logic = new StandardGameLogic();
        this.reversiBoard = new ReversiBoard(Integer.valueOf(info.get(3)), this.firstColor, this.secondColor);
        this.reversiBoard.onMouseClickedProperty().setValue(e -> {
            this.first.setScore(this.logic.playerGrade(this.reversiBoard, Enum.type.blackPlayer));
            this.second.setScore(this.logic.playerGrade(this.reversiBoard, Enum.type.whitePlayer));
            this.blackScoreLabel.setText(this.first.getScore().toString());
            this.whiteScoreLabel.setText(this.second.getScore().toString());
            if(this.logic.isGameWon(this.reversiBoard)) {
                this.endGame();
                return;
            }
        });
        ReversiPiece[][] tempBoard = this.reversiBoard.getBoard();
        for(int i = 0; i < tempBoard.length; i++) {
            for (int j = 0; j < tempBoard[i].length; j++) {
                this.reversiBoard.getBoard()[i][j].onMouseClickedProperty().setValue(e -> {
                    ReversiPiece temp = (ReversiPiece) e.getSource();
                    ArrayList<Point> availableMovesInner = this.logic.availableMoves(
                            this.reversiBoard, this.current.getType());
                    if(availableMovesInner.size() != 0) {
                        if (this.logic.validOption(this.reversiBoard, temp.getRow() + 1,
                                temp.getCol() + 1, availableMovesInner)) {
                            this.logic.changeTiles(this.current, temp.getRow(), temp.getCol(), this.reversiBoard);
                            if (this.current.getType() == Enum.type.blackPlayer) {
                                ((Circle) e.getSource()).setFill(decidePlayerColor());
                            } else if (this.current.getType() == Enum.type.whitePlayer) {
                                ((Circle) e.getSource()).setFill(decidePlayerColor());
                            }
                            ((Circle) e.getSource()).setStroke(Color.BLACK);
                            if (temp.getType() == Enum.type.notDefined) {
                                temp.setType(this.current.getType());
                            }
                            if (this.playingPlayer.getText().compareTo("First player") == 0) {
                                this.playingPlayer.setText("Second player");
                                this.current = this.second;
                            } else if (playingPlayer.getText().compareTo("Second player") == 0) {
                                this.playingPlayer.setText("First player");
                                this.current = this.first;
                            }
                        }
                    }
                    availableMovesInner = this.logic.availableMoves(this.reversiBoard, this.current.getType());
                    if(availableMovesInner.size() == 0) {
                        if(this.current.getType() == Enum.type.blackPlayer) {
                            availableMovesInner = this.logic.availableMoves(this.reversiBoard,
                                    Enum.type.whitePlayer);
                            this.current = this.second;
                        } else if(this.current.getType() == Enum.type.whitePlayer) {
                            availableMovesInner = this.logic.availableMoves(this.reversiBoard,
                                    Enum.type.blackPlayer);
                            this.current = this.first;
                        }
                        if(availableMovesInner.size() == 0) {
                            endGame();
                            return;
                        }
                        // show noMoves message
                        noMovesPrompt();
                    }
                    //
                    showAvailableMoves();
                });
            }
        }
        this.root.getChildren().add(0, this.reversiBoard);
        this.root.widthProperty().addListener((observable, oldVal, newVal) -> {
            double boardNewWidth = newVal.doubleValue() - PREFSIZECHANGE;
            this.reversiBoard.setPrefWidth(boardNewWidth);
            this.reversiBoard.draw();
        });
        this.root.heightProperty().addListener((observable, oldValue, newValue) -> {
            this.reversiBoard.setPrefHeight(newValue.doubleValue());
            this.reversiBoard.draw();
        });
        this.reversiBoard.setDisable(true);
    }

    private Color decidePlayerColor() {
        return this.current.getColor();
    }

    @FXML
    public void startGame() {
        reversiBoard.setDisable(false);
        showAvailableMoves();
    }

    @FXML
    public void endGame() {
        this.start.setDisable(true);
        this.end.setDisable(true);
        this.closeButton.setVisible(true);
        this.reversiBoard.setDisable(true);
        this.winnerLabel.setVisible(true);
        if(Integer.valueOf(this.whiteScoreLabel.getText()) > Integer.valueOf(this.blackScoreLabel.getText())) {
            this.winnerLabel.setText("Second player Wins!");
        } else if(Integer.valueOf(this.whiteScoreLabel.getText()) < Integer.valueOf(this.blackScoreLabel.getText())) {
            this.winnerLabel.setText("First player Wins!");
        } else {
            this.winnerLabel.setText("Its a tie!");
        }
    }

    public void closeGameWindow(MouseEvent mouseEvent) {
        Main.switchBackToMain();
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
                        this.reversiBoard.getBoard()[i][j].setStroke(Color.YELLOW);
                    }
                }
            }
        }
    }

    private void noMovesPrompt() {
        Stage noMovesWindow = new Stage();
        noMovesWindow.setTitle("No moves!");
        noMovesWindow.centerOnScreen();
        Label noMovesLabel = new Label("No moves!\n turn goes to other player.");
        noMovesLabel.setId("noMove");
        VBox box = new VBox();
        box.getChildren().add(noMovesLabel);
        Scene scene = new Scene(box, 250, 40);
        root.setDisable(true);
        noMovesWindow.setScene(scene);
        noMovesWindow.showAndWait();
        root.setDisable(false);
    }
}
