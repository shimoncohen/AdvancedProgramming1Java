import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private final int MINBUTTONSIZE = 100;

    private static Stage mainWindow;
    private static Scene layoutDisplay;
    private static Stage gameWindow;
    private Button startGameButton;
    private Button settingsButton;
    private Button closeButton;


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
        this.startGameButton = new Button("Start game!");
        this.startGameButton.setOnAction(e -> {
            try {
                gameWindow = new Stage();
                HBox root = FXMLLoader.load(getClass().getResource("ReversiGame.fxml"));
                Scene scene = new Scene(root, 500, 400);
                scene.getStylesheets().add(getClass().getResource("Reversi.css").toExternalForm());
                // setting the game window
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
        // setting the settings button
        SettingsWindow settingsWindow = new SettingsWindow();
        this.startGameButton.setMinWidth(MINBUTTONSIZE);
        this.settingsButton = new Button("Settings");
        this.settingsButton.setOnAction(e -> settingsWindow.display());
        this.settingsButton.setMinWidth(MINBUTTONSIZE);
        // setting the close button
        this.closeButton = new Button("Close");
        this.closeButton.setMinWidth(100);
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

    public static void switchBackToMain() {
        gameWindow.close();
        mainWindow.setScene(layoutDisplay);
        mainWindow.show();
    }
}