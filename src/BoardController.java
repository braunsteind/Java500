import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable {

    //private GameLauncher gameLauncher;

    @FXML
    private GridPane root;
    private Board board;

    public BoardController() {
        //this.gameLauncher = gameLauncher;
        //this.board = gameLauncher.getBoard();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.board = new Board(8);
        board.setPrefWidth(400);
        board.setPrefHeight(400);
        root.getChildren().add(0, board);
        board.draw();
    }
    /**
     * Handle click on board.
     *
     * @param event The event.
     */
    /**
     public void handleClick(ActionEvent event) {
     System.out.println("BLJAFGKJASKGJSALKGJKLSAGJASKLGJKDLSGHJKALSGHKLADGHLSAKHGLKASGKALD");
     int col = this.board.getColumnIndex(null);
     int row = this.board.getRowIndex(null);
     }
     **/
}