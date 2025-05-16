package GameObjects;
import java.awt.* ;
import utils.Vector2;

public abstract class GameObject {

    
    public String name;
    public Vector2 position;

    public int width;
    public int height;

    
    public void onCollisionEnter(GameObject collision){}

    public void update(Graphics g){};

    public void init(){};

    
}
