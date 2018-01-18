package core;

/**
 * Type of display interface.
 */
public interface Display {

    /**
     * Show the winner
     *
     * @param winner the winning player
     */
    void announceWinner(PlayerColor winner);

    /**
     * Show the board.
     *
     * @param b the board
     */
    void showBoard(Board b);

    /**
     * Show no moves.
     *
     * @param c player with no moves
     */
    void noMoves(PlayerColor c);

    /**
     * Show possible moves
     *
     * @param player given player
     * @param list his possible moves
     */
    void showMoves(PlayerColor player, java.util.ArrayList<Point> list);

    /**
     * Show invalid input.
     */
    void invalidInput();

    /**
     * Show the played move.
     *
     * @param player the player who played it
     * @param row the played row
     * @param col the played column
     */
    void announceMove(PlayerColor player, int row, int col);

    /**
     * Show the played move.
     *
     * @param player the player who played
     */
    void announceNoMve(PlayerColor player);
}
