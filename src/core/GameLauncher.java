package core;

public class GameLauncher {

    private Rules rules;
    private Display display;
    private Board board;
    private LocalHumanPlayer player1;
    private LocalHumanPlayer player2;


    public GameLauncher(int size, PlayerColor player1Color, PlayerColor player2Color) {
        this.board = new Board(size, player1Color);
        this.rules = new RegularRules();
        this.display = new GuiDisplay();

        this.player1 = new LocalHumanPlayer(player1Color, board, rules, display);
        this.player2 = new LocalHumanPlayer(player2Color, board, rules, display);
    }

    public Rules getRules() {
        return this.rules;
    }

    public Display getDisplay() {
        return this.display;
    }

    public Board getBoard() {
        return this.board;
    }

    public LocalHumanPlayer getPlayer1() {
        return this.player1;
    }

    public LocalHumanPlayer getPlayer2() {
        return this.player2;
    }
}