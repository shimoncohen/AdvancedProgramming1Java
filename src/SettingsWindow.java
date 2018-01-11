import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.scene.control.*;

import javax.swing.text.html.ImageView;
import java.io.*;
import java.util.ArrayList;

public class SettingsWindow {

    private static ComboBox<String> selectStartingPlayer = new ComboBox<>();
    private static ColorPicker firstColorPicker = new ColorPicker();
    private static ColorPicker secondColorPicker = new ColorPicker();
    private static Slider boardSize = new Slider();

    // defined const values
    private static final String FILEPATH = "src/settings.txt";
    private static final int MINSIZE = 4;
    private static final int MAXSIZE = 20;
    private static final int SLIDERMAXWIDTH = 150;
    private static final double DEFAULTBOARDSIZE = 4;
    private static final double BLOCKINCREMENT = 0;

    public void display() {
        final int WIDTH = 300;
        final int HEIGHT = 400;

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Settings");
        window.setResizable(false);

        // starting player options display options
        Label startingPlayer = new Label("Starting player:");
        this.selectStartingPlayer.setValue("BlackPlayer");
        this.selectStartingPlayer.getItems().add("BlackPlayer");
        this.selectStartingPlayer.getItems().add("WhitePlayer");

        // choose first players color display
        Label firstPlayerColor = new Label("First player color:");
        //firstColorPicker.setValue(Color.BLACK);
        // choose second players color display
        Label secondPlayerColor = new Label("Second player color:");

        Label boardSizeLabel = new Label("Board size:");
        boardSize.setMin(MINSIZE);
        boardSize.setMax(MAXSIZE);
        boardSize.setMaxWidth(SLIDERMAXWIDTH);
        boardSize.setShowTickMarks(true);
        boardSize.setShowTickLabels(true);
        boardSize.setBlockIncrement(BLOCKINCREMENT);
        for(int i = 1; i < 5; i++) {
            boardSize.adjustValue(MINSIZE + i * 2);
        }
        HBox slideLayout = new HBox(10);
        slideLayout.setAlignment(Pos.CENTER);
        slideLayout.getChildren().addAll(boardSizeLabel, boardSize);

        firstColorPicker.setValue(Color.BLACK);
        secondColorPicker.setValue(Color.WHITE);

        loadSettings();

        // setting the button that closes the window
        Button closeAndsave = new Button("Close & save");
        closeAndsave.setOnAction(e -> {

            try {
                writeSettings(boardSize.getValue(), firstColorPicker.getValue().toString(),
                        secondColorPicker.getValue().toString(), selectStartingPlayer.getValue());
            } catch (IOException exc) {
                System.out.println("Error writing into settings file");
            }
            window.close();
        });

        // setting the button that closes the window
        Button closeNoSave = new Button("Close w/o saving");
        closeNoSave.setOnAction(e -> {
            window.close();
        });

        // setting the layout of the window
        VBox layout = new VBox(15);
        Scene scene = new Scene(layout, WIDTH, HEIGHT);
        window.setScene(scene);


        VBox innerLayout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        innerLayout.setAlignment(Pos.CENTER);

        // add all of the wanted objects to the layouts
        innerLayout.getChildren().add(closeAndsave);
        innerLayout.getChildren().add(closeNoSave);
        layout.getChildren().addAll(startingPlayer, selectStartingPlayer, firstPlayerColor, firstColorPicker,
                secondPlayerColor, secondColorPicker, slideLayout, innerLayout);

        // set the windows scene to be the layout

        // display window
        window.showAndWait();
    }

    private static void writeSettings(Double size, String firstColor,
                               String secondColor, String starter) throws IOException {
        File file = new File(FILEPATH);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            Writer writer = bufferedWriter;
            // writing first player color
            writer.write("first Player color: " + firstColor + "\n");
            // writing second player color
            writer.write("second Player color: " + secondColor + "\n");
            // writing starting player
            writer.write("Starting player: " + starter + "\n");
            // writing board size
            writer.write("size: " + size + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bufferedWriter.close();
        }
    }

    public static ArrayList<String> loadSettings() {
        File file = new File(FILEPATH);
        FileReader fileReader;
        if(file.exists()) {
            String firstColor, secondColor, starter, line;
            BufferedReader bufferedReader = null;
            try {
                fileReader = new FileReader(FILEPATH);
                bufferedReader = new BufferedReader(fileReader);
                // reading first player color
                line = bufferedReader.readLine();
                line = line.replace("first Player color: ", "");
                firstColor = line;
                // reading second player color
                line = bufferedReader.readLine();
                line = line.replace("second Player color: ", "");
                secondColor = line;
                line = bufferedReader.readLine();
                // reading starting player
                line = line.replace("Starting player: ", "");
                starter = line;
                line = bufferedReader.readLine();
                // reading board size
                line = line.replace("size: ", "");
                // set values
                boardSize.setValue(Double.valueOf(line));
                selectStartingPlayer.setValue(starter);
                firstColorPicker.setValue(Color.valueOf(firstColor));
                secondColorPicker.setValue(Color.valueOf(secondColor));

            } catch (IOException e) {
                System.out.println("Error reading from file");
            } finally {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.out.println("Error closing the file");
                }
            }
            // if the file is empty
        } else {
            try {
                writeSettings(DEFAULTBOARDSIZE,"Black", "White", "BlackPlayer");
            } catch (IOException e) {
                System.out.println("Error creating the file");
            }
        }
        ArrayList<String> info = new ArrayList<>();
        info.add(selectStartingPlayer.getValue());
        info.add(firstColorPicker.getValue().toString());
        info.add(secondColorPicker.getValue().toString());
        info.add(String.valueOf(Math.floor(boardSize.getValue())).replace(".0", ""));
        return info;
    }
}

