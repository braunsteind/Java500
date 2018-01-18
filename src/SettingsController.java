import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    public static final int SIZE = 8;
    public static final String FILE_NAME = "data";

    @FXML
    private ComboBox<Integer> boardSizeBox;
    @FXML
    private ColorPicker player1ColorPicker;
    @FXML
    private ColorPicker player2ColorPicker;
    @FXML
    private Label errorMsg;

    private int boardSize;
    private PlayerColor startingPlayer;
    private PlayerColor secondPlayer;
    private Color player1Color;
    private Color player2Color;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set default settings.
        this.boardSize = SIZE;
        player1Color = Color.WHITE;
        player2Color = Color.WHITE;
        startingPlayer = PlayerColor.BLACK;
        secondPlayer = PlayerColor.WHITE;
        this.errorMsg.setText("");

        //set size options.
        boardSizeBox.getItems().addAll(4, 6, 8, 10, 12, 14, 16, 18, 20);
    }

    public SettingsController() {
    }

    @FXML
    public void setPlayer1Start() {
        //set the starting player to black player.
        this.startingPlayer = PlayerColor.BLACK;
        this.secondPlayer = PlayerColor.WHITE;
    }

    @FXML
    public void setPlayer2Start() {
        //set the starting player to white player.
        this.startingPlayer = PlayerColor.WHITE;
        this.secondPlayer = PlayerColor.BLACK;
    }

    @FXML
    public void setSize() {
        this.boardSize = this.boardSizeBox.getValue();
    }

    public void setPlayer1Color() {
        this.player1Color = this.player1ColorPicker.getValue();
    }

    public void setPlayer2Color() {
        this.player2Color = this.player2ColorPicker.getValue();
    }

    public void handleBack() {
        //check not the same color.
        if (player1Color.equals(player2Color)) {
            this.errorMsg.setText("Error: identical color");
        } else {
            //create Settings File.
            SettingsFile sf = new SettingsFile(FILE_NAME);
            try {
                //save the file.
                sf.save(boardSize, startingPlayer, secondPlayer, player1Color.toString(), player2Color.toString());

                //change scene.
                Parent settingsPage = FXMLLoader.load(getClass().getResource("menu.fxml"));
                Stage settingsStage = (Stage) boardSizeBox.getScene().getWindow();
                settingsStage.setScene(new Scene(settingsPage, 500, 400));
                settingsStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}