package GameObjects.objects;
import GameObjects.GameObject;

import java.awt.*;

/**
 * The Circle class represents a circular graphical object in a game. It extends the GameObject
 * class and provides specific implementation for rendering and updating a circle, including
 * attributes such as position, size, border, and fill colors.
 */
public class Circle extends GameObject {
    private Color borderColor;
    private Color fillColor;
    private int borderWidth;

    /**
     * Constructs a new Circle object with specified attributes for rendering
     * a graphical circle in a 2D space.
     *
     * @param name The name of the Circle, which can be used for identification.
     * @param x The X-coordinate of the circle's position.
     * @param y The Y-coordinate of the circle's position.
     * @param width The width (or diameter) of the circle.
     * @param height The height (or diameter) of the circle.
     * @param borderWidth The thickness of the circle's border.
     * @param borderColor The color of the circle's border.
     * @param fillColor The fill color of the circle.
     */
    public Circle(String name, int x, int y, int width, int height, int borderWidth, Color borderColor, Color fillColor) {
        this.name = name;
        this.position = new utils.Vector2(x, y);
        this.width = width;
        this.height = height;
        this.borderWidth = borderWidth;
        this.borderColor = borderColor;
        this.fillColor = fillColor;
    }

    /**
     * Renders the Circle object by drawing a filled oval and its border
     * using the specified Graphics context.
     *
     * @param g The Graphics context used for rendering the object.
     */
    @Override
    public void draw(Graphics g) {
        // Draw the circle
        g.setColor(fillColor);
        g.fillOval(position.x, position.y, width, height);

        // Draw the border
        g.setColor(borderColor);
        ((Graphics2D)g).setStroke(new BasicStroke(borderWidth));

        g.drawOval(position.x, position.y, width, height);
    }

    /**
     * Updates the visual representation of the Circle object by invoking
     * the {@code draw} method with the specified Graphics context.
     *
     * @param g The Graphics context used for rendering the object.
     */
    @Override
    public void update(Graphics g) {
        draw(g);
    }
}
