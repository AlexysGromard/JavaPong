package GameObjects.objects;

import GameObjects.GameObject;
import utils.AudioPlayer;
import utils.Sound;
import utils.Vector2;
import windows.Game;

import java.awt.*;
import java.util.Random;

public class Puck extends GameObject{

    private Vector2 speed;
    private int maxSpeed = 140;

    public Puck(String name, int x, int y, int width, int height) {
        this.name = name;
        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;

        this.speed = new Vector2( 9, 0);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Add anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Draw the white circle
        g2.setColor(Color.WHITE);
        g2.fillOval(position.x, position.y, width, height);

        g2.dispose();
    }

    @Override
    public void update(Graphics g) {
        this.position.x += this.speed.x;
        this.position.y += this.speed.y;
        this.normalizeSpeed();
        draw(g);
    }

    @Override
    public void onCollisionEnter(GameObject collision){
        Random r = new Random();
        if(collision instanceof Paddle){
            if(collision.position.x > this.position.y){
                this.speed.x = 0 - this.speed.x;
                this.speed.x += r.nextInt(3) - 1;
                this.speed.y += r.nextInt(3) - 1;
                 this.speed.y += ( (Paddle)collision).speed * 0.5;
            }
            if(collision.position.x < this.position.y){
                this.speed.x =  0 - this.speed.x;
                this.speed.x += r.nextInt(3) - 1;
                this.speed.y += r.nextInt(3) - 1;
                this.speed.y +=( (Paddle)collision).speed * 0.5;
            }

            // Play sound
            AudioPlayer.play(Sound.PUCK_SOUND);
       }
        else if(collision.name == "Border_top" || collision.name == "Border_bottom"){
            this.speed.y *= -1;
            this.speed.y += r.nextInt(3) - 1;
       }
       else if(collision.name == "Border_left"){
            Game.gameObjects.remove(this);
            Game.pointMarqued(false);
       }
        else if(collision.name == "Border_right"){
            Game.gameObjects.remove(this);
            Game.pointMarqued(true);
       }

    }

    private void normalizeSpeed(){
        int norme = speed.x * speed.x + speed.y * speed.y;
        if(norme> maxSpeed){
            speed.x =speed.x * maxSpeed / norme;
            speed.y = speed.y * maxSpeed / norme;
        }
    }
}
