package core;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Color.TRANSPARENT;

/**
 * Board class.
 */
public class Board extends GridPane {

    public static final int DIVIDE = 8;

    //class members
    private int size;
    private Point lastPut;
    private PlayerColor[][] board;

    /**
     * core.Board constructor.
     * Creating a board with the given size.
     *
     * @param boardSize the board size
     */
    public Board(int boardSize) {
        /**PlayerColor second;
         if (starter == PlayerColor.BLACK)
         second = PlayerColor.WHITE;
         else
         second = PlayerColor.BLACK;**/

        this.getChildren().clear();

        int i, j;

        this.lastPut = new Point(-3, -3);
        this.size = boardSize;
        this.board = new PlayerColor[size][size];

        //initializing the board with empty cells.
        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                this.board[i][j] = PlayerColor.EMPTY;
            }
        }

        //initializing starting position.
        board[(size / 2) - 1][size / 2] = PlayerColor.BLACK;
        board[size / 2][(size / 2) - 1] = PlayerColor.BLACK;
        board[(size / 2) - 1][(size / 2) - 1] = PlayerColor.WHITE;
        board[size / 2][size / 2] = PlayerColor.WHITE;


        /**note: if want to change pos of stating board!
         * //need to get PlayerColor starter from GameLauncher.
         board[(size / 2) - 1][size / 2] = starter;
         board[size / 2][(size / 2) - 1] = starter;
         board[(size / 2) - 1][(size / 2) - 1] = second;
         board[size / 2][size / 2] = second;**/
    }

    /**
     * Copy constructor.
     * Creating a new board from a given one.
     *
     * @param b the board to copy
     */
    public Board(Board b) {
        this.getChildren().clear();

        int i, j;

        this.size = b.size;
        this.lastPut = b.getLastPut();

        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                this.board[i][j] = b.getSquare(i, j);
            }
        }
    }

    /**
     * Set the board size.
     *
     * @param size The new board size.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Get the board size.
     *
     * @return board size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Get the square in row i and column j.
     *
     * @param row The row.
     * @param col The column.
     * @return The square.
     */
    public PlayerColor getSquare(int row, int col) {
        return board[row][col];
    }

    public void put(PlayerColor player, int row, int col) {

        int noMove = -2;
        //if point not on board.
        if (row < 0 || row >= size || col < 0 || col >= size) {
            //if didn't put anything
            lastPut.setPoint(noMove, noMove);
            return;
        }
        //put.
        this.board[row][col] = player;
        ;
        //update lastPut.
        lastPut.setPoint(row, col);
        int i, j;
        PlayerColor enemy;
        if (player == PlayerColor.BLACK) {
            enemy = PlayerColor.WHITE;
        } else {
            enemy = PlayerColor.BLACK;
        }


        //up.
        i = row - 1;
        j = col;
        //if there is an enemy.
        if (i >= 0 && this.board[i][j] == enemy) {
            //go to the last enemy.
            while (i >= 0 && this.board[i][j] == enemy) {
                i--;
            }
            //if should flip.
            if (i >= 0 && this.board[i][j] == player) {
                flipBetween(player, row, col, i, j);
            }
        }
        //up right.
        i = row - 1;
        j = col + 1;
        //if there is an enemy.
        if (i >= 0 && j < size && this.board[i][j] == enemy) {
            //go to the last enemy.
            while (i >= 0 && j < size && this.board[i][j] == enemy) {
                i--;
                j++;
            }
            //if can put.
            if (i >= 0 && j < size && this.board[i][j] == player) {
                flipBetween(player, row, col, i, j);
            }
        }
        //right.
        i = row;
        j = col + 1;
        //if there is an enemy.
        if (j < size && this.board[i][j] == enemy) {
            //go to the last enemy.
            while (j < size && this.board[i][j] == enemy) {
                j++;
            }
            //if can put.
            if (j < size && this.board[i][j] == player) {
                flipBetween(player, row, col, i, j);
            }
        }
        //down right.
        i = row + 1;
        j = col + 1;
        //if there is an enemy.
        if (i < size && j < size && this.board[i][j] == enemy) {
            //go to the last enemy.
            while (i < size && j < size && this.board[i][j] == enemy) {
                i++;
                j++;
            }
            //if can put.
            if (i < size && j < size && this.board[i][j] == player) {
                flipBetween(player, row, col, i, j);
            }
        }
        //down.
        i = row + 1;
        j = col;
        //if there is an enemy.
        if (i < size && this.board[i][j] == enemy) {
            //go to the last enemy.
            while (i < size && this.board[i][j] == enemy) {
                i++;
            }
            //if can put.
            if (i < size && this.board[i][j] == player) {
                flipBetween(player, row, col, i, j);
            }
        }
        //down left.
        i = row + 1;
        j = col - 1;
        //if there is an enemy.
        if (i < size && j >= 0 && this.board[i][j] == enemy) {
            //go to the last enemy.
            while (i < size && j >= 0 && this.board[i][j] == enemy) {
                i++;
                j--;
            }
            //if can put.
            if (i < size && j >= 0 && this.board[i][j] == player) {
                flipBetween(player, row, col, i, j);
            }
        }
        //left.
        i = row;
        j = col - 1;
        //if there is an enemy.
        if (j >= 0 && this.board[i][j] == enemy) {
            //go to the last enemy.
            while (j >= 0 && this.board[i][j] == enemy) {
                j--;
            }
            //if can put.
            if (j >= 0 && this.board[i][j] == player) {
                flipBetween(player, row, col, i, j);
            }
        }
        //up left.
        i = row - 1;
        j = col - 1;
        //if there is an enemy.
        if (i >= 0 && j >= 0 && this.board[i][j] == enemy) {
            //go to the last enemy.
            while (i >= 0 && j >= 0 && this.board[i][j] == enemy) {
                i--;
                j--;
            }
            //if can put.
            if (i >= 0 && j >= 0 && this.board[i][j] == player) {
                flipBetween(player, row, col, i, j);
            }
        }
    }


    /**
     * Get the last put.
     *
     * @return the last put
     */
    public Point getLastPut() {
        return this.lastPut;
    }

    /**
     * Equals override.
     *
     * @param b The other board.
     * @return True if equals, false if not.
     */
    public boolean equals(Board b) {
        int i, j;

        //in case of different sizes, no need to further check.
        if (this.size != b.size) {
            return false;
        }

        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                if (board[i][j] != b.getSquare(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Flip discs between given values.
     *
     * @param player the player that owns the new discs
     * @param i1     starting row
     * @param j1     starting column
     * @param i2     ending row
     * @param j2     ending column
     */
    private void flipBetween(PlayerColor player, int i1, int j1, int i2, int j2) {
        int temp;
        //if same row.
        if (i1 == i2) {
            //if j1 is bigger.
            if (j2 < j1) {
                //swap.
                temp = j1;
                j1 = j2;
                j2 = temp;
            }
            //while can still move right.
            while (j1 < j2) {
                //flip and move.
                this.board[i1][j1] = player;
                j1++;
            }
            //if same column.
        } else if (j1 == j2) {
            //if i1 is bigger.
            if (i2 < i1) {
                temp = i1;
                i1 = i2;
                i2 = temp;
            }
            //while can still move down.
            while (i1 < i2) {
                //flip and move.
                this.board[i1][j1] = player;
                i1++;
            }
        } else {
            //make sure to move down.
            if (i2 < i1) {
                temp = i1;
                i1 = i2;
                i2 = i1;

                temp = j1;
                j1 = j2;
                j2 = temp;
            }
            //if going right.
            if (j1 < j2) {
                //while can move right.
                while (j1 < j2) {
                    //flip and move.
                    this.board[i1][j1] = player;
                    i1++;
                    j1++;
                }
                //if going left
            } else {
                //while can move left.
                while (j2 < j1) {
                    //flip and move.
                    this.board[i1][j1] = player;
                    i1++;
                    j1--;
                }
            }
        }
    }

    /**
     * Draw the board.
     */
    public void draw(Color player1Color, Color player2Color) {
        this.getChildren().clear();
        int height = (int) this.getPrefHeight();
        int width = (int) this.getPrefWidth();
        int cellHeight = height / this.size;
        int cellWidth = width / this.size;
        int cellRadius = (cellHeight + cellWidth) / DIVIDE;

        //loop on the board.
        for (int row = 0; row < this.size; row++) {
            for (int col = 0; col < this.size; col++) {

                //draw transparent rectangles.
                Rectangle rectangle = new Rectangle(cellWidth, cellHeight, TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                this.add(rectangle, row, col);

                //if the color is black.
                if (board[row][col] == PlayerColor.BLACK) {
                    fillCircle(player1Color, cellRadius, row, col);
                }
                //if the color is white.
                else if (board[row][col] == PlayerColor.WHITE) {
                    fillCircle(player2Color, cellRadius, row, col);
                }
            }
        }
    }

    /**
     * Drawing player's discs.
     *
     * @param playerColor given player to draw
     * @param cellRadius  size of disc
     * @param row         row to draw on
     * @param col         column to draw on
     */
    private void fillCircle(Color playerColor, int cellRadius, int row, int col) {
        Circle circle = new Circle(cellRadius);
        circle.setFill(playerColor);
        circle.setStroke(Color.BLACK);
        this.add(circle, row, col);
        this.setValignment(circle, VPos.CENTER);
        this.setHalignment(circle, HPos.CENTER);
    }

    /**
     * Draw in specific square.
     *
     * @param row   The row.
     * @param col   The col.
     * @param color The color.
     */
    public void drawSquare(int row, int col, Color color) {
        int height = (int) this.getPrefHeight();
        int width = (int) this.getPrefWidth();
        int cellHeight = height / this.size;
        int cellWidth = width / this.size;

        Rectangle rectangle = new Rectangle(cellWidth, cellHeight, color);
        rectangle.setStroke(Color.BLACK);
        this.add(rectangle, row, col);
    }
}