package GameObjects.objects;
import java.awt.Color;
import java.awt.Graphics;

import GameObjects.GameObject;
import utils.Vector2;

/**
 * Represents an obstacle in the game. The obstacle is a movable rectangle that
 * oscillates vertically within specified boundaries. This class extends the
 * GameObject base class, inheriting properties like position, size, and name.
 */
public class Obstacle extends GameObject{
    
    private int middleY; //center Y of the obstacle.

    private boolean goingUp = true; //direction of the obstacle

    /**
     * Constructs a new instance of the Obstacle class with specified name and position.
     * The obstacle is initialized with a fixed height and width, and its vertical
     * oscillation behavior starts from the specified position.
     *
     * @param name The name of the obstacle.
     * @param position The initial position of the obstacle represented as a Vector2 object.
     */
    public Obstacle(String name, Vector2 position ){
        this.name = name;
        this.position = position;
        this.middleY = position.y;
        this.height = 100;
        this.width = 12;
    }

    /**
     * Draws the obstacle on the screen using the specified Graphics object.
     * The obstacle is represented as a rounded rectangle with fixed width, height,
     * and corner arc dimensions.
     *
     * @param g The Graphics object used to render the obstacle on the screen.
     *          This object provides the drawing context where the obstacle's
     *          rounded rectangle shape is drawn.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(255, 59, 48));
        g.fillRoundRect(position.x, position.y, width, height, 10, 10);
    }

    /**
     * Updates the state of the obstacle for the current frame and adjusts its position
     * to simulate vertical oscillation. Additionally, the method redraws the obstacle on the screen.
     * The oscillation occurs between the boundaries defined by the `middleY` property with
     * an offset of 300 units in both upward and downward directions.
     *
     * @param g The Graphics object used to render the obstacle. It provides the context
     *          required for drawing the obstacle during the update process.
     */
    @Override
    public void update(Graphics g) {
       this.draw(g);
       if(goingUp){
        this.position.y -= 5;
       }
       else{
        this.position.y += 5;
       }

       if(this.position.y > this.middleY + 300){
        this.goingUp = true;
       }
       if(this.position.y < this.middleY - 300){
        this.goingUp = false;
       }
    }
}
