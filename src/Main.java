import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import jdk.nashorn.internal.objects.annotations.Property;

public class Main extends Application {

    final int MINBUTTONSIZE = 100;

    Stage mainWindow;
    Scene layoutDisplay;
    Button startGameButton;
    Button settingsButton;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainWindow = primaryStage;
        mainWindow.setTitle("Reversi");

        VBox layoutMain = new VBox(20);
        layoutDisplay = new Scene(layoutMain, 300, 300);

        // define the main windows buttons
        startGameButton = new Button("Start game!");
        startGameButton.setOnAction(e -> {
            try {
                HBox root = (HBox)FXMLLoader.load(getClass().getResource("ReversiGame.fxml"));
                Scene scene = new Scene(root, 500, 400);
                scene.getStylesheets().add(getClass().getResource("Reversi.css").toExternalForm());

                //primaryStage.setResizable(false);
                primaryStage.setTitle("Reversi");
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            Game.run();

//            Label l1 = new Label("l1");
//            Label l2 = new Label("l2");
//
//            BorderPane gameLayout = new BorderPane();
//            VBox gameInfo = new VBox(10);
//            gameInfo.setAlignment(Pos.CENTER);
//            gameInfo.getChildren().addAll(l1, l2);
//            int[][] b = new int[4][4];
//            ReversiBoard board = new ReversiBoard(b);
//            board.setAlignment(Pos.CENTER);
//            //board.getChildren().addAll(l3, l4);
//            gameLayout.leftProperty().setValue(board);
//            gameLayout.rightProperty().setValue(gameInfo);
//            Button temp = new Button();
//            gameLayout.getChildren().add(temp);
//            layoutDisplay = new Scene(gameLayout, 300, 300);
//            primaryStage.setScene(layoutDisplay);
//            primaryStage.show();
        });
        startGameButton.setMinWidth(MINBUTTONSIZE);
        settingsButton = new Button("Settings");
        settingsButton.setOnAction(e -> SettingsWindow.display());
        settingsButton.setMinWidth(MINBUTTONSIZE);

        layoutMain.setAlignment(Pos.CENTER);
        layoutMain.getChildren().addAll(startGameButton, settingsButton);

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