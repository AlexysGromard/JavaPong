package src.windows;
import javax.swing.*;

public class Window  extends JFrame{
    
    JPanel panel;

    public Window(){
        setSize(600,  400);
        this.panel = new Game();
        setContentPane(this.panel);
    }

}
