package ui.grid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;

import javax.swing.JPanel;

import model.Board;
import model.Camera;
import model.Square.SquareColor;

public class Grid extends JPanel {
    private Board board;
    private Camera camera;

    public Grid(Board board) {
        this.board = board;
        camera = new Camera(board.getWidth(), board.getHeight());

        addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                double zoomAmount = e.getPreciseWheelRotation();
                camera.zoom(zoomAmount * 8);
                repaint();
            }
        });
    }

    public void setBoard(Board board) {
        this.board = board;
        camera = new Camera(board.getWidth(), board.getHeight());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (board == null) return;
        
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        double camWidth = camera.getWidth();
        double camHeight = camera.getHeight();

        double camX = camera.getX();
        double camY = camera.getY();

        for (int i = 0; i < camHeight; i++) {
            for (int j = 0; j < camWidth; j++) {
                double boardX = camX + j;
                double boardY = camY + i;

                if (boardX < 0 || boardX >= board.getWidth() ||
                        boardY < 0 || boardY >= board.getHeight()) {
                    continue; 
                }

                int x1 = (j * panelWidth) / (int) camWidth;
                int y1 = (i * panelHeight) / (int) camHeight;
                int x2 = ((j + 1) * panelWidth) / (int) camWidth;
                int y2 = ((i + 1) * panelHeight) / (int) camHeight;
                
                if (board.getColorAt((int) boardY, (int) boardX) == SquareColor.BLACK) {
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

        int relativeAntRow = antRow - (int) camera.getY();
        int relativeAntCol = antCol - (int) camera.getX();
        
        int antX1 = (relativeAntCol * panelWidth) / (int) camWidth;
        int antY1 = (relativeAntRow * panelHeight) / (int) camHeight;
        int antX2 = ((relativeAntCol + 1) * panelWidth) / (int) camWidth;
        int antY2 = ((relativeAntRow + 1) * panelHeight) / (int) camHeight;

        g.setColor(Color.RED);
        g.fillRect(antX1, antY1, antX2 - antX1, antY2 - antY1);
    }
}
