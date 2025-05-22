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
        // Draw the circle
        g.setColor(fillColor);
        g.fillOval(position.x, position.y, width, height);

        // Draw the border
        g.setColor(borderColor);
        ((Graphics2D)g).setStroke(new BasicStroke(borderWidth));

        g.drawOval(position.x, position.y, width, height);
    }

    @Override
    public void update(Graphics g) {
        draw(g);
    }
}
