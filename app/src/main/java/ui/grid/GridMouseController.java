package ui.grid;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;

public class GridMouseController extends MouseAdapter {
    private Grid grid;

    public GridMouseController(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        double rotation = e.getPreciseWheelRotation();

        // zoom in by 10% of the board width.
        int boardWidth = grid.getBoard().getWidth();
        double zoomAmount = rotation * boardWidth * 0.02;

        grid.getCamera().zoom(zoomAmount);
        grid.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point p = e.getPoint(); 

        // We divide the point by the board's dimensions to transform
        // the point to board coordinates.
        double camWidth = grid.getCamera().getWidth();
        double camHeight = grid.getCamera().getHeight();

        // We compute the coordinates in screen-space.
        p.x = (int) (p.x * (camWidth / grid.getWidth()));
        p.y =  (int) (p.y * (camHeight / grid.getHeight()));

        grid.setMousePosition(p);
        grid.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point mousePosition = grid.getMousePosition();
        grid.setLastMousePosition(mousePosition);  

        if (!grid.getIsUpdating()) {
            grid.getBoard().createAnt(mousePosition.y, mousePosition.x, grid.getMouseColor());      
            grid.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point currPoint = e.getPoint();
        Point lastPoint = grid.getLastMousePosition();

        double camWidth = grid.getCamera().getWidth();
        double camHeight = grid.getCamera().getHeight();

        // We compute the coordinates in screen-space.
        currPoint.x = (int) (currPoint.x * (camWidth / grid.getWidth()));
        currPoint.y =  (int) (currPoint.y * (camHeight / grid.getHeight()));
        double dx = -(currPoint.x - lastPoint.x);
        double dy = -(currPoint.y - lastPoint.y);

        dx *= grid.getCamera().getWidth() * 0.005;
        dy *= grid.getCamera().getWidth() * 0.005;

        grid.setLastMousePosition(currPoint);
        grid.getCamera().pan(dx, dy);
        grid.repaint();
    }
}
