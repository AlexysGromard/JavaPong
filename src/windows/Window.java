package windows;
import java.awt.*;
import javax.swing.*;

/**
 * The Window class extends JFrame and serves as the main application window
 * for the JavaPong game. It manages different views, including the game,
 * menu, and result screens, and provides functionality to switch between them.
 */
public class Window extends JFrame {

    static JPanel mainPanel;

    /**
     * An enumeration representing the different views available in the application.
     * This includes:
     * - GAME: The main gameplay view.
     * - MENU: The menu screen view.
     * - RESULT: The results or end-game screen view.
     *
     * These views are used to manage the application's state and switch between
     * different user interfaces, using a card layout in the application's main panel.
     */
    static public enum viewName{
        GAME, MENU, RESULT, PAUSE, NEWGAME;
    }

    /**
     * Constructs a new instance of the main application window for the JavaPong game.
     * This constructor initializes the window with a title, sets up its layout
     * and content panel, and makes the window visible to the user. The main panel
     * is created, configured, and added to the window, while the window itself is centered
     * on the screen.
     *
     * Once all components are configured, the window is made visible to the user.
     */
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
     * Initializes the main application window with specific settings.
     *
     * This method sets the window to a maximized state, allows resizing, and
     * defines a minimum size for the window. It also configures the default close
     * operation to exit the application when the window is closed.
     *
     * The settings ensure the window adapts to the user's screen while maintaining
     * a consistent layout and functionality.
     */
    private void initWindow() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setMinimumSize(new Dimension(600, 400));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Creates and initializes the main panel of the application window.
     *
     * The main panel serves as the container for different views in the application,
     * which are managed using a CardLayout. This method adds the "Menu", "Game",
     * and "Result" views to the panel and assigns appropriate names for switching
     * between these views.
     *
     * @return A JPanel configured with CardLayout and containing the "Menu", "Game",
     * and "Result" views.
     */
    private JPanel createMainPanel() {
        final JPanel panel = new JPanel(new CardLayout());

        final Menu menu = new Menu();
        final Game game = new Game();
        final Result result = new Result();
        final PauseMenu pauseMenu = new PauseMenu();

        panel.add(menu, "Menu");
        panel.add(game, "Game");
        panel.add(result, "Result");
        panel.add(pauseMenu, "Pause");

        return panel;
    }

    /**
     * Switches the application's main panel to the specified view.
     *
     * This method uses a CardLayout to transition between different views
     * (GAME, MENU, RESULT) within the application's main panel. If the GAME view
     * is selected, it also resets the game state. Additionally, it ensures the focus
     * is set to the currently visible panel.
     *
     * @param view The target view to which the application should switch.
     *             This must be one of the options defined in the {@link viewName}
     */
    static void SwitchToView(viewName view){
        if(view == viewName.GAME){
            CardLayout cl = (CardLayout)Window.mainPanel.getLayout();
            cl.show(mainPanel, "Game");
        }
        else if(view == viewName.NEWGAME){
            CardLayout cl = (CardLayout)Window.mainPanel.getLayout();
            cl.show(mainPanel, "Game");
            Game.resetGame();
        }
        else if(view == viewName.MENU){
            CardLayout cl = (CardLayout)Window.mainPanel.getLayout();
            cl.show(mainPanel, "Menu");
        } else if (view == viewName.RESULT) {
            CardLayout cl = (CardLayout)Window.mainPanel.getLayout();
            cl.show(mainPanel, "Result");
        } else if (view == viewName.PAUSE) {
            CardLayout cl = (CardLayout)Window.mainPanel.getLayout();
            cl.show(mainPanel, "Pause");
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
