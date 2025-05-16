import javax.swing.*;
import windows.Window;

public class Javapong{
    
    public static void main(String[] args) throws InterruptedException {
        
        System.setProperty("sun.java2d.opengl", "true"); /* pour animation fluide */

        Window win = new Window() ;

        win.setVisible(true);

        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true){
            win.repaint() ; 
            Thread.sleep(50);
        }

    }
        
}
