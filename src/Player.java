public interface Player {

    /**
     * Play one move with the player.
     *
     * @return The chosen point. If couldn't play return -2, -2.
     */
    Point playMove();

    /**
     * Check if the player can play.
     *
     * @return True if can play, false if can't.
     */
    boolean canPlay();

    /**
     * End playing.
     */
    void endPlay();

    /**
     * Get the player color.
     *
     * @return The player color.
     */
    PlayerColor getColor();

    /**
     * Set the color of the player.
     *
     * @param newColor The new color.
     */
    void setColor(PlayerColor newColor);
}
