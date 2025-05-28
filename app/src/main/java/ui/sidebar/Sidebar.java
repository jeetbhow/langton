package ui.sidebar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;

import ui.SimulationController;

public class Sidebar extends JPanel {
    private SimulationController controller;
    private JPanel controls;

    public Sidebar(SimulationController controller, String[] resolutions) {
        this.controller = controller;
        controls = new JPanel();
        controls.setLayout(new FlowLayout(FlowLayout.LEFT));

        setLayout(new BorderLayout(0, 10));
        setPreferredSize(new Dimension(250, getPreferredSize().height));

        setupTimelineBtns();
        setupDelaySlider();
        setupResolutionDropdown(resolutions);

        add(controls, BorderLayout.CENTER);
    }

    private void setupTimelineBtns() {
        Box btnBox = Box.createHorizontalBox();
        var startBtn = new JButton("Start");
        var pauseBtn = new JButton("Pause");
        var resetBtn = new JButton("Reset");

        startBtn.addActionListener(e -> controller.start());
        pauseBtn.addActionListener(e -> controller.pause());
        resetBtn.addActionListener(e -> controller.reset());

        btnBox.setBorder(new EmptyBorder(5, 0, 0, 0));
        btnBox.add(Box.createHorizontalGlue());
        btnBox.add(startBtn);
        btnBox.add(pauseBtn);
        btnBox.add(resetBtn);
        btnBox.add(Box.createHorizontalGlue());
        add(btnBox, BorderLayout.NORTH);
    }

    private void setupDelaySlider() {
        Box delaySliderBox = Box.createHorizontalBox();
        var delayLabel = new JLabel("Delay");
        var delaySlider = new JSlider(0, 50);
        delaySlider.setMajorTickSpacing(10);
        delaySlider.addChangeListener(e -> controller.changeDelay(delaySlider.getValue()));

        delaySliderBox.add(delayLabel);
        delaySliderBox.add(delaySlider);
        controls.add(delaySliderBox);
    }

    private void setupResolutionDropdown(String[] resolutions) {
        Box resolutionBox = Box.createHorizontalBox();
        var resolutionLabel = new JLabel("Resolution");
        var resolutionDropDown = new JComboBox<>(resolutions);
        resolutionDropDown.addActionListener(e -> { 
            String selectedResolution = resolutionDropDown.getSelectedItem().toString();
            controller.changeResolution(selectedResolution);
        });
    
        resolutionBox.add(resolutionLabel);
        resolutionBox.add(resolutionDropDown);
        controls.add(resolutionBox);
    }
}
