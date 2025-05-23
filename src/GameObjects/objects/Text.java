package GameObjects.objects;
import GameObjects.GameObject;
import utils.FontManager;
import java.awt.*;

public class Text extends GameObject{
    public String text;
    private int fontSize;
    private FontManager.OrbitronStyle fontStyle;
    private Color color;

    public Text(String name, int x, int y, String text) {
        this(name, x, y, text, 12, FontManager.OrbitronStyle.REGULAR, Color.WHITE);
    }

    public Text(String name, int x, int y, String text, int fontSize, FontManager.OrbitronStyle fontStyle, Color RGBColor) {
        this.name = name;
        this.position = new utils.Vector2(x, y);
        this.text = text;
        this.fontSize = fontSize;
        this.fontStyle = fontStyle;
        this.color = RGBColor;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setFont(FontManager.getOrbitron(fontSize, fontStyle));
        g2.setColor(color);
        g2.drawString(text, position.x, position.y + g2.getFontMetrics().getAscent());
    }

    @Override
    public void update(Graphics g) {
        draw(g);
    }
}
