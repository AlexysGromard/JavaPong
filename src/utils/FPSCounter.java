package utils;

public class FPSCounter {
    private int frames = 0;
    private static int fps = 0;
    private long lastTime = System.currentTimeMillis();

    // Appelé à chaque frame rendue
    public void frameRendered() {
        frames++;
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastTime >= 1000) { // 1 seconde écoulée
            fps = frames;
            frames = 0;
            lastTime = currentTime;
        }
    }

    public static int getFPS() {
        return fps;
    }
}