package GameObjects.objects;

import GameObjects.GameObject;
import utils.FontManager;

import java.awt.*;

public class Input extends GameObject {
    public String inputText;
    private int width;
    private int height;

    private int fontSize;
    private FontManager.OrbitronStyle fontStyle;
    private Color color;
    private Color backgroundColor;

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

    @Override
    public void update(Graphics g) {
        draw(g);
    }
}
