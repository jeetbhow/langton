package ui;

import java.awt.Color;

public interface SimulationController {
    void start();
    void pause();
    void reset();
    void changeDelay(int delay);
    void changeResolution(String resolution);
    void changeColor(Color color);
}
