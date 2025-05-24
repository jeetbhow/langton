package model;

public class Cell {
    public enum CellState {
        BLACK,
        WHITE
    }

    CellState state;

    /**
     * Generate a Cell with the given state.
     * @param state The state you want to initialize the Cell with.
     */
    public Cell(CellState state) {
        this.state = state;
    }

    /**
     * Generate a Cell with a default state of BLACK.
     */
    public Cell() {
        this.state = CellState.BLACK;
    }

    /**
     * Set the state of the cell.
     * @param state The state you want to set the cell to.
     */
    public void setState(CellState state) {
        this.state = state;
    }

    /**
     * Get the state of this cell.
     * @return The state.
     */
    public CellState getState() {
        return state;
    }
}
