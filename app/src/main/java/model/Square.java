package model;

public class Square {
    public enum SquareColor {
        BLACK,
        WHITE
    }

    SquareColor color;

    /**
     * Generate a Cell with the given state.
     * @param state The state you want to initialize the Cell with.
     */
    public Square(SquareColor state) {
        this.color = state;
    }

    /**
     * Generate a Cell with a default color of BLACK.
     */
    public Square() {
        this.color = SquareColor.BLACK;
    }

    /**
     * Set the color of the square.
     * @param state The state you want to set the cell to.
     */
    public void setColor(SquareColor state) {
        this.color = state;
    }

    /**
     * Toggle the color of the square.
     */
    public void toggleState() {
        color = color == SquareColor.BLACK ? SquareColor.WHITE : SquareColor.BLACK; 
    }

    /**
     * Get the color of the square.
     * @return The state.
     */
    public SquareColor getColor() {
        return color;
    }
}
