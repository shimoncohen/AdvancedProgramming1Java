// 315383133 shimon cohen
// 302228275 Nadav Spitzer
package General;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EndGameAlert {

    private static final int ALERTWIDTH = 250;
    private static final int ALERTHEIGHT = 60;
    private static final int SPACING = 10;

    public static void popUp(HBox root, String message) {
        // setting a new window.
        Stage noMovesWindow = new Stage();
        noMovesWindow.setTitle("End!");
        noMovesWindow.centerOnScreen();
        // set the label message.
        Label noMovesLabel = new Label(message);
        Label back = new Label("Close to go back to Main menu");
        VBox box = new VBox(SPACING);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(noMovesLabel, back);
        Scene scene = new Scene(box, ALERTWIDTH, ALERTHEIGHT);
        root.setDisable(true);
        noMovesWindow.setScene(scene);
        noMovesWindow.showAndWait();
        root.setDisable(false);
    }
}
