package controllers;

import utils.FPSCounter;
import utils.Vector2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyBoardController extends KeyAdapter implements PaddleController {
    private final int keyUpCode;
    private final int keyDownCode;
    private boolean isUpPressed = false;
    private boolean isDownPressed = false;

    private final int minY;
    private final int maxY;
    private final int paddleHeight;

    private double internalY = 0; // Float position for the paddle

    public KeyBoardController(int keyUpCode, int keyDownCode, int minY, int maxY, int paddleHeight) {
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

    @Override
    public Vector2 computePosition(Vector2 position) {
        if (internalY == 0) internalY = position.y;

        double speed = 720.0 / FPSCounter.getFPS(); // 720.0 pixels/second
        double dy = 0;

        if (isUpPressed) dy -= speed;
        if (isDownPressed) dy += speed;

        internalY += dy;

        // Contraintes de bord
        if (internalY < minY) internalY = minY;
        if (internalY + paddleHeight > maxY) internalY = maxY - paddleHeight;

        return new Vector2(position.x, (int) internalY);
    }


}
