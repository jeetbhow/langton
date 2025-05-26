package ui.sidebar;

import java.awt.Dimension;

import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Sidebar extends JPanel {
    private JButton start = new JButton("Start");
    private JButton pause = new JButton("Pause");

    private Timer timer;

    public Sidebar(Timer timer) {
        this.timer = timer;

        Dimension preferredSize = getPreferredSize();
        setPreferredSize(new Dimension(250, preferredSize.height));

        start.addActionListener(e -> timer.start());
        pause.addActionListener(e -> timer.stop());

        add(start);
        add(pause);
    }
}
