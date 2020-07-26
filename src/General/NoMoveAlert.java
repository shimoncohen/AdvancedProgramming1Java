package General;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NoMoveAlert {

    private static final int ALERTWIDTH = 250;
    private static final int ALERTHEIGHT = 40;

    /*
     * function name: popUp.
     * input: root, an HBov to disable while the alert is up.
     * output: none.
     * operation: shows a window with no move message.
     */
    public static void popUp(HBox root) {
        // setting a new window.
        Stage noMovesWindow = new Stage();
        noMovesWindow.setTitle("No moves!");
        noMovesWindow.centerOnScreen();
        // set the label message.
        Label noMovesLabel = new Label("No moves!\nturn goes to other player.");
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.getChildren().add(noMovesLabel);
        Scene scene = new Scene(box, ALERTWIDTH, ALERTHEIGHT);
        root.setDisable(true);
        noMovesWindow.setScene(scene);
        noMovesWindow.showAndWait();
        root.setDisable(false);
    }
}