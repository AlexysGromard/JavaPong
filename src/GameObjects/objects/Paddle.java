package GameObjects.objects;

import GameObjects.GameObject;
import utils.Vector2;

import java.awt.*;

public class Paddle extends GameObject {
    private final Color RGBColor;

    public Paddle(String name, int x, int y, int width, int height, Color RGBColor) {
        this.name = name;
        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.RGBColor = RGBColor;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Add anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the shadow
        Color shadowColor = new Color(RGBColor.getRed(), RGBColor.getGreen(), RGBColor.getBlue(), 25);
        for (int i = 8; i >= 1; i--) { // Spread simulated by fuzzy concentric circles
            g2.setColor(new Color(shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), 64 / i)); // Lighter as we move away
            g2.fillRoundRect(position.x - i, position.y - i,
                    width + 2 * i, height + 2 * i,
                    10 + i, 10 + i);
        }
        // Draw the paddle
        g2.setColor(RGBColor);
        g2.fillRoundRect(position.x, position.y, width, height, 10, 10);

        g2.dispose();
    }

    @Override
    public void update(Graphics g) {
        draw(g);
    }
}
