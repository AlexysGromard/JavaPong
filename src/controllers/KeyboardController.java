package controllers;

import utils.FPSCounter;
import utils.Vector2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardController extends KeyAdapter implements PaddleController {
    private final int keyUpCode;
    private final int keyDownCode;

    private boolean isUpPressed = false;
    private boolean isDownPressed = false;
    private double upPressTime = 0;
    private double downPressTime = 0;

    private final int minY;
    private final int maxY;
    private final int paddleHeight;

    private double internalY = 0; // Float position for the paddle

    public KeyboardController(int keyUpCode, int keyDownCode, int minY, int maxY, int paddleHeight) {
        this.keyUpCode = keyUpCode;
        this.keyDownCode = keyDownCode;
        this.minY = minY;
        this.maxY = maxY;
        this.paddleHeight = paddleHeight;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == keyUpCode) {
            isUpPressed = true;
        } else if (e.getKeyCode() == keyDownCode) {
            isDownPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == keyUpCode) {
            isUpPressed = false;
        } else if (e.getKeyCode() == keyDownCode) {
            isDownPressed = false;
        }
    }

    /**
     * Easing function for acceleration.
     * This function is used to create a smooth acceleration effect.
     *
     * @param t The time factor (0 to 1).
     * @return The eased value.
     */
    private double ease(double t) {
        return 1 - Math.pow(1 - t, 4);
    }

    @Override
    public Vector2 computePosition(Vector2 position) {
        if (internalY == 0) internalY = position.y;

        double deltaTime = 1.0 / FPSCounter.getFPS();

        // Managing the time of key presses
        if (isUpPressed) upPressTime += deltaTime;
        else upPressTime = 0;

        if (isDownPressed) downPressTime += deltaTime;
        else downPressTime = 0;

        // Calculating the speed
        double dy = 0;
        double baseSpeed = 720.0; // pixels/second max

        if (isUpPressed) {
            double t = Math.min(1.0, upPressTime);
            double factor = ease(t);
            dy -= baseSpeed * factor * deltaTime;
        }

        if (isDownPressed) {
            double t = Math.min(1.0, downPressTime);
            double factor = ease(t);
            dy += baseSpeed * factor * deltaTime;
        }

        internalY += dy;
        

        // Contraintes de bord
        if (internalY < minY) internalY = minY;
        if (internalY + paddleHeight > maxY) internalY = maxY - paddleHeight;

        return new Vector2(position.x, (int) internalY);
    }
}
