package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class CameraTest {
    private Camera camera;

    @Test
    public void Camera_dimensions_half_on_zoom_in_by_half() {
        camera = new Camera(100.0, 100.0);
        
        camera.zoom(-50.0);

        assertEquals(50.0, camera.getWidth());
        assertEquals(50.0, camera.getHeight());
        assertEquals(25.0, camera.getX());
        assertEquals(25.0, camera.getY());
    }

    @Test
    public void Camera_dimensions_double_on_zoom_out_by_factor_of_2() {
        camera = new Camera(25.0, 25.0, 50.0, 50., 100.0, 100.0);

        camera.zoom(50);

        assertEquals(100.0, camera.getWidth());
        assertEquals(100.0, camera.getHeight());
        assertEquals(0, camera.getX());
        assertEquals(0, camera.getY());
    }

    @Test
    public void Camera_pans_to_left_edge_when_dx_is_negative_of_x() {
        camera = new Camera(25.0, 25.0, 50.0, 50., 100.0, 100.0);

        camera.pan(-25, 0);

        assertEquals(0, camera.getX());
        assertEquals(25, camera.getY());
    }

    @Test
    public void Camera_pans_to_top_edge_when_dy_is_negative_of_y() {
        camera = new Camera(25.0, 25.0, 50.0, 50., 100.0, 100.0);

        camera.pan(0, -25);

        assertEquals(25, camera.getX());
        assertEquals(0, camera.getY());
    }

    @Test
    public void Camera_clamps_when_trying_to_pan_past_left_edge() {
        camera = new Camera(25.0, 25.0, 50.0, 50., 100.0, 100.0);

        camera.pan(-26, 0);

        assertEquals(0, camera.getX());
        assertEquals(25, camera.getY());
    }

    @Test
    public void Camera_clamps_when_trying_to_pan_past_top_edge() {
        camera = new Camera(25.0, 25.0, 50.0, 50., 100.0, 100.0);

        camera.pan(0, -26);

        assertEquals(25, camera.getX());
        assertEquals(0, camera.getY());  
    }
}
