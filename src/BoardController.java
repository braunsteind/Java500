import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BoardController implements Initializable {

    public static final int WIDTH = 400;

    @FXML
    private GridPane root;
    @FXML
    private Label player1Score;
    @FXML
    private Label player2Score;
    private Color player1Color;
    private Color player2Color;
    private PlayerColor currentPlayer;
    private GameLauncher gameLauncher;

    public BoardController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //get the starting player's color.
        //................................
        //this.board = .............(get the board size from file)

        this.gameLauncher = new GameLauncher(8, PlayerColor.BLACK, PlayerColor.WHITE);
        currentPlayer = PlayerColor.BLACK;
        player1Color = Color.BLACK;
        player2Color = Color.WHITE;
        //get board.
        Board board = this.gameLauncher.getBoard();
        //set the board.
        board.setPrefWidth(WIDTH);
        board.setPrefHeight(WIDTH);
        board.draw(player1Color, player2Color);
        drawWhereCanPut();
        root.getChildren().add(0, board);

        //if game over on start.
        if (!(this.gameLauncher.getPlayer1().canPlay() || this.gameLauncher.getPlayer2().canPlay())) {
            endGame();
        }
    }

    public void handleClick(MouseEvent event) {
        Point point = calculatePoint(event.getX(), event.getY());

        //check if the point fit the rules.
        if (checkPoint(point)) {
            //put the point.
            this.gameLauncher.getBoard().put(currentPlayer, point.getRow(), point.getColumn());
            currentPlayer = swapColor(currentPlayer);
            this.gameLauncher.getBoard().draw(player1Color, player2Color);
            drawWhereCanPut();
        }
        //if no moves, change player.
        else if (this.gameLauncher.getRules().whereCanPut(this.gameLauncher.getBoard(), currentPlayer).isEmpty()) {
            currentPlayer = swapColor(currentPlayer);
            drawWhereCanPut();
        }

        //check if game over.
        if (!(this.gameLauncher.getPlayer1().canPlay() || this.gameLauncher.getPlayer2().canPlay())) {
            endGame();
        }
    }

    private Point calculatePoint(double x, double y) {
        //calculate the size of each cell.
        double sizeOfCell = ((double) (WIDTH)) / ((double) (this.gameLauncher.getBoard().getSize()));
        //check the point in board.
        double boardX = x / sizeOfCell;
        double boardY = y / sizeOfCell;
        //set the point.
        return new Point((int) boardX, (int) boardY);
    }

    /**
     * Swap the current player.
     *
     * @param currentPlayer The current player.
     * @return The new current player.
     */
    private PlayerColor swapColor(PlayerColor currentPlayer) {
        if (currentPlayer == PlayerColor.BLACK)
            return PlayerColor.WHITE;
        return PlayerColor.BLACK;
    }

    private boolean checkPoint(Point selectedPoint) {
        //check where can put.
        ArrayList<Point> whereCanPut = this.gameLauncher.getRules().whereCanPut(this.gameLauncher.getBoard(), currentPlayer);

        //check if selected point is valid.
        for (Point p : whereCanPut) {
            if (p.equals(selectedPoint)) {
                return true;
            }
        }
        return false;
    }

    private void drawWhereCanPut() {
        ArrayList<Point> whereCanPut = this.gameLauncher.getRules().whereCanPut(this.gameLauncher.getBoard(), currentPlayer);
        for (Point p : whereCanPut) {
            this.gameLauncher.getBoard().drawSquare(p.getRow(), p.getColumn(), Color.GOLD);
        }
    }


    private void endGame() {
        //set alert.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);

        //get the winner.
        PlayerColor winner = this.gameLauncher.getRules().winner(this.gameLauncher.getBoard());
        //print the right message.
        if (winner == PlayerColor.BLACK)
            alert.setContentText("Player1 Won!");
        else if (winner == PlayerColor.WHITE)
            alert.setContentText("Player2 Won!");
        else
            alert.setContentText("Tie!");

        Parent settingsPage = null;
        Stage settingsStage = null;
        //try load settings fxml.
        try {
            settingsPage = FXMLLoader.load(getClass().getResource("menu.fxml"));
            settingsStage = (Stage) alert.getDialogPane().getScene().getWindow();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        alert.showAndWait();
        settingsStage.setScene(new Scene(settingsPage, 500, 400));
        settingsStage.show();
    }
}