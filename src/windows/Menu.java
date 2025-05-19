package windows;

import GameObjects.GameObject;
import utils.FontManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Menu extends JPanel {

    public List<GameObject> gameObjects;
    private BufferedImage backgroundImage;

    Menu(){
        setBackground(new  Color(13, 13, 13));
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/menu_background.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.gameObjects = new java.util.ArrayList<GameObject>();
        this.InstantiateObjects();
    }

    /**
     * Instantiates the game objects.
     * This method creates the borders, paddles, puck, and texts.
     * It is called in the constructor of the Game class.
     */
    private void InstantiateObjects(){
        // Create the texts
        gameObjects.add(new GameObjects.objects.Text("Title", 444, 178, "JavaPong", 96, FontManager.OrbitronStyle.BOLD, new Color(242, 242, 242)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        // Ref width and height for scaling (1440x1024)
        final int refWidth = 1440;
        final int refHeight = 1024;

        // Calculating the scale factor
        double scaleX = getWidth() / (double) refWidth;
        double scaleY = getHeight() / (double) refHeight;
        double scale = Math.min(scaleX, scaleY);

        // Calculating the offset to center
        int xOffset = (int) ((getWidth() - refWidth * scale) / 2);
        int yOffset = (int) ((getHeight() - refHeight * scale) / 2);

        // Apply centering and scale
        g2.translate(xOffset, yOffset);
        g2.scale(scale, scale);

        // Antialiasing
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        // Background image
        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 0, 0, refWidth, refHeight, null);
        }

        // Update and draw each game object
        for (GameObject go : this.gameObjects) {
            go.update(g2);
        }

        g2.dispose();
    }
}
