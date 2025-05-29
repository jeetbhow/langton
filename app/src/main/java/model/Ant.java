package model;

import java.awt.Color;

public class Ant {
    private int row;
    private int col;
    private Color color;
    private Color foregroundColor;
    private int direction;              // Range 0-3. Loops back around.

    // TODO Add documentation here.
    public Ant(int row, int col) {
        this(row, col, Color.WHITE);
    }

    // TODO Add documentation
    public Ant(int row, int col, Color color) {
        this.row = row;
        this.col = col;
        this.color = color;
        this.direction = 0;

        float[] hsb = Color.RGBtoHSB(color.getRed(),
            color.getGreen(),
            color.getBlue(),
            null
        );

        float complementaryHue = (hsb[0] + 0.5f) % 1.0f;
        foregroundColor = Color.getHSBColor(complementaryHue, hsb[1], hsb[2]);
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
     * Get the ant's color.
     * 
     * @return A Color object representing the color of the ant.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Get the foreground color of the ant.
     * 
     * @return A Color object representing the foreground color of the ant.
     */
    public Color getFgColor() {
        return foregroundColor;
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
