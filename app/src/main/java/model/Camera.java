package model;

public class Camera {
    private double x;
    private double y;
    private double width;
    private double height;

    public Camera(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Camera(double width, double height) {
        this.x = 0;
        this.y = 0;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void zoom(double amount) {
        double centerX = x + width / 2;
        double centerY = y + width / 2;

        double newWidth = width + amount;
        double newHeight = width + amount;

        x = centerX - newWidth / 2.0;
        y = centerY - newHeight / 2.0;
        width = newWidth;
        height = newHeight;
    }
}
