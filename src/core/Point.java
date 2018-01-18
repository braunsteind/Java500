package core;

public class Point {

    private int row;
    private int col;

    /**
     * Default constructor.
     * Set the point to (-1,-1).
     */
    public Point() {
    }

    /**
     * Constructor.
     *
     * @param ro     The row of the point.
     * @param column The column of the point.
     */
    public Point(int ro, int column) {
        this.row = ro;
        this.col = column;
    }

    /**
     * Copy constructor.
     *
     * @param p the point to copy
     */
    public Point(Point p) {
        this.row = p.row;
        this.col = p.col;
    }

    /**
     * Get the point row.
     *
     * @return The point row.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Get the point column.
     *
     * @return The point column.
     */
    public int getColumn() {
        return this.col;
    }

    /**
     * Set the point row.
     *
     * @param r The row.
     */
    public void setRow(int r) {
        this.row = r;
    }

    /**
     * Set the point column.
     *
     * @param c The column.
     */
    public void setCol(int c) {
        this.col = c;
    }

    /**
     * Set point.
     *
     * @param r The row.
     * @param c The column.
     */
    public void setPoint(int r, int c) {
        this.row = r;
        this.col = c;
    }

    /**
     * Equals override.
     *
     * @param p The other point.
     * @return True if equals, false if not.
     */
    public boolean equals(Point p) {
        if (p.row == this.row && p.col == this.col) {
            return true;
        }
        return false;
    }
}

