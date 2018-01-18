package javafx;

import core.PlayerColor;
import core.SettingsFile;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * SettingsController class.
 * Class for handling interaction with settings.fxml file.
 */
public class SettingsController implements Initializable {
    public static final int SIZE = 8;
    public static final String FILE_NAME = "data";

    //class graphic members
    @FXML
    private ComboBox<Integer> boardSizeBox;
    @FXML
    private ColorPicker player1ColorPicker;
    @FXML
    private ColorPicker player2ColorPicker;
    @FXML
    private Label errorMsg;

    //class members
    private int boardSize;
    private PlayerColor startingPlayer;
    private PlayerColor secondPlayer;
    private Color player1Color;
    private Color player2Color;

    /**
     * Initializing settings menu.
     *
     * @param location location
     * @param resources resources
     */
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

    /**
     * Default constructor.
     */
    public SettingsController() {}

    /**
     * Set the black player as starting player.
     */
    @FXML
    public void setPlayer1Start() {
        //set the starting player to black player.
        this.startingPlayer = PlayerColor.BLACK;
        this.secondPlayer = PlayerColor.WHITE;
    }

    /**
     * Set the white player as starting player.
     */
    @FXML
    public void setPlayer2Start() {
        //set the starting player to white player.
        this.startingPlayer = PlayerColor.WHITE;
        this.secondPlayer = PlayerColor.BLACK;
    }

    /**
     * Set the board size from menu settings input.
     */
    @FXML
    public void setSize() {
        this.boardSize = this.boardSizeBox.getValue();
    }

    /**
     * Set the color for player1.
     */
    public void setPlayer1Color() {
        this.player1Color = this.player1ColorPicker.getValue();
    }

    /**
     * Set the color for player 2.
     */
    public void setPlayer2Color() {
        this.player2Color = this.player2ColorPicker.getValue();
    }

    /**
     * Handle the event of saving the settings file and going back to menu.
     */
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
                Parent settingsPage = FXMLLoader.load(getClass().getResource("/javafx/menu.fxml"));
                Stage settingsStage = (Stage) boardSizeBox.getScene().getWindow();
                settingsStage.setScene(new Scene(settingsPage, 500, 400));
                settingsStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}