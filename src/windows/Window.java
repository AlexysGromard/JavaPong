package src.windows;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class Window  extends JFrame{
    
    JPanel panel;

    public Window(){
        //Window constants
        setSize(800,  500);
        setResizable(false);
        setBackground(new  Color(8, 8, 14));
        setMinimumSize(new Dimension(600, 400));
        setName("Javapong - retro edition");

        //Displaying the right panel
        this.panel = new Game();
        setContentPane(this.panel);
    }

}
