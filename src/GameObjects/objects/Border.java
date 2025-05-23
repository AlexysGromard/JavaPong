package GameObjects.objects;

import java.awt.Color;
import java.awt.Graphics;

import GameObjects.GameObject;
import utils.Vector2;

public class Border extends GameObject{
    
    private Color borderColor;
    private Color shadowColor = null;
    private BorderType borderType;

    public enum BorderType {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM
    }

    public Border(String name, Vector2 position, Vector2 size, Color borderColor) {
        this(name, position, size, borderColor, null, null);
    }

    public Border(String name, Vector2 position, Vector2 size, Color borderColor, Color shadowColor, BorderType borderType) {
        this.name = name;
        this.position = position;
        this.width = size.x;
        this.height = size.y;
        this.borderColor = borderColor;
        this.shadowColor = shadowColor;
        this.borderType = borderType;
    }

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

    @Override
    public void update(Graphics g) {
        draw(g);
    }
}
