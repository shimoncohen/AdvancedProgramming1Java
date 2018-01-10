import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import jdk.nashorn.internal.objects.annotations.Property;

import java.awt.*;

public class Main extends Application {

    final int MINBUTTONSIZE = 100;

    Stage mainWindow;
    Scene layoutDisplay;
    Button startGameButton;
    Button settingsButton;
    Button closeButton;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainWindow = primaryStage;
        mainWindow.setTitle("Reversi");

        VBox layoutMain = new VBox(20);
        layoutDisplay = new Scene(layoutMain, 300, 300);

        // set height and width of window
        mainWindow.setMinWidth(140);
        mainWindow.setMinHeight(200);

        // define the main windows buttons
        startGameButton = new Button("Start game!");
        startGameButton.setOnAction(e -> {
            try {
                Stage gameWindow = new Stage();
                HBox root = FXMLLoader.load(getClass().getResource("ReversiGame.fxml"));
                Scene scene = new Scene(root, 500, 400);
                scene.getStylesheets().add(getClass().getResource("Reversi.css").toExternalForm());
                gameWindow.setMinWidth(500);
                gameWindow.setMinHeight(400);
                gameWindow.setTitle("Reversi");
                gameWindow.setScene(scene);
                gameWindow.show();
                primaryStage.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        startGameButton.setMinWidth(MINBUTTONSIZE);
        settingsButton = new Button("Settings");
        settingsButton.setOnAction(e -> SettingsWindow.display());
        settingsButton.setMinWidth(MINBUTTONSIZE);
        closeButton = new Button("Close");
        closeButton.setMinWidth(100);
        closeButton.setOnMouseClicked(e -> {
            mainWindow.close();
        });

        layoutMain.setAlignment(Pos.CENTER);
        layoutMain.getChildren().addAll(startGameButton, settingsButton, closeButton);

        // set all of the main windows properties
        primaryStage.setTitle("Reversi");
        primaryStage.setScene(layoutDisplay);
//        primaryStage.setResizable(true);
        // if window is resizeable
//        if(primaryStage.isResizable()) {
//            // set min width and height to 300
//            primaryStage.setMinHeight(300);
//            primaryStage.setMinWidth(300);
//        }
        // set window to center of screen
        primaryStage.centerOnScreen();

        // show window
        primaryStage.show();
    }
}