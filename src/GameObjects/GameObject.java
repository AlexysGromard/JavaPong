package src.GameObjects;

import src.utils.Vector2;

public abstract class GameObject {

    
    String name;
    Vector2 position;

    
    void onCollisionEnter(GameObject collision){}

    void update(){};

    void  init(){};

    
}
