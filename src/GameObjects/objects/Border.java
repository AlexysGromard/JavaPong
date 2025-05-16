package src.GameObjects.objects;

import java.awt.Color;

import src.GameObjects.GameObject;
import src.utils.Vector2;

public class Border extends GameObject{
    
    public Color color;

    public Border(Vector2 position, Vector2 size, Color color, String name){
        this.name = name;
        this.position = position;
        this.width = size.x;
        this.height = size.y;
        this.color = color;
    }
}
