package GameObjects.objects;
import GameObjects.GameObject;
import controllers.PaddleController;
import utils.Vector2;
import java.awt.*;

/**
 * Represents a paddle game object that is used in a game. The paddle is controlled
 * either manually or programmatically via a {@link PaddleController} and can be drawn
 * and updated within the game's graphics context.
 *
 * This class extends the {@link GameObject} abstract class and provides specific behavior
 * for Paddle objects, including movement based on a controller and rendering a visual
 * representation with shadow effects.
 */
public class Paddle extends GameObject {
    private final Color RGBColor;
    public PaddleController controller;

    public int speed;

    /**
     * Constructs a new Paddle instance with the specified parameters.
     *
     * @param name The name identifier for the paddle.
     * @param x The initial X-coordinate of the paddle.
     * @param y The initial Y-coordinate of the paddle.
     * @param width The width of the paddle.
     * @param height The height of the paddle.
     * @param RGBColor The color of the paddle in RGB format.
     * @param controller The {@link PaddleController} instance used to control the paddle's movement.
     */
    public Paddle(String name, int x, int y, int width, int height, Color RGBColor, PaddleController controller) {
        this.name = name;
        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.RGBColor = RGBColor;
        this.controller = controller;
    }

    /**
     * Renders the paddle object and its shadow within a graphics context.
     * The shadow is drawn using concentric translucent rectangles for a soft visual effect,
     * and the paddle is represented as a rounded rectangle with the specified color.
     *
     * @param g The {@link Graphics} object used for rendering the paddle and shadow.
     */
    @Override
    public void draw(Graphics g) {
        // Draw the shadow
        Color shadowColor = new Color(RGBColor.getRed(), RGBColor.getGreen(), RGBColor.getBlue(), 25);
        for (int i = 12; i >= 1; i--) { // Spread simulated by fuzzy concentric circles
            g.setColor(new Color(shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), 64 / i)); // Lighter as we move away
            g.fillRoundRect(position.x - i, position.y - i,
                    width + 2 * i, height + 2 * i,
                    10 + i, 10 + i);
        }
        // Draw the paddle
        g.setColor(RGBColor);
        g.fillRoundRect(position.x, position.y, width, height, 10, 10);
    }

    /**
     * Updates the paddle's position and speed based on the {@link PaddleController},
     * and renders it within the provided graphics context. The controller computes
     * the new position of the paddle, and the speed is calculated as the difference
     * between the new and previous Y-coordinate values. After the position is updated,
     * the paddle is rendered using the {@link #draw(Graphics)} method.
     *
     * @param g The {@link Graphics} object used for rendering the paddle and its shadow.
     */
    @Override
    public void update(Graphics g) {
        if (controller != null) {
            Vector2 v = controller.computePosition(position);
            speed = v.y - this.position.y;
            this.position = v;
        }
        draw(g);
    }
}
