package GameObjects.objects;
import GameObjects.GameObject;
import utils.FontManager;
import java.awt.*;

/**
 * Represents a text object in the game that can be drawn on the screen.
 * This class extends the {@code GameObject} class and provides specific functionality
 * for rendering text with configurable properties such as font, size, style, and color.
 */
public class Text extends GameObject{
    public String text;
    private int fontSize;
    private FontManager.OrbitronStyle fontStyle;
    private Color color;

    /**
     * Constructs a new {@code Text} object with specified position and text content,
     * using default font size, style, and color settings.
     *
     * @param name The name of the text object.
     * @param x The x-coordinate of the text's position.
     * @param y The y-coordinate of the text's position.
     * @param text The string to be displayed.
     */
    public Text(String name, int x, int y, String text) {
        this(name, x, y, text, 12, FontManager.OrbitronStyle.REGULAR, Color.WHITE);
    }

    /**
     * Constructs a new {@code Text} object with specified parameters for position, text content,
     * font size, style, and color.
     *
     * @param name The name of the text object.
     * @param x The x-coordinate of the text's position.
     * @param y The y-coordinate of the text's position.
     * @param text The text string to be displayed.
     * @param fontSize The size of the text font.
     * @param fontStyle The style of the font, represented by {@code FontManager.OrbitronStyle}.
     * @param RGBColor The color of the text, specified as a {@code Color} object.
     */
    public Text(String name, int x, int y, String text, int fontSize, FontManager.OrbitronStyle fontStyle, Color RGBColor) {
        this.name = name;
        this.position = new utils.Vector2(x, y);
        this.text = text;
        this.fontSize = fontSize;
        this.fontStyle = fontStyle;
        this.color = RGBColor;
    }

    /**
     * Renders the text object onto the specified {@code Graphics} context.
     * Depending on the content of the text, a different font may be selected to support non-Latin characters.
     *
     * @param g The {@code Graphics} context used to render the text.
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // If the text contains non-Latin characters, use a different font
        if (!text.matches("[\\p{L}\\p{N} ]+")) {
            g2.setFont(new Font("Segoe UI Symbol", Font.PLAIN, fontSize));
        } else {
            g2.setFont(FontManager.getOrbitron(fontSize, fontStyle));
        }

        g2.setColor(color);
        g2.drawString(text, position.x, position.y + g2.getFontMetrics().getAscent());
    }

    /**
     * Updates the state of the object by rendering it onto the specified {@code Graphics} context.
     * This method delegates the rendering operation to the {@link #draw(Graphics)} method.
     *
     * @param g The {@code Graphics} context used to render the text.
     */
    @Override
    public void update(Graphics g) {
        draw(g);
    }
}
