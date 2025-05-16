package windows;
import java.awt.* ;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import GameObjects.GameObject;

public class Game extends JPanel {

    public List<GameObject> gameObjects;

    Game(){
        this.gameObjects = new ArrayList<GameObject>();
        this.InstantiateObjects();
    }

    private void InstantiateObjects(){

    }
    
    @Override
    public void paintComponent (Graphics g){
        
        for (GameObject go : this.gameObjects) {
            go.update(g);
        }
   }
}
