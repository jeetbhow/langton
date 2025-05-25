package model;

public class Square {
    public enum SquareState {
        BLACK,
        WHITE
    }

    SquareState state;

    /**
     * Generate a Cell with the given state.
     * @param state The state you want to initialize the Cell with.
     */
    public Square(SquareState state) {
        this.state = state;
    }

    /**
     * Generate a Cell with a default state of BLACK.
     */
    public Square() {
        this.state = SquareState.BLACK;
    }

    /**
     * Set the state of the cell.
     * @param state The state you want to set the cell to.
     */
    public void setState(SquareState state) {
        this.state = state;
    }

    /**
     * Toggle the state of the square.
     */
    public void toggleState() {
        state = state == SquareState.BLACK ? SquareState.WHITE : SquareState.BLACK; 
    }

    /**
     * Get the state of this cell.
     * @return The state.
     */
    public SquareState getState() {
        return state;
    }
}
