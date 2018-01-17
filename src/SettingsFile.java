import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SettingsFile implements java.io.Serializable {

    //class members
    private int boardSize;
    private PlayerColor firstPlayer;
    private PlayerColor secondPlayer;
    private String filename;

    public int getBoardSize() {
        return this.boardSize;
    }

    public PlayerColor getFirstPlayer() {
        return this.firstPlayer;
    }

    public PlayerColor getSecondPlayer() {
        return this.secondPlayer;
    }


    /**
     * Load table data from file.
     * Current table data is cleared.
     *
     * @throws IOException if openning file was not successful
     */
    public void load() throws IOException {
        File file = new File(this.filename);

        ObjectInputStream is = null;
        try {
            //opening and reaind the source file
            is = new ObjectInputStream(new FileInputStream(filename));
            SettingsFile temp = (SettingsFile) is.readObject();
            //import the loaded file data to the current SettingsFile
            this.boardSize = temp.getBoardSize();
            this.firstPlayer = temp.getFirstPlayer();
            this.secondPlayer = temp.getSecondPlayer();
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


    /**
     * Save table data to the specified file.
     *
     * @param filename the path for HighScores
     * @throws IOException if openning file was not successful
     */
    public void save(File filename) throws IOException {
        File file = new File(this.filename);
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        fos.close();
        oos.close();
    }
}