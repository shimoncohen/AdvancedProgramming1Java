// 315383133 shimon cohen
// 302228275 Nadav Spitzer
package General;

import Controllers.SettingsWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private final int MINBUTTONSIZE = 100;
    private final int SETMINWIDTH = 500;
    private final int SETMINHEIGHT = 400;
    private final int SCENEMINHEIGHT = 300;
    private final int SCENEMINWIDTH = 300;
    private final int MAINWINDOWMINWIDTH = 140;
    private final int MAINWINDOWMINHEIGHT = 200;
    private final int VBOXSPACING = 20;
    // the 3 stages of the game - main, settings and game.
    private static Stage mainWindow;
    private static Scene layoutDisplay;
    private static Stage gameWindow;
    // the buttons of the main window
    private Button startGameButton;
    private Button settingsButton;
    private Button closeButton;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // defining the main window.
        mainWindow = primaryStage;
        mainWindow.setTitle("Reversi");

        VBox layoutMain = new VBox(VBOXSPACING);
        layoutDisplay = new Scene(layoutMain, SCENEMINWIDTH, SCENEMINHEIGHT);

        // set height and width of window
        mainWindow.setMinWidth(MAINWINDOWMINWIDTH);
        mainWindow.setMinHeight(MAINWINDOWMINHEIGHT);

        // define the main windows buttons
        this.startGameButton = new Button("Start game!");
        // set action of the startGame button.
        this.startGameButton.setOnAction(e -> {
            try {
                gameWindow = new Stage();
                // loading preferences from fxml file.
                HBox root = FXMLLoader.load(getClass().getResource("../Style/ReversiGame.fxml"));
                Scene scene = new Scene(root, SETMINWIDTH, SETMINHEIGHT);
                scene.getStylesheets().add(getClass().getResource("../Style/Reversi.css").toExternalForm());
                // setting the game window
                gameWindow.setMinWidth(SETMINWIDTH);
                gameWindow.setMinHeight(SETMINHEIGHT);
                gameWindow.setTitle("Reversi");
                gameWindow.setScene(scene);
                gameWindow.show();
                primaryStage.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        // setting the settings button
        SettingsWindow settingsWindow = new SettingsWindow();
        this.startGameButton.setMinWidth(MINBUTTONSIZE);
        this.settingsButton = new Button("Settings");
        // setting the action of the settings button.
        this.settingsButton.setOnAction(e -> settingsWindow.display());
        this.settingsButton.setMinWidth(MINBUTTONSIZE);
        // setting the close button
        this.closeButton = new Button("Close");
        this.closeButton.setMinWidth(MINBUTTONSIZE);
        // setting the action of the close button.
        this.closeButton.setOnMouseClicked(e -> {
            mainWindow.close();
        });

        layoutMain.setAlignment(Pos.CENTER);
        layoutMain.getChildren().addAll(this.startGameButton, this.settingsButton, this.closeButton);

        // set all of the main windows properties
        primaryStage.setTitle("Reversi");
        primaryStage.setScene(layoutDisplay);
        // set window to center of screen
        primaryStage.centerOnScreen();

        // show window
        primaryStage.show();
    }

    /*
     * function name: switchBacToMain.
     * input: none.
     * output: none.
     * operation: closes the game window and shows the main menu.
     */
    public static void switchBackToMain() {
        gameWindow.close();
        mainWindow.setScene(layoutDisplay);
        mainWindow.show();
    }
}