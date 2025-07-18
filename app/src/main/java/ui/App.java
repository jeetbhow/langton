package ui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Board;
import ui.sidebar.Sidebar;
import ui.bottomBar.BottomBar;
import ui.grid.Grid;

public class App extends JFrame implements SimulationController {
    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 1000;
    private static final int BOARD_WIDTH = 200;
    private static final int BOARD_HEIGHT = 200;
    private static final String[] RESOLUTIONS = new String[] {
        "50x50", "100x100", "200x200",
        "400x400", "800x800", "1600x1600",
    };

    private Timer timer;
    private BottomBar bottomBar;
    private Sidebar sidebar;
    private Board board;
    private Grid grid;
  
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new App();
        });
    }

    public App() {
        timer = new Timer(25, this::updateBoard);
        sidebar = new Sidebar(this, RESOLUTIONS);
        bottomBar = new BottomBar(this);
        board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
        grid = new Grid(board);

        add(sidebar, BorderLayout.WEST);
        add(bottomBar, BorderLayout.SOUTH);
        add(grid, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setVisible(true);
    }

    private void updateBoard(ActionEvent event) {
        board.update();
        grid.repaint();
    }

    @Override
    public void start() {
        timer.start();
        grid.setIsDrawing(true);
    }

    @Override
    public void pause() {
        timer.stop();
        grid.setIsDrawing(false);
    }

    @Override
    public void reset() {
        board.resetState();
        grid.repaint();
    }

    @Override
    public void changeDelay(int delay) {
        timer.setDelay(delay);
    }

    @Override
    public void changeResolution(String resolution) {
        String regex = "(\\d{2,4})x(\\d{2,4})";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(resolution);

        if (!matcher.find()) {
            throw new IllegalArgumentException("The resolution " + resolution + " is not supported.");
        }

        int width = Integer.parseInt(matcher.group(1));
        int height = Integer.parseInt(matcher.group(2));

        this.board = new Board(width, height);
        grid.setBoard(board);
    }

    @Override
    public void changeColor(Color color) {
        grid.setMouseColor(color);
    }
}
