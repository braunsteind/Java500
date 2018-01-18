package core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SettingsFile implements java.io.Serializable {
    //if not file, set default.
    public static final int SIZE = 8;
    public static final PlayerColor FIRST_PLAYER = PlayerColor.BLACK;
    public static final PlayerColor SECOND_PLAYER = PlayerColor.WHITE;
    //Black color.
    public static final String PLAYER1COLOR = "0x000000ff";
    //White color.
    public static final String PLAYER2COLOR = "0xffffffff";


    //class members
    private int boardSize;
    private PlayerColor firstPlayer;
    private PlayerColor secondPlayer;
    private String player1Color;
    private String player2Color;
    private String filename;

    /**
     * Constructor.
     *
     * @param filename The file name.
     */
    public SettingsFile(String filename) {
        this.filename = filename;
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    public PlayerColor getFirstPlayer() {
        return this.firstPlayer;
    }

    public PlayerColor getSecondPlayer() {
        return this.secondPlayer;
    }

    public String getPlayer1Color() {
        return this.player1Color;
    }

    public String getPlayer2Color() {
        return this.player2Color;
    }

    /**
     * Load table data from file.
     * Current table data is cleared.
     *
     * @throws IOException if failed to open the file.
     */
    public void load() throws IOException {
        File file = new File(this.filename);
        //if file exist.
        if (file.exists() && !file.isDirectory()) {
            ObjectInputStream is = null;
            try {
                //opening and reaind the source file
                is = new ObjectInputStream(new FileInputStream(this.filename));
                SettingsFile temp = (SettingsFile) is.readObject();
                //import the loaded file data to the current core.SettingsFile
                this.boardSize = temp.getBoardSize();
                this.firstPlayer = temp.getFirstPlayer();
                this.secondPlayer = temp.getSecondPlayer();
                this.player1Color = temp.getPlayer1Color();
                this.player2Color = temp.getPlayer2Color();
            } catch (IOException e) {
                System.out.println("Error while loading");
            } catch (ClassNotFoundException e) {
                System.out.println("Problem with class");
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }
        //if no file, set default settings.
        else {
            save(SIZE, FIRST_PLAYER, SECOND_PLAYER, PLAYER1COLOR, PLAYER2COLOR);
        }
    }

    /**
     * @param boardSize    The board size.
     * @param firstPlayer  The starting player.
     * @param secondPlayer The second player.
     * @param player1Color The color of player1.
     * @param player2Color The color of player2.
     * @throws IOException if failed to open file.
     */
    public void save(int boardSize, PlayerColor firstPlayer, PlayerColor secondPlayer, String player1Color,
                     String player2Color) throws IOException {
        //set the data.
        this.boardSize = boardSize;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.player1Color = player1Color;
        this.player2Color = player2Color;

        File file = new File(this.filename);
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        fos.close();
        oos.close();
    }
}