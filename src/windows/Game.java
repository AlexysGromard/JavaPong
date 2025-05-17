package windows;
import java.awt.* ;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import GameObjects.GameObject;
import GameObjects.objects.Paddle;

public class Game extends JPanel {

    public List<GameObject> gameObjects;

    Game(){
        this.gameObjects = new ArrayList<GameObject>();
        this.InstantiateObjects();
    }

    private void InstantiateObjects(){
        gameObjects.add(new Paddle("Paddle_left", 50, 200, 12, 210, new Color(0, 255, 247)));
    }
    
    @Override
    public void paintComponent (Graphics g){
        
        for (GameObject go : this.gameObjects) {
            go.update(g);
        }
   }
}
