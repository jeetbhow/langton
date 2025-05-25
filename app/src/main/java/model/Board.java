package model;

import model.Square.SquareState;

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
        
    }

    /**
     * Get the state of the square at the given location.
     * @param row The y-coordinate of the location.
     * @param col The x-coordinate of the location.
     * @return A SquareState enum indicating the color of the square at that location.
     */
    public SquareState getStateAt(int row, int col) {
        // TODO
        return SquareState.BLACK;
    }

    /**
     * Flip the state of a particular square on the board.
     * @param row The y-coordinate of the square.
     * @param col The x-coordinate of the square.
     * @param state The state that you want to set the square to.
     */
    public void flipStateAt(int row, int col) {
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
     * @param row The y-coordinate of the ant.
     * @param col The x-coordinate of the ant.
     */
    public void setAnt(int row, int col) {
        // TODO
    }

    /**
     * Set the ant's direction.The ant's direction is represented
     * as an integer fromm 0-3, where 0 maps onto north, and subsequent integers
     * are created by moving clockwise.
     * @param direction The direction you want the ant to face.
     * @throws IllegalArgumentException If an integer outside the range of [0, 3] is passed in.
     */
    public void setAntDirection(int direction) throws IllegalArgumentException {
        // TODO
    }

    /**
     * Returns true or false depending on whether the ant is at the position.
     * @param row The y-coordinate of the position.
     * @param col The x-coordinate of the position.
     * @return A boolean value indicating whether or not the ant is at the position.
     */
    public boolean isAntAt(int row, int col) {
        // TODO
        return false;
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
