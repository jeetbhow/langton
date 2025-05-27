package ui.grid;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Board;
import model.Square.SquareColor;

public class Grid extends JPanel {
    private Board board;

    public Grid() {}

    public Grid(Board board) {
        this.board = board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    if (board == null) return;
    
    int panelWidth = getWidth();
    int panelHeight = getHeight();
    int boardWidth = board.getWidth();
    int boardHeight = board.getHeight();

    for (int i = 0; i < boardHeight; i++) {
        for (int j = 0; j < boardWidth; j++) {
            int x1 = (j * panelWidth) / boardWidth;
            int y1 = (i * panelHeight) / boardHeight;
            int x2 = ((j + 1) * panelWidth) / boardWidth;
            int y2 = ((i + 1) * panelHeight) / boardHeight;
            
            if (board.getColorAt(i, j) == SquareColor.BLACK) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.WHITE);
            }
            g.fillRect(x1, y1, x2 - x1, y2 - y1);
        }
    }

    var ant = board.getAnt();
    int antRow = ant.getRow();
    int antCol = ant.getCol();
    
    // Same calculation for ant position
    int antX1 = (antCol * panelWidth) / boardWidth;
    int antY1 = (antRow * panelHeight) / boardHeight;
    int antX2 = ((antCol + 1) * panelWidth) / boardWidth;
    int antY2 = ((antRow + 1) * panelHeight) / boardHeight;

    g.setColor(Color.RED);
    g.fillRect(antX1, antY1, antX2 - antX1, antY2 - antY1);
}
}
