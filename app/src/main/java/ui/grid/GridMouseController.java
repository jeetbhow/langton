package ui.grid;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;

public class GridMouseController extends MouseAdapter {
    private Grid grid;
    private Point lastPoint;

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
    public void mousePressed(MouseEvent e) {
        lastPoint = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point currPoint = e.getPoint();

        double dx = -(currPoint.x - lastPoint.x);
        double dy = -(currPoint.y - lastPoint.y);
        dx *= grid.getCamera().getWidth() * 0.001;
        dy *= grid.getCamera().getWidth() * 0.001;

        lastPoint = currPoint;

        grid.getCamera().pan(dx, dy);
        grid.repaint();
    }
}
