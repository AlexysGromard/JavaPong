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

    Game(){
        this.gameObjects = new ArrayList<GameObject>();
        this.InstantiateObjects();
    }

    private void InstantiateObjects(){
        // Create the paddles
        gameObjects.add(new Paddle("Paddle_left", 50, 200, 12, 210, new Color(0, 255, 247)));
        gameObjects.add(new Paddle("Paddle_right", 700, 200, 12, 210, new Color(255, 0, 224)));

        // Create the puck
        gameObjects.add(new GameObjects.objects.Puck("Puck", 400, 250, 39, 39));

        // Create the text
        gameObjects.add(new GameObjects.objects.Text("Title", 300, 50, "Pong", 50, FontManager.OrbitronStyle.BOLD, Color.WHITE));
    }
    
    @Override
    public void paintComponent (Graphics g){
        
        for (GameObject go : this.gameObjects) {
            go.update(g);
        }
   }
}
