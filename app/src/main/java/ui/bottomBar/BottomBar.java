package ui.bottomBar;

import javax.swing.JPanel;
import javax.swing.JColorChooser;

import ui.SimulationController;

public class BottomBar extends JPanel {
     private SimulationController controller;

    public BottomBar(SimulationController controller) {
        this.controller = controller;
        setupColorPicker();
    }

    private void setupColorPicker() {
        var colorPicker = new JColorChooser();
        
        colorPicker.getSelectionModel().addChangeListener(e -> {
            controller.changeColor(colorPicker.getColor());
        });

        add(colorPicker);
    }
}
