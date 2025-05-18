package windows;
import java.awt.* ;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import GameObjects.GameObject;
import GameObjects.objects.Paddle;
import utils.FontManager;

public class Game extends JPanel {

    public List<GameObject> gameObjects;
    private final int baseWidth = 800;
    private final int baseHeight = 500;

    Game(){
        setBackground(new  Color(8, 8, 14));

        this.gameObjects = new ArrayList<GameObject>();
        this.InstantiateObjects();
    }

    private void InstantiateObjects(){
        // Create the paddles
        gameObjects.add(new Paddle("Paddle_left", 37, 407, 12, 210, new Color(0, 255, 247)));
        gameObjects.add(new Paddle("Paddle_right", 1391, 407, 12, 210, new Color(255, 0, 224)));

        // Create the puck
        gameObjects.add(new GameObjects.objects.Puck("Puck", 700, 493, 39, 39));

        // Create the texts
        gameObjects.add(new GameObjects.objects.Text("Score_left", 629, 27, "0", 64, FontManager.OrbitronStyle.MEDIUM, Color.WHITE));
        gameObjects.add(new GameObjects.objects.Text("Score_right", 759, 27, "0", 64, FontManager.OrbitronStyle.MEDIUM, Color.WHITE));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        // Ref width and height for scaling (1440x1024)
        final int refWidth = 1440;
        final int refHeight = 1024;

        // Calculating the scale factor
        double scaleX = getWidth() / (double) refWidth;
        double scaleY = getHeight() / (double) refHeight;
        double scale = Math.min(scaleX, scaleY); // Pour garder les proportions

        // Calculating the offset to center
        int xOffset = (int) ((getWidth() - refWidth * scale) / 2);
        int yOffset = (int) ((getHeight() - refHeight * scale) / 2);

        // Apply centering and scale
        g2.translate(xOffset, yOffset);
        g2.scale(scale, scale);

        // Antialiasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Update and draw each game object
        for (GameObject go : this.gameObjects) {
            go.update(g2);
        }

        g2.dispose();
    }
}
