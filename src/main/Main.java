package main;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class Main extends Application {

    /**
     * Main JavaFX function.
     *
     * @param args no command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Start function of Application class.
     *
     * @param primaryStage the main stage
     */
    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        try {
            //loading fxml for main menu
            root = FXMLLoader.load(getClass().getResource("/javafx/menu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //setting the primary windows size and title
        primaryStage.setTitle("Reversi");
        primaryStage.setScene(new Scene(root, 500, 350));
        primaryStage.show();
    }
}