package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

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
        assertEquals(3, board.getWidth());
        assertEquals(3, board.getHeight());
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                assertEquals(Color.BLACK, board.getColorAt(i, j));
            }
        }
    }

    @Test
    void Ant_should_move_west_when_facing_north_on_black_square() {
        var board = new Board(3, 3);
        board.createAnt(1, 1);
        board.setAntDirection(0, 0);
        
        board.update();

        assertTrue(board.isAntAt(0, 1, 0));
        assertEquals(3, board.getAntDirection(0));
        assertEquals(Color.WHITE, board.getColorAt(1, 1));
    }

    @Test
    void Ant_should_move_east_when_facing_north_on_white_square() {
        var board = new Board(3, 3);
        board.createAnt(1, 1);
        board.setAntDirection(0, 0);
        board.setColorAt(1, 1, Color.WHITE);
        
        board.update();

        assertTrue(board.isAntAt(0, 1, 2));
        assertEquals(1, board.getAntDirection(0));
        assertEquals(Color.BLACK, board.getColorAt(1, 1));
    }

    @Test
    void Ant_should_move_north_when_facing_west_on_white_square() {
        var board = new Board(3, 3);
        board.createAnt(1, 1);
        board.setAntDirection(0, 3);
        board.setColorAt(1, 1, Color.WHITE);
        
        board.update();

        assertTrue(board.isAntAt(0, 0, 1));
        assertEquals(0, board.getAntDirection(0));
        assertEquals(Color.BLACK, board.getColorAt(1, 1));
    }

    @Test
    void Passing_direction_greater_than_3_should_throw() {
        var board = new Board(3, 3);
        assertThrows(IllegalArgumentException.class, () -> {
            board.setAntDirection(0, 4);
        });
    }

    @Test
    void Passing_negative_direction_should_throw() {
        var board = new Board(3, 3);
        assertThrows(IllegalArgumentException.class, () -> {
            board.setAntDirection(0, -1);
        });
    }
}
