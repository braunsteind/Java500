public class ConsoleDisplay implements Display {
    public void announceWinner(PlayerColor winner) {

        if (winner == PlayerColor.BLACK) {
            System.out.println();
            System.out.println("X IS THE WINNER");
            System.out.println();

        } else if (winner == PlayerColor.WHITE) {
            System.out.println();
            System.out.println("O IS THE WINNER");
            System.out.println();

        } else {
            System.out.println();
            System.out.println("TIE");
            System.out.println();
        }
    }


    public void showBoard(Board board) {
        PlayerColor color;

        System.out.println();
        System.out.println("Current board:");

        System.out.print(" | ");
        for (int i = 1; i <= board.getSize(); i++) {
            System.out.print(i + " | ");
        }
        System.out.println();
        System.out.print("--");
        for (int i = 0; i < board.getSize(); i++) {
            System.out.print("----");
        }
        System.out.println();
        //loop for the rows.
        for (int i = 0; i < board.getSize(); i++) {
            System.out.print(i + 1);
            System.out.print("| ");
            //loop for the columns.
            for (int j = 0; j < board.getSize(); j++) {
                //check if the spot is marked.

                color = board.getSquare(i, j);
                if (color == PlayerColor.EMPTY) {
                    System.out.print("  | ");
                } else if (color == PlayerColor.BLACK) {
                    System.out.print("X | ");
                } else {
                    System.out.print("O | ");
                }
            }
            System.out.println();
            System.out.print("--");

            for (int k = 0; k < board.getSize(); k++) {
                System.out.print("----");
            }
            System.out.println();
        }
    }

    public void noMoves(PlayerColor color) {
        if (color == PlayerColor.BLACK) {
            System.out.print("X: It's your move.");
            System.out.println();
        } else if (color == PlayerColor.WHITE) {
            System.out.print("O: It's your move.");
            System.out.println();
        } else {
            return;
        }
        System.out.println("No possible moves. Play passes back to the other player. Press any key to continue.");
        //cin.ignore(std::numeric_limits<streamsize>::max(), '\n');
    }

    public void showMoves(PlayerColor player, java.util.ArrayList<Point> list) {

        int row, col;

        if (player == PlayerColor.BLACK) {
            System.out.println("X: It's your move.");
            System.out.println("Your possible moves: ");
        } else if (player == PlayerColor.WHITE) {
            System.out.println("O: It's your move.");
            System.out.println("Your possible moves: ");
        } else {
            return;
        }
        //print the possible moves.
        for (int i = 0; i < list.size() - 1; i++) {
            row = list.get(i).getRow() + 1;
            col = list.get(i).getColumn() + 1;
            System.out.print("(" + row + "," + col + "), ");
        }
        //print last possible move.
        row = list.get(list.size() - 1).getRow() + 1;
        col = list.get(list.size() - 1).getColumn() + 1;
        System.out.print("(" + row + "," + col + ")");
        System.out.println();
        System.out.println("Please enter your move (row col): ");
    }

    public void invalidInput() {
        System.out.print("Invalid input, please enter your move (row col): ");
    }

    public void announceMove(PlayerColor player, int row, int col) {
        if (player == PlayerColor.BLACK) {
            System.out.println("X played (" + row + "," + col + ")");
        } else if (player == PlayerColor.WHITE) {
            System.out.println("O played (" + row + "," + col + ")");
        }
    }

    public void announceNoMve(PlayerColor player) {
        if (player == PlayerColor.BLACK) {
            System.out.println("X has no moves");
        } else if (player == PlayerColor.WHITE) {
            System.out.println("O has no moves");
        }
    }
}