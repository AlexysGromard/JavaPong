package GameObjects.objects;

import GameObjects.GameObject;
import utils.AudioPlayer;
import utils.FontManager;
import utils.Sound;
import utils.Vector2;

import java.awt.*;

/**
 * Represents a game button that can be interacted with via mouse clicks.
 * The button is rendered with text, font, and background properties, and it includes
 * functionality to handle mouse interactions such as hovering and clicking.
 */
public class Button extends GameObject {
    private String text;
    private int width;
    private int height;
    private Color fontColor;
    private int fontSize;
    private Color backgroundColor;

    private Point mousePosition;
    private ButtonClickListener clickListener;

    /**
     * Constructs a new instance of the Button class, representing a clickable button
     * with customizable dimensions, text, fonts, and colors.
     *
     * @param name The name identifier of the button.
     * @param position The position of the button, represented as a Vector2 object.
     * @param width The width of the button in pixels.
     * @param height The height of the button in pixels.
     * @param text The text displayed on the button.
     * @param fontColor The color of the button's text.
     * @param fontSize The size of the font for the button's text.
     * @param backgroundColor The background color of the button.
     */
    public Button(String name, Vector2 position, int width, int height, String text, Color fontColor, int fontSize, Color backgroundColor) {
        this.name = name;
        this.position = position;
        this.width = width;
        this.height = height;
        this.text = text;
        this.fontColor = fontColor;
        this.fontSize = fontSize;
        this.backgroundColor = backgroundColor;
    }

    /**
     * Represents a listener interface for handling button click events.
     * Classes implementing this interface are expected to define specific
     * behavior to execute when a button is clicked.
     */
    public interface ButtonClickListener {
        void onClick(Button button);
    }

    /**
     * Sets a click listener for the button. The provided listener will be
     * notified whenever the button is clicked, allowing for the execution
     * of custom behavior upon a click event.
     *
     * @param listener An instance of the {@code ButtonClickListener} interface
     *                 that defines the behavior to execute when the button is clicked.
     */
    public void setClickListener(ButtonClickListener listener) {
        this.clickListener = listener;
    }

    public void handleClick(Point mousePosition) {
        if (isMouseOver(mousePosition) && clickListener != null) {
            AudioPlayer.play(Sound.BUTTON_CLICK);
            clickListener.onClick(this);
        }
    }

    /**
     * Checks if the mouse pointer is over the area defined by the object.
     *
     * @param mousePosition The current position of the mouse, represented as a {@code Point}.
     *                      The X and Y coordinates of the point are used to determine whether
     *                      it lies within the object boundaries.
     * @return {@code true} if the mouse pointer is within the boundaries of the object,
     *         {@code false} otherwise.
     */
    public boolean isMouseOver(Point mousePosition) {
        return (
            mousePosition.x >= position.x &&
            mousePosition.x <= position.x + width &&
            mousePosition.y >= position.y &&
            mousePosition.y <= position.y + height
        );
    }

    /**
     * Renders the button on the provided graphics context. The method handles
     * drawing the button's background, its text, and an underline if the
     * mouse cursor is hovering over the button. It also applies anti-aliasing
     * for smoother rendering.
     *
     * @param g The graphics context used for rendering. This context should
     *          be provided by the environment where the button is displayed,
     *          such as a Swing or AWT component.
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Add anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the button background
        g2.setColor(backgroundColor);
        g2.fillRect(position.x, position.y, width, height);

        // Draw the button text
        g2.setFont(FontManager.getOrbitron(fontSize, FontManager.OrbitronStyle.MEDIUM));
        g2.setColor(fontColor);
        FontMetrics metrics = g2.getFontMetrics();
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getAscent();
        int x = position.x + (width - textWidth) / 2;
        int y = position.y + (height + textHeight) / 2 - metrics.getDescent();
        g2.drawString(text, x, y);

        // Underline text if mouse is over
        if (isMouseOver(this.mousePosition)) {
            int underlineY = y + 4;
            g2.setStroke(new BasicStroke(3.0f));
            g2.drawLine(x, underlineY, x + textWidth, underlineY);
        }

        g2.dispose();
    }

    /**
     * Updates the state of the button and renders it. This method updates
     * the mouse position and triggers a redraw of the button on the provided
     * graphics context.
     *
     * @param g The graphics context used for rendering. This context should
     *          be provided by the environment where the button is displayed.
     * @param mousePosition The current position of the mouse, represented as a {@code Point}.
     *                      Used to update the button's state, such as hover effects.
     */
    @Override
    public void update(Graphics g, Point mousePosition) {
        this.mousePosition = mousePosition;
        draw(g);
    }
}
