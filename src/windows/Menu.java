package windows;

import GameObjects.GameObject;
import GameObjects.objects.Button;
import utils.AudioPlayer;
import utils.FontManager;
import utils.Sound;
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

public class Menu extends View {

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

        // Start GameMusic
        AudioPlayer.loop(Sound.GAME_MUSIC);
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
            AudioPlayer.stop();
        });

        Button optionsBtn = new Button("options", new Vector2(556, 527), 328, 60, "OPTIONS", new Color(242, 242, 242), new Color(0, 0, 0, 0));
        optionsBtn.setClickListener(btn -> {
            System.out.println("OPTIONS clicked");
            // TODO: IMPLEMENT OPTION PAGE
            Window.SwitchToView(viewName.RESULT);
        });

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
}
