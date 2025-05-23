package GameObjects;

import windows.Game;

public class GameCollision {
    
    /**
     * Check all the gameObjects in Game.gameObjects for collision
      */
    public static void checkCollision(){
        for (GameObject go1 : Game.gameObjects) {
            for(GameObject go2 : Game.gameObjects){
                if(go1 != go2){
                    if(isColliding(go1, go2)){
                        go1.onCollisionEnter(go2);
                    }
                }
            }
        }
    }

    /**
     * Check if the 2 gameObject are colliding
     * @param a 1st gameObject
     * @param b 2nd gameObject
     * @return bool: true if the 2 gameObject are in collision
      */
    private static boolean isColliding(GameObject a, GameObject b) {
        return a.position.x < b.position.x + b.width &&
            a.position.x + a.width > b.position.x &&
            a.position.y < b.position.y + b.height &&
            a.position.y + a.height > b.position.y;
    }
}
