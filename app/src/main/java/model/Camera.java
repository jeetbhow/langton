package model;

public class Camera {
    private double x;
    private double y;
    private double width;
    private double height;
    private double maxWidth;
    private double maxHeight;

    public Camera(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.maxHeight = height;
        this.maxWidth = width;
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
        // 1 ️⃣ find the current centre of the view in board coordinates
        double centreX = x + width / 2.0;
        double centreY = y + height / 2.0; // ← use height, not width

        // 2 ️⃣ work out the new view size, clamped to the board bounds
        double newWidth = Math.clamp(width + amount, 1, maxWidth);
        double newHeight = Math.clamp(height + amount, 1, maxHeight);

        // 3 ️⃣ put the centre back where it was
        // Valid range for the top-left corner is [0 , board-size – view-size]
        x = Math.clamp(centreX - newWidth / 2.0, 0, maxWidth - newWidth);
        y = Math.clamp(centreY - newHeight / 2.0, 0, maxHeight - newHeight);

        width = newWidth;
        height = newHeight;
    }
}
