package utils;

/**
 * The FPSCounter class is responsible for measuring and tracking the number
 * of frames rendered per second (FPS) in a rendering loop. It provides functionality
 * to update the frame count and retrieve the calculated FPS value.
 *
 * The FPS is calculated by counting the frames rendered within a time span
 * of one second and resetting the count after every second.
 */
public class FPSCounter {
    private int frames = 0;
    private static int fps = 0;
    private long lastTime = System.currentTimeMillis();

    /**
     * Updates the frame count and recalculates the frames per second (FPS) if a second
     * has elapsed since the last calculation. This method is intended to be called
     * every time a frame is rendered in the rendering loop.
     *
     * The FPS calculation works by incrementing a frame counter on each call and
     * checking if one second has passed since the last update. When a second elapses,
     * the method updates the FPS value with the current frame count, resets the frame
     * counter, and updates the timestamp of the last calculation.
     */
    public void frameRendered() {
        frames++;
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastTime >= 1000) { // 1 second has passed
            fps = frames;
            frames = 0;
            lastTime = currentTime;
        }
    }

    public static int getFPS() {
        return fps;
    }
}