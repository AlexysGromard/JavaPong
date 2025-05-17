package GameObjects.objects;

import GameObjects.GameObject;
import utils.Vector2;

import java.awt.*;

public class Puck extends GameObject{
    public Puck(String name, int x, int y, int width, int height) {
        this.name = name;
        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Add anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Draw the white circle
        g2.setColor(Color.WHITE);
        g2.fillOval(position.x, position.y, width, height);

        g2.dispose();
    }

    @Override
    public void update(Graphics g) {
        draw(g);
    }
}
