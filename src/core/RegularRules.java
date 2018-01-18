package core; /**
 * Created by ShlomiZ on 08/01/2018.
 */

import java.util.ArrayList;


public class RegularRules implements Rules {

    private boolean endGame;

    /**
     * Constructor.
     */
    public RegularRules() {
        this.endGame = false;
    }

    /**
     * Check where the player can put.
     *
     * @param b The game board.
     * @param c The player color.
     * @return The answer vector of possible moves.
     */
    public java.util.ArrayList<Point> whereCanPut(Board b, PlayerColor c) {
        ArrayList<Point> moves = new ArrayList<Point>();
        //if end game.
        if (endGame) {
            return moves;
        }
        //loop on each square
        for (int i = 0; i < b.getSize(); i++) {
            for (int j = 0; j < b.getSize(); j++) {
                //if the square belong to the player.

                if (b.getSquare(i, j) == c) {
                    //get the results for the square.
                    checkSurrounding(b, c, i, j, moves);
                }
            }
        }
        removeDouble(moves);
        return moves;
    }

    /**
     * Tell who the winner.
     *
     * @return The winner color.
     */
    public PlayerColor winner(Board b) {
        int player1Score = getScore(PlayerColor.BLACK, b);
        int player2Score = getScore(PlayerColor.WHITE, b);

        if (player1Score > player2Score) {
            return PlayerColor.BLACK;
        } else if (player2Score > player1Score) { //if player 2 wins.
            return PlayerColor.WHITE;
        }
        //tie.
        return PlayerColor.EMPTY;
    }

    /**
     * Get the player score.
     *
     * @param player The player.
     * @param b      The game board.
     * @return The score of the player.
     */
    public int getScore(PlayerColor player, Board b) {
        int score, i, j, bSize;
        score = 0;
        bSize = b.getSize();

        for (i = 0; i < bSize; i++) {
            for (j = 0; j < bSize; j++) {
                if (b.getSquare(i, j).equals(player)) {
                    score++;
                }
            }
        }
        return score;
    }

    /**
     * End the game.
     */
    public void endGame() {
        this.endGame = true;
    }

    /**
     * Check if game ended before finished.
     *
     * @return The end game bool.
     */
    public boolean getEndGame() {
        return this.endGame;
    }


    private void checkSurrounding(Board board, PlayerColor player, int row, int col, ArrayList<Point> moves) {
        int size = board.getSize();
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
        if (i >= 0 && board.getSquare(i, j) == enemy) {
            //go to the last enemy.
            while (i >= 0 && board.getSquare(i, j) == enemy) {
                i--;
            }
            //if can put.
            if (i >= 0 && board.getSquare(i, j) == PlayerColor.EMPTY) {
                moves.add(new Point(i, j));
            }
        }
        //up right.
        i = row - 1;
        j = col + 1;
        //if there is an enemy.
        if (i >= 0 && j < size && board.getSquare(i, j) == enemy) {
            //go to the last enemy.
            while (i >= 0 && j < size && board.getSquare(i, j) == enemy) {
                i--;
                j++;
            }
            //if can put.
            if (i >= 0 && j < size && board.getSquare(i, j) == PlayerColor.EMPTY) {
                moves.add(new Point(i, j));
            }
        }
        //right.
        i = row;
        j = col + 1;
        //if there is an enemy.
        if (j < size && board.getSquare(i, j) == enemy) {
            //go to the last enemy.
            while (j < size && board.getSquare(i, j) == enemy) {
                j++;
            }
            //if can put.
            if (j < size && board.getSquare(i, j) == PlayerColor.EMPTY) {
                moves.add(new Point(i, j));
            }
        }
        //down right.
        i = row + 1;
        j = col + 1;
        //if there is an enemy.
        if (i < size && j < size && board.getSquare(i, j) == enemy) {
            //go to the last enemy.
            while (i < size && j < size && board.getSquare(i, j) == enemy) {
                i++;
                j++;
            }
            //if can put.
            if (i < size && j < size && board.getSquare(i, j) == PlayerColor.EMPTY) {
                moves.add(new Point(i, j));
            }
        }
        //down.
        i = row + 1;
        j = col;
        //if there is an enemy.
        if (i < size && board.getSquare(i, j) == enemy) {
            //go to the last enemy.
            while (i < size && board.getSquare(i, j) == enemy) {
                i++;
            }
            //if can put.
            if (i < size && board.getSquare(i, j) == PlayerColor.EMPTY) {
                moves.add(new Point(i, j));
            }
        }
        //down left.
        i = row + 1;
        j = col - 1;
        //if there is an enemy.
        if (i < size && j >= 0 && board.getSquare(i, j) == enemy) {
            //go to the last enemy.
            while (i < size && j >= 0 && board.getSquare(i, j) == enemy) {
                i++;
                j--;
            }
            //if can put.
            if (i < size && j >= 0 && board.getSquare(i, j) == PlayerColor.EMPTY) {
                moves.add(new Point(i, j));
            }
        }
        //left.
        i = row;
        j = col - 1;
        //if there is an enemy.
        if (j >= 0 && board.getSquare(i, j) == enemy) {
            //go to the last enemy.
            while (j >= 0 && board.getSquare(i, j) == enemy) {
                j--;
            }
            //if can put.
            if (j >= 0 && board.getSquare(i, j) == PlayerColor.EMPTY) {
                moves.add(new Point(i, j));
            }
        }
        //up left.
        i = row - 1;
        j = col - 1;
        //if there is an enemy.
        if (i >= 0 && j >= 0 && board.getSquare(i, j) == enemy) {
            //go to the last enemy.
            while (i >= 0 && j >= 0 && board.getSquare(i, j) == enemy) {
                i--;
                j--;
            }
            //if can put.
            if (i >= 0 && j >= 0 && board.getSquare(i, j) == PlayerColor.EMPTY) {
                moves.add(new Point(i, j));
            }
        }
        /*
        System.out.println();
        int temp1, temp2;
        for (i = 0; i < moves.size(); i++) {
            temp1 = moves.get(i).getRow() + 1;
            temp2 = moves.get(i).getColumn() + 1;
            System.out.print("("+temp1+", "+temp2+")   ");
        }
        System.out.println(); */ // values are CORRECT HERE
    }


    private void removeDouble(ArrayList<Point> list) {
        //loop on the squares.
        for (int i = 0; i < list.size(); i++) {
            //for every point, compare the other points.
            for (int j = i + 1; j < list.size(); j++) {
                //if the points are the same.
                if (list.get(i) == list.get(j)) {
                    //remove the point.
                    list.remove(i);
                    j--;
                }
            }
        }
    }
}
