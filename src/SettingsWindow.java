import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.scene.control.*;

public class SettingsWindow {

    public static void display() {
        final int WIDTH = 300;
        final int HEIGHT = 300;

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Settings");
        window.setResizable(false);

        // starting player options display options
        Label startingPlayer = new Label("Starting player:");
        ComboBox<String> selectStartingPlayer = new ComboBox<>();
        selectStartingPlayer.setValue("BlackPlayer");
        selectStartingPlayer.getItems().add("BlackPlayer");
        selectStartingPlayer.getItems().add("WhitePlayer");

        // choose first players color display
        Label firstPlayerColor = new Label("First player color:");
        ColorPicker firstColorPicker = new ColorPicker();
        firstColorPicker.setValue(Color.BLACK);
        // choose second players color display
        Label secondPlayerColor = new Label("Second player color:");
        ColorPicker secondColorPicker = new ColorPicker();

        // setting the button that closes the window
        Button closeAndave = new Button("Close & save");
        closeAndave.setOnAction(e -> {

            // todo write to file here

            window.close();
        });

        // setting the button that closes the window
        Button closeNoSave = new Button("Close w/o saving");
        closeNoSave.setOnAction(e -> {

            // todo write to file here

            window.close();
        });

        // setting the layout of the window
        VBox layout = new VBox(15);
        VBox innerLayout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        innerLayout.setAlignment(Pos.CENTER);

        // add all of the wanted objects to the layouts
        innerLayout.getChildren().add(closeAndave);
        innerLayout.getChildren().add(closeNoSave);
        layout.getChildren().addAll(startingPlayer, selectStartingPlayer, firstPlayerColor, firstColorPicker,
                secondPlayerColor, secondColorPicker, innerLayout);

        // set the windows scene to be the layout
        Scene scene = new Scene(layout, WIDTH, HEIGHT);
        window.setScene(scene);
        // display window
        window.showAndWait();
    }
}
