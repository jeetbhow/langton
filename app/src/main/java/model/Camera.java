package model;

public class Camera {
    private double x;
    private double y;
    private double width;
    private double height;
    private double maxWidth;
    private double maxHeight;

    public Camera(double x, double y, double width, double height, double maxWidth, double maxHeight) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
    }

    public Camera(double width, double height) {
        this.x = 0;
        this.y = 0;
        this.width = width;
        this.height = height;
        this.maxWidth = width;
        this.maxHeight = height;
    }

    public void setMaxHeight(double maxHeight) {
        this.maxHeight = maxHeight;
    }

    public void setMaxWidth(double maxWidth) {
        this.maxWidth = maxWidth;
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
        double centreX = x + width / 2.0;
        double centreY = y + height / 2.0;

        double newWidth = Math.clamp(width + amount, 1, maxWidth);
        double newHeight = Math.clamp(height + amount, 1, maxHeight);

        x = Math.clamp(centreX - newWidth / 2.0, 0, maxWidth - newWidth);
        y = Math.clamp(centreY - newHeight / 2.0, 0, maxHeight - newHeight);

        width = newWidth;
        height = newHeight;
    }

    public void pan(double dx, double dy) {
        x = Math.clamp(x + dx, 0.0, maxWidth - width);
        y = Math.clamp(y + dy, 0.0, maxHeight - height);
    }
}
