package GameObjects.objects;
import GameObjects.GameObject;
import controllers.PaddleController;
import utils.Vector2;
import java.awt.*;

public class Paddle extends GameObject {
    private final Color RGBColor;
    public PaddleController controller;

    public int speed;

    public Paddle(String name, int x, int y, int width, int height, Color RGBColor, PaddleController controller) {
        this.name = name;
        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.RGBColor = RGBColor;
        this.controller = controller;
    }

    @Override
    public void draw(Graphics g) {
        // Draw the shadow
        Color shadowColor = new Color(RGBColor.getRed(), RGBColor.getGreen(), RGBColor.getBlue(), 25);
        for (int i = 12; i >= 1; i--) { // Spread simulated by fuzzy concentric circles
            g.setColor(new Color(shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), 64 / i)); // Lighter as we move away
            g.fillRoundRect(position.x - i, position.y - i,
                    width + 2 * i, height + 2 * i,
                    10 + i, 10 + i);
        }
        // Draw the paddle
        g.setColor(RGBColor);
        g.fillRoundRect(position.x, position.y, width, height, 10, 10);
    }

    @Override
    public void update(Graphics g) {
        if (controller != null) {
            Vector2 v = controller.computePosition(position);
            speed = v.y - this.position.y;
            this.position = v;
        }
        draw(g);
    }
}
