package ui;

import java.awt.BorderLayout;

import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.ActionEvent;

import model.Board;
import ui.sidebar.Sidebar;
import ui.grid.Grid;

public class App extends JFrame implements SimulationController {
    private static final int SCREEN_WIDTH = 1280;
    private static final int SCREEN_HEIGHT = 720;
    private static final int GRID_WIDTH = 250;
    private static final int GRID_HEIGHT = 250;

    private Board board = new Board(GRID_WIDTH, GRID_HEIGHT);
    private Timer timer = new Timer(1, this::updateBoard);
    
    private Sidebar sidebar = new Sidebar(this);
    private Grid grid = new Grid(board);

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        var app = new App();
        app.run();
    }

    private void run() {
        add(sidebar, BorderLayout.WEST);
        add(grid, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setVisible(true);
    }

    @Override
    public void start() {
        timer.start();
    }

    @Override
    public void pause() {
        timer.stop();
    }

    @Override
    public void reset() {
        board.resetState();
        board.moveAntTo(GRID_HEIGHT / 2, GRID_WIDTH / 2);
        grid.repaint();
    }
    
    private void updateBoard(ActionEvent event) {
        try {
            board.update();
            grid.repaint();
        } catch (IndexOutOfBoundsException e) {
            timer.stop();
        }
    }
}
