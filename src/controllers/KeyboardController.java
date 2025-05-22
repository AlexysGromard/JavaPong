package controllers;

import utils.FPSCounter;
import utils.Vector2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * KeyboardController is a class used to control a paddle in a game based on
 * keyboard input. It extends KeyAdapter and implements PaddleController to
 * compute the paddle's position dynamically based on user key presses.
 *
 * The paddle movements are smoothed using acceleration effects, enabling a
 * fluid control experience. The paddle's movement is constrained within
 * defined bounds (`minY` and `maxY`), ensuring it does not go out of the playable area.
 */
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

    /**
     * Initializes a KeyboardController instance. This controller handles keyboard input
     * for controlling a paddle in the game.
     *
     * @param keyUpCode     The key code for moving the paddle up.
     * @param keyDownCode   The key code for moving the paddle down.
     * @param minY          The minimum Y-coordinate boundary for the paddle.
     * @param maxY          The maximum Y-coordinate boundary for the paddle.
     * @param paddleHeight  The height of the paddle.
     */
    public KeyboardController(int keyUpCode, int keyDownCode, int minY, int maxY, int paddleHeight) {
        this.keyUpCode = keyUpCode;
        this.keyDownCode = keyDownCode;
        this.minY = minY;
        this.maxY = maxY;
        this.paddleHeight = paddleHeight;
    }

    /**
     * Handles the event where a key is pressed. This method updates the state of
     * the directional keys (up or down) based on the event's key code.
     *
     * @param e the KeyEvent that represents the key press action
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == keyUpCode) {
            isUpPressed = true;
        } else if (e.getKeyCode() == keyDownCode) {
            isDownPressed = true;
        }
    }

    /**
     * Handles the event where a key is released. This method updates the state of
     * the directional keys (up or down) based on the event's key code.
     *
     * @param e the KeyEvent that represents the key release action
     */
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

    /**
     * Computes the new position of the paddle based on its current position and
     * the state of directional key presses (up or down). The position is adjusted
     * according to the current frame rate and ensures the paddle remains within
     * the predefined boundaries.
     *
     * @param position The current position of the paddle as a Vector2, where
     *                 position.x represents the x-coordinate and position.y
     *                 represents the y-coordinate.
     * @return A new Vector2 object representing the updated position of the paddle,
     *         with position.x remaining unchanged and position.y updated based
     *         on inputs and constraints.
     */
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
