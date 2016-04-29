package cyberthieves;

import com.sun.java.swing.plaf.motif.MotifBorders;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class MainMenu extends JPanel {

    private JPanel mainmenu;
    private JButton new_game;
    private JButton options;
    private JButton exit;
    //    static final int windowWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
//    static final int windowHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    private int widthScale = 4;
    private int heightScale = 15;
    public int height = Main.windowHeight;
    public int width = Main.windowWidth;

    private JPanel playWith;
    private JLabel gameMode;
    private JButton computerPlayer;
    private JButton overNetwork;
    private JButton back1;

    private JPanel levels;
    private JLabel gameLevel;
    private JButton easy;
    private JButton medium;
    private JButton difficult;
    private JButton back2;

    private PingPong game;

    private JPanel cards;

    public MainMenu()
    {
//         create buttons

        mainmenu = new JPanel();
        mainmenu.setBackground(Color.black);
        playWith = new JPanel();
        playWith.setBackground(Color.black);
        levels = new JPanel();
        levels.setBackground(Color.black);
        Image background ;

//         options in the main menu
        JLabel head = new JLabel();
        Image newgame1, newgame2 ;
        Image options1, options2 ;
        Image exit1, exit2 ;

//         options in the game mode menu
        Image modetext;
        Image back11, back12;
        Image compPlayer1, compPlayer2;
        Image overNetwork1, overNetwork2;

//         options in the choose level menu
        Image leveltext;
        Image back21, back22;
        Image easy1, easy2;
        Image medium1, medium2;
        Image difficult1, difficult2;

//         containers for storing menu buttons
        Box box = Box.createVerticalBox();
        Box box2 = Box.createVerticalBox();
        Box box3 = Box.createVerticalBox();


        try {
//        set the image for the heading of main menu
            background = ImageIO.read(getClass().getResource("/head.png"));
            background = background.getScaledInstance((int) (width / 2), width / 4, Image.SCALE_SMOOTH);
            head = new JLabel(new ImageIcon(background));

            modetext = ImageIO.read(getClass().getResource("/choosemode.png"));
            modetext = modetext.getScaledInstance((int) (width / 1.25), (int)(width / 6.5), Image.SCALE_SMOOTH);
            gameMode = new JLabel(new ImageIcon(modetext));

            leveltext = ImageIO.read(getClass().getResource("/chooselevel.png"));
            leveltext = leveltext.getScaledInstance((int) (width / 1.25), (int)(width / 6.5), Image.SCALE_SMOOTH);
            gameLevel = new JLabel(new ImageIcon(leveltext));

//        set the buttons to be displayed the menus
            newgame1 = ImageIO.read(getClass().getResource("/newgame.png"));
            newgame2 = ImageIO.read(getClass().getResource("/newgame2.png"));
            newgame1 = newgame1.getScaledInstance(width / widthScale, width / heightScale, Image.SCALE_SMOOTH);
            newgame2 = newgame2.getScaledInstance(width / widthScale, width / heightScale, Image.SCALE_SMOOTH);

            new_game = new JButton(new ImageIcon(newgame1) );

            new_game.setRolloverIcon(new ImageIcon(newgame2));
            new_game.setBorderPainted(false);
            new_game.setContentAreaFilled(false);
            new_game.setFocusPainted(false);
            new_game.setOpaque(false);

            options1 = ImageIO.read(getClass().getResource("/options.png"));
            options2 = ImageIO.read(getClass().getResource("/options2.png"));
            options1 = options1.getScaledInstance(width / widthScale, width / heightScale, Image.SCALE_SMOOTH);
            options2 = options2.getScaledInstance(width / widthScale, width / heightScale, Image.SCALE_SMOOTH);

            options = new JButton(new ImageIcon(options1) );

            options.setRolloverIcon(new ImageIcon(options2));
            options.setBorderPainted(false);
            options.setContentAreaFilled(false);
            options.setFocusPainted(false);
            options.setOpaque(false);

            exit1 = ImageIO.read(getClass().getResource("/exit.png"));
            exit2 = ImageIO.read(getClass().getResource("/exit2.png"));
            exit1 = exit1.getScaledInstance(width / widthScale, width / heightScale, Image.SCALE_SMOOTH);
            exit2 = exit2.getScaledInstance(width / widthScale, width / heightScale, Image.SCALE_SMOOTH);

            exit = new JButton(new ImageIcon(exit1) );

            exit.setRolloverIcon(new ImageIcon(exit2));
            exit.setBorderPainted(false);
            exit.setContentAreaFilled(false);
            exit.setFocusPainted(false);
            exit.setOpaque(false);


            compPlayer1 = ImageIO.read(getClass().getResource("/comp_player.png"));
            compPlayer2 = ImageIO.read(getClass().getResource("/comp_player2.png"));
            compPlayer1 = compPlayer1.getScaledInstance(width / widthScale, width / heightScale, Image.SCALE_SMOOTH);
            compPlayer2 = compPlayer2.getScaledInstance(width / widthScale, width / heightScale, Image.SCALE_SMOOTH);

            computerPlayer = new JButton(new ImageIcon(compPlayer1) );

            computerPlayer.setRolloverIcon(new ImageIcon(compPlayer2));
            computerPlayer.setBorderPainted(false);
            computerPlayer.setContentAreaFilled(false);
            computerPlayer.setFocusPainted(false);
            computerPlayer.setOpaque(false);

            overNetwork1 = ImageIO.read(getClass().getResource("/network.png"));
            overNetwork2 = ImageIO.read(getClass().getResource("/network2.png"));
            overNetwork1 = overNetwork1.getScaledInstance(width / widthScale, width / heightScale, Image.SCALE_SMOOTH);
            overNetwork2 = overNetwork2.getScaledInstance(width / widthScale, width / heightScale, Image.SCALE_SMOOTH);

            overNetwork = new JButton(new ImageIcon(overNetwork1) );

            overNetwork.setRolloverIcon(new ImageIcon(overNetwork2));
            overNetwork.setBorderPainted(false);
            overNetwork.setContentAreaFilled(false);
            overNetwork.setFocusPainted(false);
            overNetwork.setOpaque(false);

            easy1 = ImageIO.read(getClass().getResource("/easy.png"));
            easy2 = ImageIO.read(getClass().getResource("/easy2.png"));
            easy1 = easy1.getScaledInstance(width / widthScale, width / heightScale, Image.SCALE_SMOOTH);
            easy2 = easy2.getScaledInstance(width / widthScale, width / heightScale, Image.SCALE_SMOOTH);

            easy = new JButton(new ImageIcon(easy1) );

            easy.setRolloverIcon(new ImageIcon(easy2));
            easy.setBorderPainted(false);
            easy.setContentAreaFilled(false);
            easy.setFocusPainted(false);
            easy.setOpaque(false);

            medium1 = ImageIO.read(getClass().getResource("/medium.png"));
            medium2 = ImageIO.read(getClass().getResource("/medium2.png"));
            medium1 = medium1.getScaledInstance(width / widthScale, width / heightScale, Image.SCALE_SMOOTH);
            medium2 = medium2.getScaledInstance(width / widthScale, width / heightScale, Image.SCALE_SMOOTH);

            medium = new JButton(new ImageIcon(medium1) );

            medium.setRolloverIcon(new ImageIcon(medium2));
            medium.setBorderPainted(false);
            medium.setContentAreaFilled(false);
            medium.setFocusPainted(false);
            medium.setOpaque(false);

            difficult1 = ImageIO.read(getClass().getResource("/difficult.png"));
            difficult2 = ImageIO.read(getClass().getResource("/difficult2.png"));
            difficult1 = difficult1.getScaledInstance(width / widthScale, width / heightScale, Image.SCALE_SMOOTH);
            difficult2 = difficult2.getScaledInstance(width / widthScale, width / heightScale, Image.SCALE_SMOOTH);

            difficult = new JButton(new ImageIcon(difficult1) );

            difficult.setRolloverIcon(new ImageIcon(difficult2));
            difficult.setBorderPainted(false);
            difficult.setContentAreaFilled(false);
            difficult.setFocusPainted(false);
            difficult.setOpaque(false);

            back11 = ImageIO.read(getClass().getResource("/back.png"));
            back12 = ImageIO.read(getClass().getResource("/back2.png"));
            back11 = back11.getScaledInstance(width / widthScale, width / heightScale, Image.SCALE_SMOOTH);
            back12 = back12.getScaledInstance(width / widthScale, width / heightScale, Image.SCALE_SMOOTH);

            back1 = new JButton(new ImageIcon(back11) );

            back1.setRolloverIcon(new ImageIcon(back12));
            back1.setBorderPainted(false);
            back1.setContentAreaFilled(false);
            back1.setFocusPainted(false);
            back1.setOpaque(false);

            back21 = ImageIO.read(getClass().getResource("/back.png"));
            back22 = ImageIO.read(getClass().getResource("/back2.png"));
            back21 = back21.getScaledInstance(width / widthScale, width / heightScale, Image.SCALE_SMOOTH);
            back22 = back22.getScaledInstance(width / widthScale, width / heightScale, Image.SCALE_SMOOTH);

            back2 = new JButton(new ImageIcon(back21) );

            back2.setRolloverIcon(new ImageIcon(back22));
            back2.setBorderPainted(false);
            back2.setContentAreaFilled(false);
            back2.setFocusPainted(false);
            back2.setOpaque(false);



        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        setLayout(new BorderLayout());
        mainmenu.setLayout(new BoxLayout(mainmenu, BoxLayout.X_AXIS));
        playWith.setLayout(new BoxLayout(playWith, BoxLayout.X_AXIS));
        levels.setLayout(new BoxLayout(levels, BoxLayout.X_AXIS));

        box.add(head);
        head.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(new_game);
        new_game.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(Box.createVerticalStrut(10));
        box.add(options);
        options.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(Box.createVerticalStrut(10));
        box.add(exit);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);

        box2.add(gameMode);
        gameMode.setAlignmentX(Component.CENTER_ALIGNMENT);
        box2.add(Box.createVerticalStrut(10));
        box2.add(computerPlayer);
        computerPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
        box2.add(Box.createVerticalStrut(10));
        box2.add(overNetwork);
        overNetwork.setAlignmentX(Component.CENTER_ALIGNMENT);
        box2.add(Box.createVerticalStrut(10));
        box2.add(back1);
        back1.setAlignmentX(Component.CENTER_ALIGNMENT);

        box3.add(gameLevel);
        gameLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
        box3.add(Box.createVerticalStrut(10));
        box3.add(easy);
        easy.setAlignmentX(Component.CENTER_ALIGNMENT);
        box3.add(Box.createVerticalStrut(10));
        box3.add(medium);
        medium.setAlignmentX(Component.CENTER_ALIGNMENT);
        box3.add(Box.createVerticalStrut(10));
        box3.add(difficult);
        difficult.setAlignmentX(Component.CENTER_ALIGNMENT);
        box3.add(Box.createVerticalStrut(10));
        box3.add(back2);
        back2.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainmenu.add(box, BorderLayout.NORTH);
        playWith.add(box2, BorderLayout.NORTH);
        levels.add(box3, BorderLayout.NORTH);


        game = new PingPong();

        CardLayout cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        cards.add(mainmenu, "Main Menu");
        cards.add(playWith, "Play With");
        cards.add(levels, "Levels");
        cards.add(game, "Game");

        add(cards);

        cardLayout.show(cards,"Main Menu");

        // create an instance of inner class ButtonHandler
        // to use for button event handling
        ButtonHandler handler = new ButtonHandler();
        new_game.addActionListener(handler);
        computerPlayer.addActionListener(handler);
        overNetwork.addActionListener(handler);
        easy.addActionListener(handler);
        medium.addActionListener(handler);
        difficult.addActionListener(handler);
        back1.addActionListener(handler);
        back2.addActionListener(handler);
        options.addActionListener(handler);
        exit.addActionListener(handler);

    }

    // inner class for button event handling
    private class ButtonHandler implements ActionListener {
        public void actionPerformed( ActionEvent e )
        {
            CardLayout cardLayout = (CardLayout) cards.getLayout();
            if(e.getSource() == new_game) {
                cardLayout.show(cards, "Play With");
            }
            else if(e.getSource() == computerPlayer) {
                cardLayout.show(cards, "Levels");
            }
            else if(e.getSource() == easy) {
                cardLayout.show(cards, "Game");
                game.requestFocusInWindow();
                game.start();
            }
            else if(e.getSource() == medium) {
                cardLayout.show(cards, "Game");
                game.requestFocusInWindow();
                game.start();
            }
            else if(e.getSource() == difficult) {
                cardLayout.show(cards, "Game");
                game.requestFocusInWindow();
                game.start();
            }
            else if(e.getSource() == back1) {
                cardLayout.show(cards, "Main Menu");
            }
            else if(e.getSource() == back2) {
                cardLayout.show(cards, "Play With");
            }
            else if(e.getSource() == options) {

            }
            else if(e.getSource() == exit) {
                System.exit(0);
            }

        }
    }

}
