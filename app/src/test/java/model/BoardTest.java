package model;

import org.junit.jupiter.api.Test;

import model.Square.SquareState;

import static org.junit.jupiter.api.Assertions.*;

// Test idea
// Initial position
/**
 * | * | * | * |
 * | * | A | * |
 * | * | * | * |
 */

// Final position after update
/**
 * | * | * | * |
 * | A | * | * |
 * | * | * | * |
 */

 // Remember:
 // White -> turn clockwise
 // Black -> turn counter-clockwise

public class BoardTest {

    @Test 
    void New_board_squares_should_be_black() {
        var board = new Board(3, 3);
        for (int i = 0; i < board.height; i++) {
            for (int j = 0; j < board.width; j++) {
                assertEquals(SquareState.BLACK, board.getStateAt(i, j));
            }
        }
    }

    @Test
    void Ant_should_move_west_when_facing_north_on_black_square() {
        var board = new Board(3, 3);
        board.setAnt(1, 1);
        board.setAntDirection(0);
        
        board.update();

        assertTrue(board.isAntAt(1, 0));
        assertEquals(3, board.getAntDirection());
        assertEquals(SquareState.WHITE, board.getStateAt(1, 1));
    }

    @Test
    void Ant_should_move_east_when_facing_north_on_white_square() {
        var board = new Board(3, 3);
        board.setAnt(1, 1);
        board.setAntDirection(0);
        board.flipStateAt(1, 1);
        
        board.update();

        assertTrue(board.isAntAt(1, 2));
        assertEquals(1, board.getAntDirection());
        assertEquals(SquareState.WHITE, board.getStateAt(1, 1));
    }

    @Test
    void Ant_should_move_north_when_facing_west_on_white_square() {
        var board = new Board(3, 3);
        board.setAnt(1, 1);
        board.setAntDirection(3);
        board.flipStateAt(1, 1);
        
        board.update();

        assertTrue(board.isAntAt(0, 1));
        assertEquals(0, board.getAntDirection());
        assertEquals(SquareState.BLACK, board.getStateAt(1, 1));
    }

    @Test
    void Passing_direction_greater_than_3_should_throw() {
        var board = new Board(3, 3);
        assertThrows(IllegalArgumentException.class, () -> {
            board.setAntDirection(4);
        });
    }

    @Test
    void Passing_negative_direction_should_throw() {
        var board = new Board(3, 3);
        assertThrows(IllegalArgumentException.class, () -> {
            board.setAntDirection(-1);
        });
    }
}
