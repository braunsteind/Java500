package core;

/**
 * This class initializes game components.
 */
public class GameLauncher {

    //class members
    private Rules rules;
    private Display display;
    private Board board;
    private LocalHumanPlayer player1;
    private LocalHumanPlayer player2;

    /**
     * Class constructor.
     *
     * @param size the board size
     * @param player1Color the first player
     * @param player2Color the seocnd player
     */
    public GameLauncher(int size, PlayerColor player1Color, PlayerColor player2Color) {
        this.board = new Board(size);
        this.rules = new RegularRules();
        this.display = new GuiDisplay();
        this.player1 = new LocalHumanPlayer(player1Color, board, rules, display);
        this.player2 = new LocalHumanPlayer(player2Color, board, rules, display);
    }

    /**
     * Returns the game rules.
     *
     * @return game rules
     */
    public Rules getRules() {
        return this.rules;
    }

    /**
     * Returns the game display.
     *
     * @return game display
     */
    public Display getDisplay() {
        return this.display;
    }

    /**
     * Returns the game board.
     *
     * @return the board
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Returns the first player
     *
     * @return first player
     */
    public LocalHumanPlayer getPlayer1() {
        return this.player1;
    }

    /**
     * Returns the second player
     *
     * @return second player
     */
    public LocalHumanPlayer getPlayer2() {
        return this.player2;
    }
}