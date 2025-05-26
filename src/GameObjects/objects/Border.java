package GameObjects.objects;

import java.awt.Color;
import java.awt.Graphics;

import GameObjects.GameObject;
import utils.Vector2;

/**
 * Represents a Border object within a game, which is a drawable and updatable
 * entity extending the GameObject class. A Border is used to designate the edges
 * of the game area, with customizable dimensions, position, color, shadow,
 * and orientation.
 */
public class Border extends GameObject{
    
    private Color borderColor;
    private Color shadowColor = null;
    private BorderType borderType;

    /**
     * Enumeration that defines the possible border types for a game area.
     *
     * Each border type corresponds to one of the four edges of a rectangular game area:
     * LEFT, RIGHT, TOP, and BOTTOM. This is typically used to identify
     * the orientation of a border element within the game's layout.
     */
    public enum BorderType {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM
    }

    /**
     * Constructs a Border object with specified name, position, size, and border color.
     *
     * @param name The name of the Border object.
     * @param position The position of the Border as a Vector2 object, representing the top-left corner.
     * @param size The size of the Border as a Vector2 object, where x represents the width and y represents the height.
     * @param borderColor The color of the Border.
     */
    public Border(String name, Vector2 position, Vector2 size, Color borderColor) {
        this(name, position, size, borderColor, null, null);
    }

    /**
     * Constructs a Border object with the specified attributes, allowing customization
     * of name, position, size, border color, shadow color, and border type.
     *
     * @param name The name of the Border object.
     * @param position The position of the Border as a Vector2 object, representing the top-left corner.
     * @param size The size of the Border as a Vector2 object, where x represents the width and y represents the height.
     * @param borderColor The color of the Border.
     * @param shadowColor The shadow color of the Border.
     * @param borderType The type of the Border, represented by a BorderType enumeration value.
     */
    public Border(String name, Vector2 position, Vector2 size, Color borderColor, Color shadowColor, BorderType borderType) {
        this.name = name;
        this.position = position;
        this.width = size.x;
        this.height = size.y;
        this.borderColor = borderColor;
        this.shadowColor = shadowColor;
        this.borderType = borderType;
    }

    /**
     * Draws the border and its shadow, if applicable, on the provided graphics object.
     * The method handles rendering of the shadow with a gradient effect based on the
     * border type and shadow color, followed by rendering the main rectangular border.
     *
     * @param g The graphics context used for drawing. It is used to render the border
     *          and its shadow based on the border's position, size, colors, and type.
     */
    @Override
    public void draw(Graphics g) {
        // Draw the shadow
        if (shadowColor != null && borderType != null) {
            for (int i = 15; i >= 1; i--) {
                g.setColor(new Color(shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), 50 / i));
                switch (borderType) {
                    case LEFT:
                        g.fillRect(position.x - i, position.y - i, width + i, height + 3 * i);
                        break;
                    case RIGHT:
                        g.fillRect(position.x, position.y - i, width + i, height + 3 * i);
                        break;
                    case TOP:
                        g.fillRect(position.x - i, position.y - i, width + 3 * i, height + i);
                        break;
                    case BOTTOM:
                        g.fillRect(position.x - i, position.y, width + 3 * i, height + i);
                        break;
                }
            }
        }

        // Draw the border
        g.setColor(borderColor);
        g.fillRect(position.x, position.y, width, height);
    }

    /**
     * Updates the visual representation of the object by invoking the draw method
     * with the provided graphics context. This ensures that the object's state
     * is visually rendered on the screen.
     *
     * @param g The graphics context used to render the updated appearance of the object.
     */
    @Override
    public void update(Graphics g) {
        draw(g);
    }
}
