import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SettingsController implements java.io.Serializable {

    private static final int SIZE = 8;

    private int size;
    private PlayerColor startingPlayer;
    private PlayerColor secondPlayer;

    @FXML
    private TextField boardSize;

    public SettingsController() throws IOException {
        /**
         File file = new File("settings.txt");

         ObjectInputStream is = null;
         try {
         //opening and reading the source file
         is = new ObjectInputStream(new FileInputStream(file));
         SettingsController temp = (SettingsController) is.readObject();
         //import the loaded file data to the current settings
         this.size = temp.getSize();
         this.startingPlayer = temp.getStartingPlayer();
         this.secondPlayer = temp.getSecondPlayer();
         } catch (IOException e) {
         System.out.println("Error while loading");
         } catch (ClassNotFoundException e) {
         System.out.println("Problem with class");
         } finally {
         if (is != null) {
         is.close();
         }
         }**/
    }

    @FXML
    public void setBlackStartingPlayer() {
        this.startingPlayer = PlayerColor.BLACK;
        this.secondPlayer = PlayerColor.WHITE;
        System.out.println("Black is starting");
    }

    @FXML
    public void setWhiteStartingPlayer() {
        this.startingPlayer = PlayerColor.WHITE;
        this.secondPlayer = PlayerColor.BLACK;
        System.out.println("White is starting");
    }

    @FXML
    public void setSize() {
        //update size in the file.....
    }

    public int getSize() {
        return this.size;
    }

    public PlayerColor getStartingPlayer() {
        return this.startingPlayer;
    }

    public PlayerColor getSecondPlayer() {
        return this.secondPlayer;
    }
}