package src.windows;
import java.awt.* ;

import javax.swing.JPanel;

public class Game extends JPanel {
    
    @Override
    public void paintComponent (Graphics g){
        //final Rectangle r = p.getRect() ;
        g.fillRect (10, 10, 100, 20);
   }
}
