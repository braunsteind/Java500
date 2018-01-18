package core;

public interface Display {

    void announceWinner(PlayerColor winner);

    void showBoard(Board b);

    void noMoves(PlayerColor c);

    void showMoves(PlayerColor player, java.util.ArrayList<Point> list);

    void invalidInput();

    void announceMove(PlayerColor player, int row, int col);

    void announceNoMve(PlayerColor player);
}
