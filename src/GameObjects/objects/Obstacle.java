package GameObjects.objects;
import java.awt.Color;
import java.awt.Graphics;

import GameObjects.GameObject;
import utils.Vector2;

public class Obstacle extends GameObject{
    
    private int middleY; //center Y of the obstacle.

    private boolean goingUp = true; //direction of the obstacle

    public Obstacle(String name, Vector2 position ){
        this.name = name;
        this.position = position;
        this.middleY = position.y;
        this.height = 100;
        this.width = 12;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(255, 59, 48));
        g.fillRoundRect(position.x, position.y, width, height, 10, 10);
    }

    @Override
    public void update(Graphics g) {
       this.draw(g);
       if(goingUp){
        this.position.y -= 5;
       }
       else{
        this.position.y += 5;
       }

       if(this.position.y > this.middleY + 300){
        this.goingUp = true;
       }
       if(this.position.y < this.middleY - 300){
        this.goingUp = false;
       }
    }
}
