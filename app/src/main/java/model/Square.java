package model;

import java.awt.Color;

public class Square {
    private Color color;

    /**
     * Create a Square with a default color of BLACK.
     */
    public Square() {
        this(Color.BLACK);
    }

    /**
     * Create a Square with the given color.
     * @param color The color of the Square.
     */
    public Square(Color color) {
        this.color = color;
    }

    /**
     * Set the color of the square.
     * 
     * @param color The color you want to set the square to.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Get the color of the square.
     * 
     * @return A Color object corresponding to the color of the square.
     */
    public Color getColor() {
        return color;
    }
}
