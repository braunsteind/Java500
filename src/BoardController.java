import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BoardController implements Initializable {

    public static final int WIDTH = 400;
    public static final String FILE_NAME = "data";

    @FXML
    private GridPane root;
    @FXML
    private VBox scoreBox;
    @FXML
    private Label playingPlayer;
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
        SettingsFile sf = new SettingsFile(FILE_NAME);
        try {
            sf.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.gameLauncher = new GameLauncher(sf.getBoardSize(), sf.getFirstPlayer(), sf.getSecondPlayer());
        currentPlayer = sf.getFirstPlayer();
        System.out.println(sf.getPlayer1Color());
        System.out.println(sf.getPlayer2Color());
        player1Color = Color.web(sf.getPlayer1Color());
        player2Color = Color.web(sf.getPlayer2Color());
        //get board.
        Board board = this.gameLauncher.getBoard();
        //set the board.
        board.setPrefWidth(WIDTH);
        board.setPrefHeight(WIDTH);
        board.draw(player1Color, player2Color);
        drawWhereCanPut();
        root.getChildren().add(0, board);

        //set the starting player.
        if (currentPlayer == PlayerColor.BLACK) {
            this.playingPlayer.setText("Playing player: 1");
        } else {
            this.playingPlayer.setText("Playing player: 2");
        }

        this.player1Score.setText("Player1 score: 2");
        this.player2Score.setText("Player2 score: 2");
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
            //change score.
            int score1 = this.gameLauncher.getRules().getScore(PlayerColor.BLACK, this.gameLauncher.getBoard());
            int score2 = this.gameLauncher.getRules().getScore(PlayerColor.WHITE, this.gameLauncher.getBoard());
            this.player1Score.setText("Player1 score: " + score1);
            this.player2Score.setText("Player2 score: " + score2);
            //swap player.
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
        if (currentPlayer == PlayerColor.BLACK) {
            this.playingPlayer.setText("Playing player: 2");
            return PlayerColor.WHITE;
        }
        this.playingPlayer.setText("Playing player: 1");
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
            settingsStage = (Stage) player1Score.getScene().getWindow();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        alert.showAndWait();
        settingsStage.setScene(new Scene(settingsPage, 500, 400));
        settingsStage.show();
    }
}