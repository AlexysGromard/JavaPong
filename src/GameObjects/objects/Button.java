package GameObjects.objects;

import GameObjects.GameObject;
import utils.FontManager;

import javax.swing.text.Position;
import java.awt.*;

public class Button extends GameObject {
    private String text;
    private int width;
    private int height;
    private Color fontColor;
    private Color backgroundColor;
    private Component parentComponent;

    private Point mousePosition;

    public Button(Component parentComponent, String name, int x, int y, int width, int height, String text, Color fontColor, Color backgroundColor) {
        this.parentComponent = parentComponent;
        this.name = name;
        this.position = new utils.Vector2(x, y);
        this.width = width;
        this.height = height;
        this.text = text;
        this.fontColor = fontColor;
        this.backgroundColor = backgroundColor;
    }

    public boolean isMouseOver(Point mousePosition) {
        return (
                mousePosition.x >= position.x &&
                mousePosition.x <= position.x + width &&
                mousePosition.y >= position.y &&
                mousePosition.y <= position.y + height
        );
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Add anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the button background
        g2.setColor(backgroundColor);
        g2.fillRect(position.x, position.y, width, height);

        // Draw the button text
        g2.setFont(FontManager.getOrbitron(48, FontManager.OrbitronStyle.MEDIUM));
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


    @Override
    public void update(Graphics g, Point mousePosition) {
        this.mousePosition = mousePosition;
        draw(g);
    }
}
