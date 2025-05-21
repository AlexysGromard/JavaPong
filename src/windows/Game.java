package windows;
import java.awt.* ;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import GameObjects.GameObject;
import GameObjects.objects.Border;
import GameObjects.objects.Paddle;
import controllers.KeyBoardController;
import controllers.PaddleController;
import utils.FontManager;
import utils.Vector2;

public class Game extends JPanel {

    public List<GameObject> gameObjects;

    Game(){
        

        setBackground(new  Color(13, 13, 13));

        this.gameObjects = new ArrayList<GameObject>();
        this.InstantiateObjects();
    }

    /**
     * Instantiates the game objects.
     * This method creates the borders, paddles, puck, and texts.
     * It is called in the constructor of the Game class.
     */
    private void InstantiateObjects(){
        
        // Create the borders
        gameObjects.add(new GameObjects.objects.Border("Border_left", new Vector2(0, 0), new Vector2(6, 1024), new Color(0, 255, 247, 50), new Color(0, 255, 247), Border.BorderType.RIGHT));
        gameObjects.add(new GameObjects.objects.Border("Border_right", new Vector2(1434, 0), new Vector2(6, 1024), new Color(255, 0, 224, 50), new Color(255, 0, 224), Border.BorderType.LEFT));
        gameObjects.add(new GameObjects.objects.Border("Border_top", new Vector2(0, 0), new Vector2(1440, 6), new Color(51, 51, 51)));
        gameObjects.add(new GameObjects.objects.Border("Border_bottom", new Vector2(0, 1018), new Vector2(1440, 6), new Color(51, 51, 51)));
        gameObjects.add(new GameObjects.objects.Border("Border_center", new Vector2(716, 0), new Vector2(6, 1024), new Color(51, 51, 51)));
        gameObjects.add(new GameObjects.objects.Circle("Circle_center", 645, 438, 150, 150, 6, new Color(51, 51, 51), new Color(13, 13, 13)));

        // Create the texts
        gameObjects.add(new GameObjects.objects.Text("Score_left", 629, 27, "0", 64, FontManager.OrbitronStyle.MEDIUM, new Color(242, 242, 242)));
        gameObjects.add(new GameObjects.objects.Text("Score_right", 759, 27, "0", 64, FontManager.OrbitronStyle.MEDIUM, new Color(242, 242, 242)));

        // Create theKeyboard controllers
        PaddleController controllerLeft= new controllers.KeyBoardController(KeyEvent.VK_Z, KeyEvent.VK_S, 6, 1018, 210);
        PaddleController controllerRight = new controllers.KeyBoardController(KeyEvent.VK_UP, KeyEvent.VK_DOWN, 6, 1018, 210);

        // Create the paddles
        gameObjects.add(new Paddle("Paddle_left", 37, 407, 12, 210, new Color(0, 255, 247), controllerLeft));
        gameObjects.add(new Paddle("Paddle_right", 1391, 407, 12, 210, new Color(255, 0, 224), controllerRight));

        // Cast the controllers to KeyListener and add them to the panel
        addKeyListener((KeyListener) controllerLeft);
        addKeyListener((KeyListener) controllerRight);
        setFocusable(true);
        requestFocusInWindow();

        // Create the puck
        gameObjects.add(new GameObjects.objects.Puck("Puck", 700, 493, 39, 39));
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
        double scale = Math.min(scaleX, scaleY);

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
