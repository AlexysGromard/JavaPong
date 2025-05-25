import javax.swing.*;

import utils.FPSCounter;
import windows.Window;

/**
 * The Javapong class is the entry point for the Java Pong game.
 * It manages the main application loop, handles rendering at a fixed frame rate,
 * and updates the game's FPS counter.
 */
public class Javapong{
    public static final int FPS = 60;
    static final long FRAME_TIME = 1000000000 / FPS; // In nanoseconds

    /**
     * The main method serves as the entry point for the application. It initializes
     * the game window, sets up the frame rendering loop, and manages frame rate control.
     *
     * @param args command-line arguments passed to the application.
     * @throws InterruptedException if the thread sleep is interrupted during execution.
     */
    public static void main(String[] args) throws InterruptedException {
        
        System.setProperty("sun.java2d.opengl", "true"); /* pour animation fluide */

        Window win = new Window() ;
        win.setVisible(true);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FPSCounter fpsCounter = new FPSCounter();

        long lastTime = System.nanoTime();
        long currentTime;
        long delta;

        while (true) {
            currentTime = System.nanoTime();
            delta = currentTime - lastTime;

            if (delta >= FRAME_TIME) {
                win.repaint();
                fpsCounter.frameRendered();  // Indicate that a frame has been rendered
                lastTime = currentTime;
            } else {
                try {
                    Thread.sleep((FRAME_TIME - delta) / 1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}