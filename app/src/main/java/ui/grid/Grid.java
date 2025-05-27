package ui.grid;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Board;
import model.Square.SquareColor;

public class Grid extends JPanel {
    private final Board board;

    public Grid(Board board) {
        this.board = board;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int pixelWidth = Math.ceilDiv(getWidth(), board.getWidth());
        int pixelHeight = Math.ceilDiv(getHeight(), board.getHeight());

        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                Color color = board.getColorAt(i, j) == SquareColor.BLACK ? Color.BLACK : Color.WHITE;
                g.setColor(color);
                g.fillRect(j * pixelWidth, i * pixelHeight, pixelWidth, pixelHeight);
            }
        }

        var ant = board.getAnt();
        int antRow = ant.getRow();
        int antCol = ant.getCol();

        g.setColor(Color.RED);
        g.fillRect(antCol * pixelWidth, antRow * pixelHeight, pixelWidth, pixelHeight);
    }
}
