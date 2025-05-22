import javax.swing.*;

import utils.FPSCounter;
import windows.Window;

public class Javapong{
    public  static final int FPS = 60;
    static final long FRAME_TIME = 1000000000 / FPS; // In nanoseconds

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
