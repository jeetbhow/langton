package model;

import model.Square.SquareState;

public class Board {
    int width;
    int height;
    int antRow;
    int antCol;
    int antDirection;          // Range 0-3. Loops back around. 
    Square[][] squares;

    /**
     * Create a Board with the given dimensions. Each square in the board is
     * by default initialized to black.
     * @param width The width of the Board.
     * @param height The height of the Board.
     */
    public Board(int width, int height) {
        squares = new Square[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                squares[i][j] = new Square();
            }
        }
    }

    /**
     * Get the state of the square at the given location.
     * @param row The y-coordinate of the location.
     * @param col The x-coordinate of the location.
     * @return A SquareState enum indicating the color of the square at that location.
     */
    public SquareState getStateAt(int row, int col) {
        return squares[row][col].getState();
    }

    /**
     * Flip the state of a particular square on the board.
     * @param row The y-coordinate of the square.
     * @param col The x-coordinate of the square.
     * @param state The state that you want to set the square to.
     */
    public void flipStateAt(int row, int col) {
        squares[row][col].toggleState();
    }

    /**
     * Reset the entire state of the board to black.
     */
    public void resetState() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (squares[i][j].getState() == SquareState.WHITE) {
                    flipStateAt(i, j);
                }
            }
        }
    }

    /**
     * Set the location of the ant.
     * @param row The y-coordinate of the ant.
     * @param col The x-coordinate of the ant.
     */
    public void setAnt(int row, int col) {
        antRow = row;
        antCol = col;
    }

    /**
     * Set the ant's direction.The ant's direction is represented
     * as an integer fromm 0-3, where 0 maps onto north, and subsequent integers
     * are created by moving clockwise.
     * @param direction The direction you want the ant to face.
     * @throws IllegalArgumentException If an integer outside the range of [0, 3] is passed in.
     */
    public void setAntDirection(int direction) throws IllegalArgumentException {
        if (direction > 3 || direction < 0) {
            throw new IllegalArgumentException("Direction should be between 0 and 3.");
        }
        this.antDirection = direction;
    }

    /**
     * Returns true or false depending on whether the ant is at the position.
     * @param row The y-coordinate of the position.
     * @param col The x-coordinate of the position.
     * @return A boolean value indicating whether or not the ant is at the position.
     */
    public boolean isAntAt(int row, int col) {
        return antRow == row && antCol == col;
    }

    /**
     * Get the ant's current direction. The ant's direction is represented
     * as an integer fromm 0-3, where 0 maps onto north, and subsequent integers
     * are created by moving clockwise.
     * @return An integer from 0-3 representing the ant's current direction.
     */
    public int getAntDirection() {
        return antDirection;
    }

    /**
     * Update the state of the board.
     */
    public void update() {
        Square square = squares[antRow][antCol];
        SquareState color = square.getState();

        // Flip the square that the ant is on.
        flipStateAt(antRow, antCol);

        // Change the ant direction.
        if (color == SquareState.WHITE) {
            antDirection = (antDirection + 1) % 4;
        } else {
            antDirection = (antDirection + 3) % 4;
        }

        // Move based on that direction
        switch (antDirection) {
            case 0 -> {
                antRow--;
            }
            case 1 -> {
                antCol++;
            }
            case 2 -> {
                antRow++;
            } 
            case 3 -> {
                antCol--;
            }
        }
    }   
}
