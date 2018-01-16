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


public class MenuController implements Initializable {

    public void handleNewGame() {
        //Launch the game.
        GameLauncher gameLauncher = new GameLauncher();

        //create game runner.
        GameRunner game = new GameRunner(gameLauncher.getBoard(), gameLauncher.getPlayer1(), gameLauncher.getPlayer2(),
                gameLauncher.getRules(), gameLauncher.getDisplay());

        //run the game.
        game.run();
    }

    public void handleSettings(ActionEvent event) {
        //try load settings fxml.
        try {
            Parent settingsPage = FXMLLoader.load(getClass().getResource("settings.fxml"));
            Stage settingsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            settingsStage.setScene(new Scene(settingsPage, 500, 350));
            settingsStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         try {
         SettingsController sc = new SettingsController();
         } catch (IOException e) {
         e.printStackTrace();
         }**/
    }

    public void handleQuit() {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}