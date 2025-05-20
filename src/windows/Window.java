package windows;
import utils.FontManager;

import java.awt.*;

import javax.swing.*;

public class Window extends JFrame {

    public Window() {
        super("Javapong - retro edition");
        JPanel mainPanel = createMainPanel();

        initWindow();
        setContentPane(mainPanel);
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

        final Menu menu = new Menu(panel);
        final Game game = new Game(panel);

        panel.add(menu, "Menu");
        panel.add(game, "Game");

        return panel;
    }
}
