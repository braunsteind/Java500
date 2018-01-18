package core;

public interface Rules {
    //Enum for representing players
    java.util.ArrayList<Point> whereCanPut(Board b, PlayerColor c);

    PlayerColor winner(Board b);

    int getScore(PlayerColor player, Board b);

    void endGame();

    boolean getEndGame();

}
