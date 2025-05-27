package ui.sidebar;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import ui.SimulationController;

public class Sidebar extends JPanel {
    public Sidebar(SimulationController controller) {
        setPreferredSize(new Dimension(250, getPreferredSize().height));

        var startBtn = new JButton("Start");
        var pauseBtn = new JButton("Pause");
        var resetBtn = new JButton("Reset");

        startBtn.addActionListener(e -> controller.start());
        pauseBtn.addActionListener(e -> controller.pause());
        resetBtn.addActionListener(e -> controller.reset());

        var delayLabel = new JLabel("Delay");
        var delaySlider = new JSlider(0, 50);
        delaySlider.setMajorTickSpacing(10);
        delaySlider.addChangeListener(e -> controller.changeDelay(delaySlider.getValue()));

        var delaySliderBox = Box.createHorizontalBox();
        delaySliderBox.add(delayLabel);
        delaySliderBox.add(delaySlider);

        add(startBtn);
        add(pauseBtn);
        add(resetBtn);
        add(delaySliderBox);
    }
}
