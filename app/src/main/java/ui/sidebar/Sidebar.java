package ui.sidebar;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.SimulationController;

public class Sidebar extends JPanel {
    private JButton startBtn = new JButton("Start");
    private JButton pauseBtn = new JButton("Pause");
    private JButton resetBtn = new JButton("Reset");

    public Sidebar(SimulationController controller) {
        Dimension preferredSize = getPreferredSize();
        setPreferredSize(new Dimension(250, preferredSize.height));

        startBtn.addActionListener(e -> controller.start());
        pauseBtn.addActionListener(e -> controller.pause());
        resetBtn.addActionListener(e -> controller.reset());

        add(startBtn);
        add(pauseBtn);
        add(resetBtn);
    }
}
