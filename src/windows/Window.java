package windows;
import utils.FontManager;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class Window  extends JFrame{
    
    JPanel panel;

    public Window(){
        //Window constants
        setSize(800,  500);
        setResizable(true);
        setMinimumSize(new Dimension(600, 400));
        setTitle("Javapong - retro edition");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Displaying the right panel
        this.panel = new Game();
        setContentPane(this.panel);
        pack(); // Adjuste size of the window to fit the panel
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }
}
