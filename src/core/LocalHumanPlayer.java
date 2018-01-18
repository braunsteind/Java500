package core;

import java.util.ArrayList;
import java.util.Scanner;

public class LocalHumanPlayer implements Player {

    private Board board;
    private Rules rules;
    private PlayerColor color;
    private Display display;

    /**
     * Constructor.
     *
     * @param playerCol The player color.
     * @param b         The game board.
     * @param rul       The game rules.
     * @param disp      The display format.
     */
    public LocalHumanPlayer(PlayerColor playerCol, Board b, Rules rul, Display disp) {
        this.color = playerCol;
        this.board = b;
        this.rules = rul;
        this.display = disp;
    }


    public Point getInput(ArrayList<Point> moves) {
        Point p = new Point(-2, -2);
        int row, col;

        Scanner reader = new Scanner(System.in);
        while (true) {
            row = reader.nextInt();
            col = reader.nextInt();

            // CHECK IF ROW AND COL ARE NUMBERS

            //fixint positions.
            row--;
            col--;

            p.setRow(row);
            p.setCol(col);

            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).equals(p)) {
                    return p;
                }
            }
        }
    }

    public Point playMove() {
        Point choice = new Point(-2, -2);
        ArrayList<Point> moves;
        //show board.
        display.showBoard(board);
        //get the player's possible moves.
        moves = rules.whereCanPut(board, color);
        //if no moves for player1.
        if (moves.isEmpty()) {
            display.noMoves(color);
            return choice;
        }
        //show moves.
        display.showMoves(color, moves);
        //get the move.
        choice = getInput(moves);
        return choice;
    }

    public PlayerColor getColor() {
        return this.color;
    }

    public void setColor(PlayerColor newColor) {
        this.color = newColor;
    }

    public boolean canPlay() {
        ArrayList<Point> currentMoves = rules.whereCanPut(this.board, this.color);
        if (currentMoves.size() == 0) {
            return false;
        }
        return true;
    }

    public void endPlay() {
    }
}
