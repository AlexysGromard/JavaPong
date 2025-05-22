package windows;

import GameObjects.GameObject;
import GameObjects.objects.Button;
import GameObjects.objects.Text;
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

public class Result extends View {
    public List<GameObject> gameObjects;
    private BufferedImage backgroundImage;

    Result(){
        setBackground(new Color(13, 13, 13));
        try {
            backgroundImage = ImageIO.read(new File("resources/result_background.png"));
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

    private void InstantiateObjects(){
        // Create the texts
        gameObjects.add(new Text("score_left", 299, 346, "0", 200, FontManager.OrbitronStyle.MEDIUM, new Color(242, 242, 242)));
        gameObjects.add(new Text("score_right", 996, 346, "0", 200, FontManager.OrbitronStyle.MEDIUM, new Color(242, 242, 242)));

        gameObjects.add(new Text("result_left", 256, 597, "Winner", 64, FontManager.OrbitronStyle.REGULAR, new Color(242, 242, 242)));
        gameObjects.add(new Text("result_right", 952, 597, "Looser", 64, FontManager.OrbitronStyle.REGULAR, new Color(242, 242, 242)));

        // Button
        Button goToMenuButton = new Button("go_to_menu", new Vector2(548, 826), 345, 60, "GO TO MENU", new Color(242, 242, 242), new Color(0, 0, 0, 0));
        goToMenuButton.setClickListener(btn -> {
            Window.SwitchToView(Window.viewName.MENU);
        });

        gameObjects.add(goToMenuButton);
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
