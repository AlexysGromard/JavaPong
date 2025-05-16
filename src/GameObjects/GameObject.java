package src.GameObjects;
import java.awt.* ;
import src.utils.Vector2;

public abstract class GameObject {

    
    String name;
    Vector2 position;

    int width;
    int height;

    
    public void onCollisionEnter(GameObject collision){}

    public void update(Graphics g){};

    public void init(){};

    
}
