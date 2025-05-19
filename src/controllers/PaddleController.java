package controllers;

import utils.Vector2;

public interface PaddleController {
    /**
     * Computes the position of the paddle based on the given position.
     *
     * @param position The current position of the paddle.
     * @return The new position of the paddle.
     */
    public Vector2 computePosition(Vector2 position);
}
