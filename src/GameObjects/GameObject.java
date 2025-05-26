package GameObjects;
import java.awt.* ;
import utils.Vector2;

/**
 * Represents an abstract base class for all game objects. A game object is a drawable
 * and updatable entity in a game, with properties such as position, size, and name.
 * Implementing classes should define specific behavior by overriding the provided methods.
 */
public abstract class GameObject {

    public String name;
    public Vector2 position;
    public int width;
    public int height;

    /**
     * Called if the object is colliding with another object
     * @param collision The other object to check collision with
     */
    public void onCollisionEnter(GameObject collision){};

    /**
     * Called each frame to update the object
     * @param g Graphics object to draw on
     */
    public void update(Graphics g){};

    /**
     * Draws the object on the screen
     * @param g Graphics object to draw on
     */
    public void draw(Graphics g){};

    /**
     * Updates the state of the game object on each frame and handles interactions based on the current mouse position.
     * This method can be overridden by subclasses to define specific update behavior.
     *
     * @param g the Graphics context used for rendering the game object
     * @param mousePosition the current position of the mouse, used to detect interactions with the game object
     */
    public void update(Graphics g, Point mousePosition){};
}
