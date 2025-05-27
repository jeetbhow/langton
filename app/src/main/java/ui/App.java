package ui;

import java.awt.BorderLayout;

import javax.swing.SwingUtilities;
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
    private static final int GRID_WIDTH = 200;
    private static final int GRID_HEIGHT = 200;

    private Timer timer;
    private Sidebar sidebar;
    private Board board;
    private Grid grid;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new App();
        });
    }

    public App() {
        timer = new Timer(25, this::updateBoard);
        sidebar = new Sidebar(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setVisible(true);

        board = new Board(GRID_WIDTH, GRID_HEIGHT);
        board.createAnt(GRID_HEIGHT / 2, GRID_WIDTH / 2);
        grid = new Grid(board);

        add(sidebar, BorderLayout.WEST);
        add(grid, BorderLayout.CENTER);
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
    
    @Override
    public void changeDelay(int delay) {
        timer.setDelay(delay);
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
