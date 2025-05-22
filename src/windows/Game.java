package windows;
import java.awt.* ;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import GameObjects.GameCollision;
import GameObjects.GameObject;
import GameObjects.objects.Border;
import GameObjects.objects.Circle;
import GameObjects.objects.Obstacle;
import GameObjects.objects.Paddle;
import GameObjects.objects.Puck;
import GameObjects.objects.Text;
import controllers.KeyBoardController;
import controllers.PaddleController;
import utils.AudioPlayer;
import utils.FontManager;
import utils.Vector2;
import windows.Window.viewName;

public class Game extends View {

    public static List<GameObject> gameObjects;

    public static Integer scoreLeftPlayer = 0;
    public static Integer scoreRightPlayer = 0;

    private static Text textScoreLeft;
    private static Text textScoreRight;


    private static int frameCounter = 0;

    private static boolean restart = false;

    Game(){
        setBackground(new  Color(13, 13, 13));

        Game.gameObjects = new ArrayList<GameObject>();
        this.startGame();
    }

    /**
     * Instantiates the game objects.
     * This method creates the borders, paddles, puck, and texts.
     * It is called in the constructor of the Game class.
     */
    private void startGame(){

        //Clear the list in case of a new game.
        gameObjects.clear();

        scoreLeftPlayer = 0;
        scoreRightPlayer = 0;
        frameCounter = 0;
        
        // Create the borders
        gameObjects.add(new Border("Border_left", new Vector2(0, 0), new Vector2(6, 1024), new Color(0, 255, 247, 50), new Color(0, 255, 247), Border.BorderType.RIGHT));
        gameObjects.add(new Border("Border_right", new Vector2(1434, 0), new Vector2(6, 1024), new Color(255, 0, 224, 50), new Color(255, 0, 224), Border.BorderType.LEFT));
        gameObjects.add(new Border("Border_top", new Vector2(0, 0), new Vector2(1440, 6), new Color(51, 51, 51)));
        gameObjects.add(new Border("Border_bottom", new Vector2(0, 1018), new Vector2(1440, 6), new Color(51, 51, 51)));
        gameObjects.add(new Border("Border_center", new Vector2(716, 0), new Vector2(6, 1024), new Color(51, 51, 51)));
        gameObjects.add(new Circle("Circle_center", 645, 438, 150, 150, 6, new Color(51, 51, 51), new Color(13, 13, 13)));

        // Create the texts
        Game.textScoreLeft = new Text("Score_left", 629, 27, "0", 64, FontManager.OrbitronStyle.MEDIUM, new Color(242, 242, 242));
        gameObjects.add(Game.textScoreLeft);
        Game.textScoreRight = new Text("Score_right", 759, 27, "0", 64, FontManager.OrbitronStyle.MEDIUM, new Color(242, 242, 242));
        gameObjects.add(Game.textScoreRight);

        // Create theKeyboard controllers
        PaddleController controllerLeft= new controllers.KeyBoardController(KeyEvent.VK_Z, KeyEvent.VK_S, 6, 1018, 210);
        PaddleController controllerRight = new controllers.KeyBoardController(KeyEvent.VK_UP, KeyEvent.VK_DOWN, 6, 1018, 210);

        // Create the paddles
        gameObjects.add(new Paddle("Paddle_left", 37, 407, 12, 210, new Color(0, 255, 247), controllerLeft));
        gameObjects.add(new Paddle("Paddle_right", 1391, 407, 12, 210, new Color(255, 0, 224), controllerRight));

        // Cast the controllers to KeyListener and add them to the panel
        addKeyListener((KeyListener) controllerLeft);
        addKeyListener((KeyListener) controllerRight);
        setFocusable(true);
        requestFocusInWindow();

        // Create the puck
        gameObjects.add(new Puck("Puck", 700, 493, 39, 39));
    }

    static void resetGame(){
        scoreLeftPlayer = 0;
        scoreRightPlayer = 0;
        frameCounter = 0;
        restart = true;
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(restart){ 
            //La logique du restart lorsqu'un but est marqué est appliquée afin d'éviter les accès concurents sur gameObjects dans les foreach.

            List<GameObject> toDestroy = new ArrayList<GameObject>();

            //Suppr all balls and add a new one:
            for (GameObject go : gameObjects) {
                if(go instanceof Puck || go instanceof Obstacle){
                    toDestroy.add(go);
                }
            }
            for (GameObject go : toDestroy) {
                
                gameObjects.remove(go);
                
            }

            textScoreLeft.text = scoreLeftPlayer.toString();
            textScoreRight.text = scoreRightPlayer.toString();
           

            // Create the puck
            gameObjects.add(new Puck("Puck", 700, 493, 39, 39));
            restart = false;
        }



        Graphics2D g2 = (Graphics2D) g.create();

        View.RenderContext ctx = getRenderContext();
        g2.translate(ctx.xOffset(), ctx.yOffset());
        g2.scale(ctx.scale(), ctx.scale());

        // Antialiasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Check des collisions
        GameCollision.checkCollision();

        //Check the bonuses:
        bonusManagement();

        // Update and draw each game object
        for (GameObject go : Game.gameObjects) {
            go.update(g2);
        }

        frameCounter++;
        g2.dispose();
    }
    private static void bonusManagement(){
        //In charge of increasing puck's speed or adding obstacles.
        if(frameCounter > 600){
            Random r = new Random();
            if(r.nextInt(2) == 0){
                 gameObjects.add(new Puck("Puck2", 700, 493, 39, 39));
            }
            else{
                gameObjects.add(new Obstacle("obs", new Vector2(r.nextInt(1000) + 200, r.nextInt(200) +400)));
            }
           
            frameCounter = 0;
        } 
    }


    public static void pointMarqued(boolean isLeftPlayer){
        if(isLeftPlayer){
            scoreLeftPlayer++;
            textScoreLeft.text = scoreLeftPlayer.toString();
           
        }
        else{
            scoreRightPlayer++;
            textScoreRight.text = scoreRightPlayer.toString();
        }

        if(scoreLeftPlayer >= 3 || scoreRightPlayer >= 3){

            Window.SwitchToView(viewName.RESULT);
        }

       restart = true;
       frameCounter = 0;
    }
}