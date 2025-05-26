package model;

public class Ant {
    private int row;
    private int col;
    private int direction; // Range 0-3. Loops back around.

    public Ant(int row, int col) {
        this.row = row;
        this.col = col;
        this.direction = 0;
    }

    /**
     * Set the location of the ant.
     * 
     * @param row The y-coordinate of the ant.
     * @param col The x-coordinate of the ant.
     */
    public void moveTo(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Get the row that the ant is currently on.
     * 
     * @return The current row.
     */
    public int getRow() {
        return row;
    }

    /**
     * Get the column that the ant is currently on.
     * 
     * @return The current column.
     */
    public int getCol() {
        return col;
    }

    /**
     * Returns true or false depending on whether the ant is at the position.
     * 
     * @param row The y-coordinate of the position.
     * @param col The x-coordinate of the position.
     * @return A boolean value indicating whether or not the ant is at the position.
     */
    public boolean isAt(int row, int col) {
        return this.row == row && this.col == col;
    }

    /**
     * Get the ant's current direction. The ant's direction is represented
     * as an integer fromm 0-3, where 0 maps onto north, and subsequent integers
     * are created by moving clockwise.
     * 
     * @return An integer from 0-3 representing the ant's current direction.
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Set the ant's direction.
     * 
     * @param direction The direction you want the ant to face.
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

}
