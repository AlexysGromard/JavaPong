package windows;

import GameObjects.GameObject;
import GameObjects.objects.Button;
import utils.FontManager;
import utils.Vector2;
import windows.Window.viewName;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Objects;

public class Menu extends JPanel {

    public List<GameObject> gameObjects;
    private BufferedImage backgroundImage;

    Menu(){
         setBackground(new  Color(13, 13, 13));
        try {
            backgroundImage = ImageIO.read(new File("resources/menu_background.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.gameObjects = new java.util.ArrayList<GameObject>();
        this.InstantiateObjects();

        // Add a mouse listener to handle clicks
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleClick(e.getPoint());
            }
        });
    }

    /**
     * Handles a click on the menu.
     * This method is called when the mouse is clicked on the menu.
     * It iterates over the game objects and checks if any of them is a button.
     * If so, it calls the handleClick method of the button.
     *
     * @param clickPoint The point where the mouse was clicked
     */
    private void handleClick(Point clickPoint) {
        Point logicalPoint = screenToLogical(clickPoint);
        for (GameObject go : gameObjects) {
            if (go instanceof Button button) {
                button.handleClick(logicalPoint);
            }
        }
    }

    /**
     * Instantiates the game objects.
     * This method creates the borders, paddles, puck, and texts.
     * It is called in the constructor of the Game class.
     */
    private void InstantiateObjects(){
        // Create the texts
        gameObjects.add(new GameObjects.objects.Text("Title", 444, 178, "JavaPong", 96, FontManager.OrbitronStyle.BOLD, new Color(242, 242, 242)));

        // Create the buttons
        Button playBtn = new Button("play", new Vector2(556, 407), 328, 60, "PLAY", new Color(242, 242, 242), new Color(0, 0, 0, 0));
        playBtn.setClickListener(btn -> {
            Window.SwitchToView(viewName.GAME);
        });

        Button optionsBtn = new Button("options", new Vector2(556, 527), 328, 60, "OPTIONS", new Color(242, 242, 242), new Color(0, 0, 0, 0));
        optionsBtn.setClickListener(btn -> System.out.println("OPTIONS clicked"));

        Button quitBtn = new Button("quit", new Vector2(556, 647), 328, 60, "QUIT", new Color(242, 242, 242), new Color(0, 0, 0, 0));
        quitBtn.setClickListener(btn -> System.exit(0));

        gameObjects.add(playBtn);
        gameObjects.add(optionsBtn);
        gameObjects.add(quitBtn);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        RenderContext ctx = getRenderContext();
        g2.translate(ctx.xOffset(), ctx.yOffset());
        g2.scale(ctx.scale(), ctx.scale());

        // Antialiasing
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 0, 0, 1440, 1024, null);
        }

        // Position souris logique
        Point mousePosition = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(mousePosition, this);
        Point logicalMousePosition = screenToLogical(mousePosition);

        boolean isHoveringAnyButton = false;

        for (GameObject go : gameObjects) {
            if (go instanceof Button button) {
                button.update(g2, logicalMousePosition);
                if (button.isMouseOver(logicalMousePosition)) {
                    isHoveringAnyButton = true;
                }
            } else {
                go.update(g2);
            }
        }

        setCursor(Cursor.getPredefinedCursor(isHoveringAnyButton ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR));
        g2.dispose();
    }

    /**
     * Converts a point on the screen to a point on the logical screen.
     * @param screenPoint The point on the screen to convert.
     * @return The converted point on the logical screen.
     */
    private Point screenToLogical(Point screenPoint) {
        final int refWidth = 1440;
        final int refHeight = 1024;
        double scaleX = getWidth() / (double) refWidth;
        double scaleY = getHeight() / (double) refHeight;
        double scale = Math.min(scaleX, scaleY);
        int xOffset = (int) ((getWidth() - refWidth * scale) / 2);
        int yOffset = (int) ((getHeight() - refHeight * scale) / 2);
        int logicalX = (int) ((screenPoint.x - xOffset) / scale);
        int logicalY = (int) ((screenPoint.y - yOffset) / scale);
        return new Point(logicalX, logicalY);
    }

    /**
     * A record that stores the scale, x offset, and y offset of the logical screen.
     * @param scale The scale of the logical screen.
     * @param xOffset  The offset of the logical screen from the left side of the physical screen.
     * @param yOffset The offset of the logical screen from the top left corner of the physical screen.
     */
    private record RenderContext(double scale, int xOffset, int yOffset) {}

    private RenderContext getRenderContext() {
        final int refWidth = 1440;
        final int refHeight = 1024;
        double scaleX = getWidth() / (double) refWidth;
        double scaleY = getHeight() / (double) refHeight;
        double scale = Math.min(scaleX, scaleY);
        int xOffset = (int) ((getWidth() - refWidth * scale) / 2);
        int yOffset = (int) ((getHeight() - refHeight * scale) / 2);
        return new RenderContext(scale, xOffset, yOffset);
    }

}
