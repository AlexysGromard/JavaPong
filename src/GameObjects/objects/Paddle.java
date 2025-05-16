package GameObjects.objects;

import GameObjects.GameObject;

public class Paddle extends GameObject {
    public Paddle(String name, int x, int y, int width, int height) {
        this.name = name;
        this.position.x = x;
        this.position.y = y;
        this.width = width;
        this.height = height;
    }
}
