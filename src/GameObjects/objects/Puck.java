package GameObjects.objects;
import GameObjects.GameObject;
import utils.AudioPlayer;
import utils.Sound;
import utils.Vector2;
import windows.Game;
import java.awt.*;
import java.util.Random;

/**
 * The Puck class represents a puck object in a game, extending the GameObject class.
 * It defines specific behavior for the puck, including movement, collision handling,
 * and rendering on the screen.
 */
public class Puck extends GameObject{

    private Vector2 speed;
    private int maxSpeed = 140;

    /**
     * Constructs a new Puck instance with specified name, position, dimensions, and an initial speed.
     *
     * @param name   The name of the puck.
     * @param x      The x-coordinate of the puck's initial position.
     * @param y      The y-coordinate of the puck's initial position.
     * @param width  The width of the puck.
     * @param height The height of the puck.
     */
    public Puck(String name, int x, int y, int width, int height) {
        this.name = name;
        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.speed = new Vector2( 9, 0);
    }

    /**
     * Draws the puck as a white circular shape on the specified graphics context.
     *
     * @param g The Graphics object used to render the puck.
     */
    @Override
    public void draw(Graphics g) {     
        // Draw the white circle
        g.setColor(Color.WHITE);
        g.fillOval(position.x, position.y, width, height);
    }

    /**
     * Updates the state of the puck by adjusting its position based on its speed and rendering
     * its updated state on the provided Graphics context. The speed is normalized to prevent
     * it from exceeding the maximum allowable speed.
     *
     * @param g The Graphics object used to render the puck with its updated position and state.
     */
    @Override
    public void update(Graphics g) {
        this.position.x += this.speed.x;
        this.position.y += this.speed.y;
        
        this.normalizeSpeed();
        draw(g);
    }

    /**
     * Handles collision events for the puck by reacting to collisions with various game objects,
     * such as paddles, borders, and obstacles. The reaction involves updating the puck's position,
     * speed, and optionally triggering sound effects or game events like scoring.
     *
     * @param collision The game object that the puck has collided with.
     */
    @Override
    public void onCollisionEnter(GameObject collision){
        Random r = new Random();

        if(collision instanceof Paddle ){
            if(collision.position.x > this.position.y){
                this.speed.x = 0 - this.speed.x;
                this.position.x -= 4;
                this.speed.x += r.nextInt(3) - 1;
                this.speed.y += r.nextInt(3) - 1;
                 this.speed.y += ( (Paddle)collision).speed * 0.5;
            }
            if(collision.position.x < this.position.y){
                this.speed.x =  0 - this.speed.x;
                this.position.x += 4;
                this.speed.x += r.nextInt(3) - 1;
                this.speed.y += r.nextInt(3) - 1;
                this.speed.y +=( (Paddle)collision).speed * 0.5;
            }

            // Play sound
            AudioPlayer.play(Sound.PUCK_SOUND);
        }
        else if(collision.name == "Border_top"){
            this.position.y += 4;
            this.speed.y *= -1;
            this.speed.x += r.nextInt(3) - 1;
            this.speed.y += r.nextInt(3) - 1;
       }
       else if(collision.name == "Border_bottom"){
            this.position.y -= 4;
            this.speed.y *= -1;
            this.speed.x += r.nextInt(3) - 1;
            this.speed.y += r.nextInt(3) - 1;
       }
       else if(collision.name == "Border_left"){
            Game.pointMarqued(false);
       }
        else if(collision.name == "Border_right"){
            Game.pointMarqued(true);
       }
       else if(collision instanceof Obstacle){ //Colision with an obstacle
        
            if(collision.position.x > this.position.y){
                this.speed.x = 0 - this.speed.x;
                this.position.x -= 6;
                this.speed.x += r.nextInt(3) - 1;
                this.speed.y += r.nextInt(3) - 1;
            }
            if(collision.position.x < this.position.y){
                this.speed.x =  0 - this.speed.x;
                this.position.x += 6;
                this.speed.x += r.nextInt(3) - 1;
                this.speed.y += r.nextInt(3) - 1;
            }

            // Play sound
            AudioPlayer.play(Sound.PUCK_SOUND);
       }

    }

    /**
     * Normalizes the speed of the puck to ensure it does not exceed the maximum allowable speed.
     * The normalization process calculates the magnitude (norm) of the speed vector and adjusts
     * the speed components proportionally if the magnitude surpasses the defined maximum speed.
     *
     * This method ensures the puck operates within the speed constraints defined by the game logic.
     */
    private void normalizeSpeed(){
        int norme = speed.x * speed.x + speed.y * speed.y;
        if(norme> maxSpeed){
            speed.x =speed.x * maxSpeed / norme;
            speed.y = speed.y * maxSpeed / norme;
        }
    }
}
