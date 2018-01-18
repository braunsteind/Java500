package core;

/**
 * Game rules interface.
 */
public interface Rules {

    /**
     * Returns an ArrayList with all possible moves of the given player.
     *
     * @param b
     * @param c
     * @return
     */
    java.util.ArrayList<Point> whereCanPut(Board b, PlayerColor c);

    /**
     * Returns the winner.
     *
     * @param b the game board
     * @return which player has won
     */
    PlayerColor winner(Board b);

    /**
     * Get the score of a given player.
     *
     * @param player the wanted player
     * @param b the board
     * @return wanted player's scroe
     */
    int getScore(PlayerColor player, Board b);

    /**
     *
     */
    void endGame();

    /**
     * Returns true if game has ended, false otherwise
     *
     * @return game end status
     */
    boolean getEndGame();

}
