package src.GameObjects;

import src.utils.Vector2;

public abstract class GameObject {

    
    String name;
    Vector2 position;

    int width;
    int height;

    
    void onCollisionEnter(GameObject collision){}

    void update(){};

    void  init(){};

    
}
