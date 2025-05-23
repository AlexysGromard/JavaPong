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

public class Result extends View {
    private BufferedImage backgroundImage;

    private Text score_left;
    private Text score_right;

    private Text result_left;
    private Text result_right;

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
        score_left = new Text("score_left", 299, 346, Game.scoreLeftPlayer.toString(), 200, FontManager.OrbitronStyle.MEDIUM, new Color(242, 242, 242));
        gameObjects.add(score_left);
        score_right = new Text("score_right", 996, 346, Game.scoreRightPlayer.toString(), 200, FontManager.OrbitronStyle.MEDIUM, new Color(242, 242, 242));
        gameObjects.add(score_right);

        result_left = new Text("result_left", 256, 597, "Winner", 64, FontManager.OrbitronStyle.REGULAR, new Color(242, 242, 242));
        gameObjects.add(result_left);
        result_right = new Text("result_right", 952, 597, "Looser", 64, FontManager.OrbitronStyle.REGULAR, new Color(242, 242, 242));
        gameObjects.add(result_right);

        // Button
        Button goToMenuButton = new Button("go_to_menu", new Vector2(548, 826), 345, 60, "GO TO MENU", new Color(242, 242, 242), new Color(0, 0, 0, 0));
        goToMenuButton.setClickListener(btn -> {
            Window.SwitchToView(Window.viewName.MENU);
        });

        gameObjects.add(goToMenuButton);
    }



    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Display score + winner / looser
        score_left.text = Game.scoreLeftPlayer.toString();
        score_right.text = Game.scoreRightPlayer.toString();
        if(Game.scoreLeftPlayer > Game.scoreLeftPlayer){
        result_left.text = "Winner";
        result_right.text = "Looser";
        }
        else{
        result_left.text = "Looser";
        result_right.text = "Winner";
        }

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
