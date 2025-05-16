package GameObjects;
import java.awt.* ;
import utils.Vector2;

public abstract class GameObject {

    public String name;
    public Vector2 position;
    public int width;
    public int height;

    /**
     * Calculates if the object is colliding with another object
     * @param collision The other object to check collision with
     */
    public void onCollisionEnter(GameObject collision){}

    /**
     * Called each frame to update the object
     * @param g Graphics object to draw on
     */
    public void update(Graphics g){};

    /**
     * Draws the object on the screen
     * @param g Graphics object to draw on
     */
    public void draw(Graphics g){}

    
}
