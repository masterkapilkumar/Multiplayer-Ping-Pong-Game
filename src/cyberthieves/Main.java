package cyberthieves;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Main extends JFrame {

    public static int windowWidth = 500;
    public static int windowHeight = 500;
    private JPanel cards;
    MainMenu menu;
    SplashScreen splash;
    static WindowHandler windowHandler;

    public static void main(String[] args){
        Main game = new Main();
        windowHandler = new WindowHandler(game);
        game.initialize();
    }


    /**
     * This method will set up everything need for the game to run
     */
    void initialize()
    {
        setTitle("Ping Pong");
//        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        setLayout(new BorderLayout());

        setSize(windowWidth, windowHeight);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);               //full screen
//        setUndecorated(true);                        //make the title bar invisible
//        windowWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
//        windowHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        menu = new MainMenu();
        splash = new SplashScreen();

        splash.setFocusable(true);
        cards = new JPanel(new CardLayout());

        cards.add(splash, "Splash Screen");
        cards.add(menu, "Menu");

        getContentPane().add(cards);
        splash.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                CardLayout cardLayout = (CardLayout) cards.getLayout();
                cardLayout.next(cards);
                menu.requestFocusInWindow();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        splash.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CardLayout cardLayout = (CardLayout) cards.getLayout();
                cardLayout.next(cards);
                menu.requestFocusInWindow();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });



        setVisible(true);

    }

}