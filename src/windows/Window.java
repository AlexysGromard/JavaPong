package windows;
import utils.FontManager;

import java.awt.*;

import javax.swing.*;

public class Window extends JFrame {

    static JPanel mainPanel;

    static public enum viewName{
        GAME, MENU
    }



    public Window() {
        super("Javapong - retro edition");
        Window.mainPanel =  createMainPanel();

        initWindow();
        setContentPane(Window.mainPanel);
        pack();
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    /**
     * Initialize the window with default settings.
     */
    private void initWindow() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setMinimumSize(new Dimension(600, 400));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Create the main panel of the window.
     */
    private JPanel createMainPanel() {
        final JPanel panel = new JPanel(new CardLayout());

        final Menu menu = new Menu();
        final Game game = new Game();

        panel.add(menu, "Menu");
        panel.add(game, "Game");

        return panel;
    }

    static void SwitchToView(viewName view){
        if(view == viewName.GAME){
            CardLayout cl = (CardLayout)Window.mainPanel.getLayout();
            cl.show(mainPanel, "Game");
        }
        else  if(view == viewName.MENU){
            CardLayout cl = (CardLayout)Window.mainPanel.getLayout();
            cl.show(mainPanel, "Menu");
        }

        //Rendre le focus à la fenêtre affichée
        Component[] components = mainPanel.getComponents();
        for (Component comp : components) {
            if (comp.isShowing()){
                comp.setFocusable(true);
                comp.requestFocusInWindow();
            }
        }
    }
}
