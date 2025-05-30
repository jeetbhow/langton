package model;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

public class Board {
    private int width;
    private int height;
    private Square[][] squares;
    private List<Ant> ants;

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
        this.squares = createSquares(height, width);
        ants = new ArrayList<>();
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
     * Get the ants that are on the board.
     * 
     * @return A list of ants.
     */
    public List<Ant> getAnts() {
        return ants;
    }

    /**
     * Move an ant to the specified location.
     * 
     * @param i   The index of the ant that you want to retrieve.
     * @param row The row you want to move to.
     * @param col The column you want to move to.
     */
    public void moveAntTo(int i, int row, int col) {
        ants.get(i).moveTo(row, col);
    }

    /**
     * Determine whether or not an ant is at a given location.
     * 
     * @param i   The index of the ant.
     * @param row The row to query.
     * @param col The column to query.
     * @return True if the ant is at the location and false otherwise.
     */
    public boolean isAntAt(int i, int row, int col) {
        return ants.get(i).isAt(row, col);
    }

    /**
     * Create a white ant at the given location.
     * 
     * @param row The row you want to put the ant at.
     * @param col The column you want to put the ant at.
     */
    public void createAnt(int row, int col) {
        createAnt(row, col, Color.WHITE);
    }

    /**
     * Create an ant at the given location and assign its color.
     * 
     * @param row The row you want to put the ant.
     * @param col The column you want to put the ant.
     * @param color The color of the ant.
     */
    public void createAnt(int row, int col, Color color) {
        ants.add(new Ant(row, col, color));
    }

    /**
     * Get the direction an ant is facing.
     * 
     * @param i The index of the ant.
     * @return The direction that the ant is facing.
     */
    public int getAntDirection(int i) {
        return ants.get(i).getDirection();
    }

    /**
     * Set an ant's direction.
     * 
     * @param i The index of the ant.
     * @param direction The direction that you want to set the ant to.
     * @throws IllegalArgumentException If the user passes a direction outside the
     *                                  range of [0, 3]
     */
    public void setAntDirection(int i, int direction) {
        if (direction < 0 || direction > 3) {
            throw new IllegalArgumentException(
                    String.format(
                            "Invalid direction %d is outside the range [0, 3].", direction));
        }
        ants.get(i).setDirection(direction);
    }

    /**
     * Get the color of the square at the given location.
     * 
     * @param row The y-coordinate of the location.
     * @param col The x-coordinate of the location.
     * @return A Color object representing the color of the square at that location.
     */
    public Color getColorAt(int row, int col) {
        return squares[row][col].getColor();
    }

    /**
     * Set the color of the square at the given location.
     * 
     * @param row   The square's row
     * @param col   The square's column
     * @param color The color to set.
     */
    public void setColorAt(int row, int col, Color color) {
        squares[row][col].setColor(color);
    }

    /**
     * Reset the entire state of the board to black.
     */
    public void resetState() {
        this.squares = createSquares(height, width);
        this.ants.clear();
    }

    /**
     * Update the state of the board.
     */
    public void update() {
        for (var ant : ants) {
            int antRow = ant.getRow();
            int antCol = ant.getCol();
            Square square = squares[antRow][antCol];

            // Note: If the square's color matches the ant's color then we turn
            // CLOCKWISE. If it's black then we turn COUNTERCLOCKWISE. The way that
            // I've implemented this is to use integers that map onto the 4 cardinal
            // directions. The modulo operator allows you to wrap around. 
            // N -> 1
            // E -> 2
            // S -> 3
            // W -> 4
            // TODO In the future it might be better to use enums to represent directions
            // because this is a little hard to understand.
            int direction = ant.getDirection();
            if (square.getColor().equals(ant.getColor())) {
                ant.setDirection((direction + 1) % 4);
            } else {
                ant.setDirection((direction + 3) % 4);
            }

            // Move based on that direction
            switch (ant.getDirection()) {
                case 0 -> ant.moveTo((antRow + height - 1) % height, antCol);
                case 1 -> ant.moveTo(antRow, (antCol + 1) % width);
                case 2 -> ant.moveTo((antRow + 1) % height, antCol);
                case 3 -> ant.moveTo(antRow, (antCol + width - 1) % width);
                default ->
                    throw new IllegalStateException(
                            String.format(
                                    "%d is not a valid direction", ant.getDirection()));
            }

            // Flip the square that the ant was on.
            if (square.getColor().equals(Color.BLACK)) {
                setColorAt(antRow, antCol, ant.getColor());
            } else {
                setColorAt(antRow, antCol, Color.BLACK);
            }
        }
    }

    private Square[][] createSquares(int rows, int cols) {
        squares = new Square[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                squares[i][j] = new Square();
            }
        }
        return squares;
    }
}
