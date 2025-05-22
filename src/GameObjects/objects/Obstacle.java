package GameObjects.objects;
import java.awt.Graphics;

import GameObjects.GameObject;
import utils.Vector2;

public class Obstacle extends GameObject{
    
    private int middleY;

    Obstacle(String name, Vector2 position ){
        this.name = name;
        this.position = position;
        this.middleY = position.y;

        this.height = 100;
        this.width = 20;
    }

    @Override
    public void update(Graphics g) {
        g.
    }
}
