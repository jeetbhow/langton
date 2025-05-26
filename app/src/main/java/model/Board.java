package model;

import model.Square.SquareColor;

public class Board {
    private int width;
    private int height;
    private Square[][] squares;
    private Ant ant;

    /**
     * Create a Board with the given dimensions. Each square in the board is
     * by default initialized to black.
     * 
     * @param width  The width of the Board.
     * @param height The height of the Board.
     */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        squares = new Square[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                squares[i][j] = new Square();
            }
        }
    }

    /**
     * Get the width of the board.
     * 
     * @return The width of the board.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height of the board.
     * 
     * @return The height of the board.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the ant.
     * 
     * @return The Ant object attached to this board.
     */
    public Ant getAnt() {
        return ant;
    }

    /**
     * Move the ant to the specified location.
     * 
     * @param row The row you want to move to.
     * @param col The column you want to move to.
     */
    public void moveAntTo(int row, int col) {
        ant.moveTo(row, col);
    }

    /**
     * Determine whether or not the ant is at the given position.
     * 
     * @param row The row to query.
     * @param col The column to query.
     * @return True if the ant is at the location and false otherwise.
     */
    public boolean isAntAt(int row, int col) {
        return ant.isAt(row, col);
    }

    /**
     * Create an ant at the given location. If an ant already exists then nothing happens.
     * 
     * @param row The row you want to put the ant at.
     * @param col The column you want to put the ant at.
     */
    public void createAnt(int row, int col) {
        if (ant == null) {
            ant = new Ant(row, col);
        }
    }

    /**
     * Get the direction the ant is facing.
     * @return
     */
    public int getAntDirection() {
        return ant.getDirection();
    }

    /**
     * Set the ant's direction.
     * 
     * @param direction The direction that you want to set the ant to.
     * @throws IllegalArgumentException If the user passes a direction outside the
     *                                  range of [0, 3]
     */
    public void setAntDirection(int direction) {
        if (direction < 0 || direction > 3) {
            throw new IllegalArgumentException(
                    String.format(
                            "Invalid direction %d is outside the range [0, 3].", direction));
        }
        ant.setDirection(direction);
    }

    /**
     * Get the state of the square at the given location.
     * 
     * @param row The y-coordinate of the location.
     * @param col The x-coordinate of the location.
     * @return A SquareState enum indicating the color of the square at that
     *         location.
     */
    public SquareColor getColorAt(int row, int col) {
        return squares[row][col].getColor();
    }

    /**
     * Flip the state of a particular square on the board.
     * 
     * @param row   The y-coordinate of the square.
     * @param col   The x-coordinate of the square.
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
                if (squares[i][j].getColor() == SquareColor.WHITE) {
                    flipStateAt(i, j);
                }
            }
        }
    }

    /**
     * Update the state of the board.
     * 
     * @throws IllegalStateException If there is no ant currently on the board.
     */
    public void update() {
        if (ant == null) {
            throw new IllegalStateException("There must be an ant on the board to call update.");
        }

        int antRow = ant.getRow();
        int antCol = ant.getCol();
        Square square = squares[antRow][antCol];
        SquareColor color = square.getColor();

        // Flip the square that the ant is on.
        flipStateAt(antRow, antCol);

        // Change the ant direction.
        int direction = ant.getDirection();
        if (color == SquareColor.WHITE) {
            ant.setDirection((direction + 1) % 4);
        } else {
            ant.setDirection((direction + 3) % 4);
        }

        // Move based on that direction
        switch (ant.getDirection()) {
            case 0 -> ant.moveTo(antRow - 1, antCol);
            case 1 -> ant.moveTo(antRow, antCol + 1);
            case 2 -> ant.moveTo(antRow + 1, antCol);
            case 3 -> ant.moveTo(antRow, antCol - 1);
            default ->
                throw new IllegalStateException(
                        String.format(
                                "%d is not a valid direction", ant.getDirection()));
        }
    }
}
