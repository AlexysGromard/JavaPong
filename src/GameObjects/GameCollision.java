package GameObjects;

import windows.Game;

/**
 * This class handles collision detection between game objects in a game.
 * It iterates through all game objects in the game and checks if there are any collisions.
 */
public class GameCollision {

    /**
     * Checks for collisions between all game objects in the specified game.
     * For every pair of game objects, if a collision is detected, triggers the collision handler.
     *
     * @param game the game instance containing the game objects to check for collisions
     */
    public static void checkCollision(Game game){
        for (GameObject go1 : game.gameObjects) {
            for(GameObject go2 : game.gameObjects){
                if(go1 != go2){
                    if(isColliding(go1, go2)){
                        go1.onCollisionEnter(go2);
                    }
                }
            }
        }
    }

    /**
     * Determines whether two game objects are colliding based on their positions and dimensions.
     *
     * @param a the first game object to check for collision
     * @param b the second game object to check for collision
     * @return true if the two game objects are colliding, false otherwise
     */
    private static boolean isColliding(GameObject a, GameObject b) {
        return a.position.x < b.position.x + b.width &&
            a.position.x + a.width > b.position.x &&
            a.position.y < b.position.y + b.height &&
            a.position.y + a.height > b.position.y;
    }
}
