import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable {

    public static final int WIDTH = 400;

    //private GameLauncher gameLauncher;

    @FXML
    private GridPane root;
    private Board board;
    private PlayerColor currentPlayer;

    public BoardController() {
        //this.gameLauncher = gameLauncher;
        //this.board = gameLauncher.getBoard();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //get the starting player's color.
        //................................

        this.board = new Board(8);
        //this.board = .............(get the board size from file)
        board.setPrefWidth(WIDTH);
        board.setPrefHeight(WIDTH);
        board.draw();
        root.getChildren().add(0, board);
    }

    public void handleClick(MouseEvent event) {
        Point p = calculatePoint(event.getX(), event.getY());
        System.out.println("x: " + p.getRow() + "\ny: " + p.getColumn());
    }

    private Point calculatePoint(double x, double y) {
        //check point is on board.
        if (!(x < WIDTH && y < WIDTH)) {
            return new Point(-100, -100);
        }
        //calculate the size of each cell.
        double sizeOfCell = ((double) (WIDTH)) / ((double) (board.getSize()));
        //check the point in board.
        double x1 = x / sizeOfCell;
        double y1 = y / sizeOfCell;
        //set the point.
        return new Point((int) x1, (int) y1);


    }
}