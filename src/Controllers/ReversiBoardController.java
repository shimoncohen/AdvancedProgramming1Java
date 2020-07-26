package Controllers;

import StyleAndMain.Main;
import StyleAndMain.ReversiBoard;
import General.*;
import General.Enum;
import Interfaces.GameLogic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
/**
 * Organizes all the controllers in the board.
 */
public class ReversiBoardController implements Initializable {
    // all objects used in game window
    public Label whiteScoreLabel;
    public Label blackScoreLabel;
    public Button start;
    public Button end;
    public Label winnerLabel;
    public Label winnerLabel2;
    public Label playingPlayer;
    public Button closeButton;
    public Label firstStartLabel;
    public Label secondStartLabel;
    @FXML
    private HBox root;

    // the classes objects
    @FXML
    private ReversiBoard reversiBoard;
    private Player first;
    private Player second;
    private Player current;
    private GameLogic logic;
    private boolean endBool;

    // load from file settings
    private Color firstColor;
    private Color secondColor;

    // defaults
    private final int PREFSIZECHANGE = 120;

    /**
     *  initializing the default values of the controller.
     * @param location the url.
     * @param resources the rescourses.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.endBool = false;
        ArrayList<String> info = SettingsWindow.loadSettings();
        // initializing the first player and second player color.
        this.firstColor = Color.valueOf(info.get(1));
        this.secondColor = Color.valueOf(info.get(2));
        // initializing the starter player.
        if(info.get(0).compareTo("BlackPlayer") == 0) {
            this.first = new Player(Enum.type.blackPlayer, this.firstColor);
            this.second = new Player(Enum.type.whitePlayer, this.secondColor);
        } else {
            this.first = new Player(Enum.type.whitePlayer, this.secondColor);
            this.second = new Player(Enum.type.blackPlayer, this.firstColor);
        }
        this.current = first;
        this.logic = new StandardGameLogic();
        // initializing the board.
        this.reversiBoard = new ReversiBoard(Integer.parseInt(info.get(3)), this.firstColor, this.secondColor);
        this.reversiBoard.onMouseClickedProperty().setValue(e -> {
            // initializing the counting of players' scores.
            this.first.setScore(this.logic.playerGrade(this.reversiBoard, Enum.type.blackPlayer));
            this.second.setScore(this.logic.playerGrade(this.reversiBoard, Enum.type.whitePlayer));
            this.blackScoreLabel.setText(this.first.getScore().toString());
            this.whiteScoreLabel.setText(this.second.getScore().toString());
            if(this.endBool) {
                endGame();
            }
        });
        ReversiPiece[][] tempBoard = this.reversiBoard.getBoard();
        // go over the board cells
        for(int i = 0; i < tempBoard.length; i++) {
            for (int j = 0; j < tempBoard[i].length; j++) {
                // set event for when a cell on the board is clicked
                this.reversiBoard.getBoard()[i][j].onMouseClickedProperty().setValue(e -> {
                    ReversiPiece temp = (ReversiPiece) e.getSource();
                    ArrayList<Point> availableMovesInner = this.logic.availableMoves(
                            this.reversiBoard, this.current.getType());
                    if(availableMovesInner.size() != 0) {
                        if (this.logic.validOption(this.reversiBoard, temp.getRow() + 1,
                                temp.getCol() + 1, availableMovesInner)) {
                            // changing the tiles after the click.
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
                            // change the current player
                            swapTurn();
                        }
                    }
                    // show the new available moves
                    showAvailableMoves();
                    // check if the other player has no moves
                    checkForNoMoves();
                });
            }
        }
        resizeWindowEvents();
        // make board disabled untill start button is pressed
        this.reversiBoard.setDisable(true);
    }

    /*
     * function name: swapTrun.
     * input: none.
     * output: none.
     * operation: switches the turns between the players.
     */
    private void swapTurn() {
        if (this.playingPlayer.getText().compareTo("First player") == 0) {
            this.playingPlayer.setText("Second player");
            this.current = this.second;
        } else if (playingPlayer.getText().compareTo("Second player") == 0) {
            this.playingPlayer.setText("First player");
            this.current = this.first;
        }
    }

    /*
     * function name: checkForNoMoves.
     * input: none.
     * output: none.
     * operation: checks if there are any available moves for the current player.
     * if there are no possible moves, a message is shown.
     * if both players have no possible moves, the game ends.
     */
    private void checkForNoMoves() {
        // if the current player has no moves
        ArrayList<Point> availableMoves = this.logic.availableMoves(this.reversiBoard, this.current.getType());
        if(availableMoves.size() == 0) {
            // change the current player
            swapTurn();
            // check if the next player has no moves left
            availableMoves = this.logic.availableMoves(this.reversiBoard, this.current.getType());
            if(availableMoves.size() == 0) {
                // if true then end the game
                this.endBool = true;
                return;
            }
            // show noMoves message
            NoMoveAlert.popUp(this.root);
            //noMovesPrompt();
        }
    }

    /*
     * function name: decidePlayerColor.
     * input: none.
     * output: the color of the current player.
     * operation: returns the color of the current player.
     */
    private Color decidePlayerColor() {
        return this.current.getColor();
    }

    /*
     * function name: startGame.
     * input: none.
     * output: none.
     * operation: starts the game operation.
     */
    @FXML
    public void startGame() {
        this.firstStartLabel.setVisible(false);
        this.secondStartLabel.setVisible(false);
        this.reversiBoard.setDisable(false);
        showAvailableMoves();
    }

    /*
     * function name: endGame.
     * input: none.
     * output: none.
     * operation: disables all moves, shows a winning message and shows a close button.
     */
    @FXML
    public void endGame() {
        if(Integer.parseInt(this.whiteScoreLabel.getText()) > Integer.parseInt(this.blackScoreLabel.getText())) {
            EndGameAlert.popUp(this.root, "Second player Wins!");
        } else if(Integer.parseInt(this.whiteScoreLabel.getText()) < Integer.parseInt(this.blackScoreLabel.getText())) {
            EndGameAlert.popUp(this.root, "First player Wins!");
        } else {
            EndGameAlert.popUp(this.root, "Its a tie!");
        }
        closeGameWindow();
    }

    /**
     * setting the action of close window to be switching back to menu.
     */
    public void closeGameWindow() {
            Main.switchBackToMain();
    }

    /*
     * function name: resizeWindowEvents.
     * input: none.
     * output: none.
     * operation: reassures that the window resize doesn't go over the limited borders of
     * min and max window size.
     */
    private void resizeWindowEvents() {
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
    }

    /*
     * function name: resetPiecesStroke.
     * input: none.
     * output: none.
     * operation: .
     */
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

    /*
     * function name: showAvailableMoves.
     * input: none.
     * output: none.
     * operation: show the possible moves of the current player. Represented as yellow circles.
     */
    private void showAvailableMoves() {
        resetPiecesStroke();
        // getting a list of the possible moves.
        ArrayList<Point> options = this.logic.availableMoves(this.reversiBoard, current.getType());
        for(int i = 0; i < this.reversiBoard.getBoard().length; i++) {
            for (int j = 0; j < this.reversiBoard.getBoard()[i].length; j++) {
                for (Point option : options) {
                    // shows the possible move as a yellow circle.
                    if (option.getX() - 1 == i && option.getY() - 1 == j) {
                        this.reversiBoard.getBoard()[i][j].setStroke(Color.YELLOW);
                    }
                }
            }
        }
    }
}
