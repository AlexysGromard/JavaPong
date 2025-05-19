package GameObjects.objects;

import GameObjects.GameObject;
import windows.Game;

import java.awt.*;

public class Circle extends GameObject {
    private Color borderColor;
    private Color fillColor;
    private int borderWidth;

    public Circle(String name, int x, int y, int width, int height, int borderWidth, Color borderColor, Color fillColor) {
        this.name = name;
        this.position = new utils.Vector2(x, y);
        this.width = width;
        this.height = height;
        this.borderWidth = borderWidth;
        this.borderColor = borderColor;
        this.fillColor = fillColor;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Add anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the circle
        g2.setColor(fillColor);
        g2.fillOval(position.x, position.y, width, height);

        // Draw the border
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(borderWidth));

        g2.drawOval(position.x, position.y, width, height);

        g2.dispose();
    }

    @Override
    public void update(Graphics g) {
        draw(g);
    }
}
