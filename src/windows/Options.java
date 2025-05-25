package windows;

import GameObjects.GameObject;
import GameObjects.objects.Button;
import utils.FontManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Options extends View {
    private BufferedImage backgroundImage;

    Options() {
        setBackground(new java.awt.Color(13, 13, 13));
        try {
            backgroundImage = javax.imageio.ImageIO.read(new java.io.File("resources/menu_background.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.gameObjects = new java.util.ArrayList<>();
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
     * Instantiates the game objects.
     * This method creates the borders, paddles, puck, and texts.
     * It is called in the constructor of the Game class.
     */
    private void InstantiateObjects() {
        // Create the texts
        gameObjects.add(new GameObjects.objects.Text("Title", 518, 123, "Options", 96, FontManager.OrbitronStyle.BOLD, new Color(242, 242, 242)));
        gameObjects.add(new GameObjects.objects.Text("Controls", 605, 258, "Controls", 48, FontManager.OrbitronStyle.MEDIUM, new Color(242, 242, 242)));

        gameObjects.add(new GameObjects.objects.Text("Player1InputUP", 623, 358, "Player left paddle UP", 16, FontManager.OrbitronStyle.REGULAR, new Color(242, 242, 242)));
        gameObjects.add(new GameObjects.objects.Text("Player1InputDOWN", 623, 433, "Player left paddle DOWN", 16, FontManager.OrbitronStyle.REGULAR, new Color(242, 242, 242)));
        gameObjects.add(new GameObjects.objects.Text("Player2InputUP", 623, 508, "Player right paddle UP", 16, FontManager.OrbitronStyle.REGULAR, new Color(242, 242, 242)));
        gameObjects.add(new GameObjects.objects.Text("Player2InputDOWN", 623, 583, "Player right paddle DOWN", 16, FontManager.OrbitronStyle.REGULAR, new Color(242, 242, 242)));

        gameObjects.add(new GameObjects.objects.Text("Display", 622, 643, "Display", 48, FontManager.OrbitronStyle.MEDIUM, new Color(242, 242, 242)));

        gameObjects.add(new GameObjects.objects.Text("FPS", 755, 740, "FPS", 16, FontManager.OrbitronStyle.REGULAR, new Color(242, 242, 242)));

        // Create the input fields
        gameObjects.add(new GameObjects.objects.Input("Player1InputUP", 564, 346, 44, 44, "Z"));
        gameObjects.add(new GameObjects.objects.Input("Player1InputDOWN", 564, 421, 44, 44, "S"));
        gameObjects.add(new GameObjects.objects.Input("Player2InputUP", 564, 496, 44, 44, "↑"));
        gameObjects.add(new GameObjects.objects.Input("Player2InputDOWN", 564, 571, 44, 44, "↓"));

        gameObjects.add(new GameObjects.objects.Input("FPS", 647, 728, 93, 44, "60"));

        // Create the button
        Button backBtn = new Button("back", new utils.Vector2(556, 822), 328, 60, "BACK", new Color(242, 242, 242), 48, new Color(0, 0, 0, 0));
        backBtn.setClickListener(btn -> {
            Window.SwitchToView(Window.viewName.MENU);
        });

        gameObjects.add(backBtn);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        RenderContext ctx = getRenderContext();
        g.translate(ctx.xOffset(), ctx.yOffset());
        ((Graphics2D)g).scale(ctx.scale(), ctx.scale());

        // Antialiasing
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, 1440, 1024, null);
        }

        // Position souris logique
        Point mousePosition = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(mousePosition, this);
        Point logicalMousePosition = screenToLogical(mousePosition);

        boolean isHoveringAnyButton = false;

        for (GameObject go : gameObjects) {
            if (go instanceof Button button) {
                button.update(g, logicalMousePosition);
                if (button.isMouseOver(logicalMousePosition)) {
                    isHoveringAnyButton = true;
                }
            } else {
                go.update(g);
            }
        }

        setCursor(Cursor.getPredefinedCursor(isHoveringAnyButton ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR));
    }
}
