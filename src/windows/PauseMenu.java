package windows;

import GameObjects.GameObject;
import GameObjects.objects.Button;
import utils.FontManager;
import utils.Vector2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class PauseMenu extends View{
    private BufferedImage backgroundImage;

    PauseMenu(){
        setBackground(new Color(13, 13, 13));
        try {
            backgroundImage = ImageIO.read(new File("resources/result_background.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        gameObjects = new java.util.ArrayList<GameObject>();
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
        gameObjects.add(new GameObjects.objects.Text("Title", 389, 247, "Pause menu", 96, FontManager.OrbitronStyle.BOLD, new Color(242, 242, 242)));

        // Create the buttons
        Button resumeButton = new Button("resume", new Vector2(555, 476), 328, 60, "RESUME", new Color(242, 242, 242), new Color(0, 0, 0, 0));
        resumeButton.setClickListener(btn -> {
            Window.SwitchToView(Window.viewName.GAME);
        });

        Button goToMenuButton = new Button("go_to_menu", new Vector2(555, 596), 345, 60, "GO TO MENU", new Color(242, 242, 242), new Color(0, 0, 0, 0));
        goToMenuButton.setClickListener(btn -> {
            Window.SwitchToView(Window.viewName.MENU);
        });

        gameObjects.add(resumeButton);
        gameObjects.add(goToMenuButton);
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

}
