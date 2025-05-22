package GameObjects.objects;
import java.awt.Color;
import java.awt.Graphics;

import GameObjects.GameObject;
import utils.Vector2;

public class Obstacle extends GameObject{
    
    private int middleY;

    private boolean goingUp = true;

    public Obstacle(String name, Vector2 position ){
        this.name = name;
        this.position = position;
        this.middleY = position.y;

        this.height = 100;
        this.width = 20;
    }

    @Override
    public void draw(Graphics g) {

        //Indicative part
        g.setColor(new Color(70, 6, 5));
        g.fillRect(position.x + 8, this.middleY - 300,4 , 600 + this.height);


        //Main part
        g.setColor(new Color(220, 15, 10));
        g.fillRect(position.x, position.y, width, height);
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
