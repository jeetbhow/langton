package ui.grid;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import model.Ant;
import model.Board;
import model.Camera;
import model.Square.SquareColor;

public class Grid extends JPanel {
    private Board board;
    private Camera camera;

    public Grid(Board board) {
        this.board = board;
        camera = new Camera(board.getWidth(), board.getHeight());

        var controller = new GridMouseController(this);

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouseListener(controller);
        addMouseWheelListener(controller);
        addMouseMotionListener(controller);
    }

    public Board getBoard() {
        return board;
    }

    public Camera getCamera() {
        return camera;
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

        BufferedImage img = new BufferedImage(
            (int) camera.getWidth(),
            (int) camera.getHeight(),
            BufferedImage.TYPE_INT_RGB
        );

        Graphics2D gImg = (Graphics2D) img.createGraphics();
        try {
            gImg.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR
            );

            for (int y = 0; y < camera.getHeight(); y++) {
                for (int x = 0; x < camera.getWidth(); x++) {
                    // These are board coordinates.
                    int row = (int) camera.getY() + y;
                    int col = (int) camera.getX() + x;

                    if (row < 0 || row >= board.getHeight() ||
                        col < 0 || col >= board.getWidth()) {
                            continue;
                    }

                    Color color;
                    if (board.getColorAt(row, col) == SquareColor.BLACK) {
                        color = Color.BLACK;
                    } else {
                        color = Color.WHITE;
                    }

                    // x and y are screen coordinates.
                    img.setRGB(x, y, color.getRGB());
                }
            }

            Ant ant = board.getAnt();
            int antY = ant.getRow() - (int) camera.getY();
            int antX = ant.getCol() - (int) camera.getX();

            if (antX >= 0 && antX < camera.getWidth() 
                && antY >= 0 && antY < camera.getHeight()) {
                img.setRGB(antX, antY, Color.RED.getRGB());
            } 

            g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        } finally {
            gImg.dispose();
        }
    }
}
