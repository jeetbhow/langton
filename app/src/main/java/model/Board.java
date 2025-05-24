package model;

public class Board {
    int width;
    int height;
    int antX;
    int antY;
    byte direction;          // Range 0-3. Loops back around. 
    Square[] squares;

    /**
     * Create a Board with the given dimensions. Each square in the board is
     * by default initialized to black.
     * @param width The width of the Board.
     * @param height The height of the Board.
     */
    public Board(int width, int height) {
        // TODO
    }

    /**
     * Flip the state of a particular square on the board.
     * @param x The x-coordinate of the square.
     * @param y The y-coordinate of the square.
     * @param state The state that you want to set the square to.
     */
    public void flipStateAt(int x, int y) {
        // TODO
    }

    /**
     * Reset the entire state of the board to black.
     */
    public void resetState() {
        // TODO
    }

    /**
     * Set the location of the ant.
     * @param x The x-coordinate of the ant.
     * @param y The y-coordinate of the ant.
     */
    public void setAnt(int x, int y) {
        // TODO
    }

    /**
     * Get the ant's current direction. The ant's direction is represented
     * as an integer fromm 0-3, where 0 maps onto north, and subsequent integers
     * are created by moving clockwise.
     * @return An integer from 0-3 representing the ant's current direction.
     */
    public int getAntDirection() {
        // TODO
        return 0;
    }

    /**
     * Update the state of the board.
     */
    public void update() {
        // TODO
    }   
}
