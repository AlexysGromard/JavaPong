package GameObjects.objects;

import GameObjects.GameObject;
import utils.FontManager;

import java.awt.*;

/**
 * The Input class represents a graphical text input component in a game. It extends the functionality
 * of the GameObject class by providing additional properties and behaviors for rendering and updating input fields.
 * The class enables customization of the input's dimensions, font style, text color, and background color.
 */
public class Input extends GameObject {
    public String inputText;
    private int width;
    private int height;

    private int fontSize;
    private FontManager.OrbitronStyle fontStyle;
    private Color color;
    private Color backgroundColor;

    /**
     * Constructs a new Input object with specified properties, including position, dimensions, and default style settings.
     *
     * @param name       The name of the input field.
     * @param x          The X-coordinate of the input's position on the screen.
     * @param y          The Y-coordinate of the input's position on the screen.
     * @param width      The width of the input field.
     * @param height     The height of the input field.
     * @param inputText  The initial text displayed in the input field.
     */
    public Input(String name, int x, int y, int width, int height, String inputText) {
        this.name = name;
        this.position = new utils.Vector2(x, y);
        this.width = width;
        this.height = height;
        this.inputText = inputText;
        this.fontSize = 24; // Default font size
        this.fontStyle = FontManager.OrbitronStyle.REGULAR; // Default font style
        this.color = new Color(242, 242, 242); // Default color
        this.backgroundColor = new Color(51, 51, 51); // Default background color
    }

    /**
     * Renders a graphical input field, including its background and centered text, onto the provided Graphics context.
     *
     * @param g The Graphics object used for rendering the input field and its text.
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Draw the background rectangle
        g2.setColor(backgroundColor);
        g.fillRoundRect(position.x, position.y, width, height, 10, 10);

        // Draw the input text
        // Calculate the position to center the text within the rectangle
        g2.setFont(FontManager.getOrbitron(fontSize, fontStyle));
        FontMetrics metrics = g2.getFontMetrics();
        int textX = position.x + (width - metrics.stringWidth(inputText)) / 2;
        int textY = position.y + (height - metrics.getHeight()) / 2;

        Text textObject = new Text(name, textX, textY, inputText, fontSize, fontStyle, color);
        textObject.draw(g);
    }

    /**
     * Updates the graphical representation of the object by rendering it onto the provided Graphics context.
     * This method calls the {@link #draw(Graphics)} method to perform the actual drawing operations.
     *
     * @param g The Graphics object used to render the visual representation of this object.
     */
    @Override
    public void update(Graphics g) {
        draw(g);
    }
}
