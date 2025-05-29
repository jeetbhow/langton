package ui.grid;

import java.util.List;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import model.Ant;
import model.Board;
import model.Camera;

public class Grid extends JPanel {
    private Point mousePosition;
    private Point lastMousePosition;
    private Color mouseColor;
    private Board board;
    private Camera camera;
    private boolean isUpdating;

    public Grid(Board board) {
        this.board = board;
        mouseColor = Color.WHITE;
        isUpdating = false;
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

    public void setBoard(Board board) {
        this.board = board;
        camera = new Camera(board.getWidth(), board.getHeight());
        repaint();
    }

    public Camera getCamera() {
        return camera;
    }

    public Point getLastMousePosition() {
        return lastMousePosition;
    }

    public void setLastMousePosition(Point p) {
        lastMousePosition = p;
    }

    public Point getMousePosition() {
        return mousePosition;
    }

    public void setMousePosition(Point p) {
        mousePosition = p;
    }

    public void setIsDrawing(boolean isDrawing) {
        this.isUpdating = isDrawing;
    }

    public boolean getIsUpdating() {
        return isUpdating;
    }

    public Color getMouseColor() {
        return mouseColor;
    }

    public void setMouseColor(Color color) {
        mouseColor = color;
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

                    Color color = board.getColorAt(row, col);
    
                    // x and y are screen coordinates.
                    img.setRGB(x, y, color.getRGB());
                }
            }

            List<Ant> ants = board.getAnts();
            for (var ant : ants) {
                int antY = ant.getRow() - (int) camera.getY();
                int antX = ant.getCol() - (int) camera.getX();

                if (isUpdating) {
                    if (antX >= 0 && antX < camera.getWidth() 
                        && antY >= 0 && antY < camera.getHeight()) {
                        img.setRGB(antX, antY, ant.getFgColor().getRGB());
                    } 
                } else {
                    if (antX >= 0 && antX < camera.getWidth() 
                        && antY >= 0 && antY < camera.getHeight()) {
                        img.setRGB(antX, antY, ant.getColor().getRGB());
                    } 
                }

            }

            if (mousePosition != null && !isUpdating) {
                int mx = mousePosition.x;
                int my = mousePosition.y;
                img.setRGB(mx, my, mouseColor.getRGB());
            }
        } finally {
            g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
            gImg.dispose();
        }
    }
}
