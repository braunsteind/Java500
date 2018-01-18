package javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * MenuController class.
 * Main menu.
 * Class for handling interaction with menu.fxml file.
 */
public class MenuController implements Initializable {

    /**
     * Handling the event of Play game buttom clicked.
     *
     * @param event the mouse clicking event
     */
    public void handleNewGame(ActionEvent event) {
        //try load settings fxml.
        try {
            Parent settingsPage = FXMLLoader.load(getClass().getResource("/javafx/board.fxml"));
            Stage settingsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            settingsStage.setScene(new Scene(settingsPage, 550, 430));
            settingsStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handle the event of clicking the settings button.
     *
     * @param event mouse clicking on settings
     */
    public void handleSettings(ActionEvent event) {
        //try load settings fxml.
        try {
            Parent settingsPage = FXMLLoader.load(getClass().getResource("/javafx/settings.fxml"));
            Stage settingsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            settingsStage.setScene(new Scene(settingsPage, 500, 350));
            settingsStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handle the event of mouse clicking the exit button.
     */
    public void handleQuit() {
        System.exit(0);
    }

    /**
     * Initialize method of Application class.
     *
     * @param location location
     * @param resources resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}