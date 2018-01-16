public class GameRunner {

    private Board board;
    private Player player1;
    private Player player2;
    private Rules rules;
    private Display display;

    public GameRunner(Board b, Player p1, Player p2, Rules rules, Display dsp) {
        this.board = b;
        this.player1 = p1;
        this.player2 = p2;
        this.rules = rules;
        this.display = dsp;
    }

    public void run() {
        Point played;

        //play while at least one player can.
        while (player1.canPlay() || player2.canPlay()) {
            //make sure black play first.
            if (player1.getColor() == PlayerColor.BLACK) {
                //play with player1.
                played = player1.playMove();
                board.put(player1.getColor(), played.getRow(), played.getColumn());
                //if game not over.
                if (player1.canPlay() || player2.canPlay()) {
                    //play with player2.
                    played = player2.playMove();
                    board.put(player2.getColor(), played.getRow(), played.getColumn());
                }
            } else {
                //play with player2.
                played = player2.playMove();
                board.put(player2.getColor(), played.getRow(), played.getColumn());
                //if game not over.
                if (player1.canPlay() || player2.canPlay()) {
                    //play with player1.
                    played = player1.playMove();
                    board.put(player1.getColor(), played.getRow(), played.getColumn());
                }
            }
        }

        //if game wan't ended before time.
        if (!rules.getEndGame()) {
            player1.endPlay();
            //print the board.
            display.showBoard(board);
            //check for winner.
            PlayerColor winner = rules.winner(board);
            display.announceWinner(winner);
        }
    }
}