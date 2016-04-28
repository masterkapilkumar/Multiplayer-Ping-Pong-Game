package cyberthieves;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JPanel {

    private JButton new_game;
    private JButton options;
    private JButton exit;
    static final int windowWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    static final int windowHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private int widthScale = 4;
    private int heightScale = 15;
    public int height = 700;
    public int width = 700;
    private JPanel cards;

    public MainMenu()
    {
        // create buttons
        setBackground(Color.black);
        Image background ;
        Image newgame1, newgame2 ;
        Image options1, options2 ;
        Image exit1, exit2 ;
        Box box = Box.createVerticalBox();
        JLabel head = new JLabel();
        setVisible(true);

        JPanel cards;

        try {
            background = ImageIO.read(getClass().getResource("/head.png"));
            background = background.getScaledInstance((int)(width / 3.5), width / 6, Image.SCALE_SMOOTH);
            head = new JLabel(new ImageIcon(background));
            add(head);

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

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        cards = new JPanel(new CardLayout());

        box.add(Box.createVerticalStrut(height/9));
        box.add(head);
        box.add(new_game);
        box.add(Box.createVerticalStrut(10));
        box.add(options);
        box.add(Box.createVerticalStrut(10));
        box.add(exit);

        add(box);

        // create an instance of inner class ButtonHandler
        // to use for button event handling
        ButtonHandler handler = new ButtonHandler();
        new_game.addActionListener(handler);
        options.addActionListener(handler);
        exit.addActionListener(handler);

    }

    // inner class for button event handling
    private class ButtonHandler implements ActionListener {
        public void actionPerformed( ActionEvent e )
        {
            if(e.getSource() == new_game) {
                setVisible(false);
                PingPong pp = new PingPong();
                pp.start();
            }
            else if(e.getSource() == options) {

            }
            else if(e.getSource() == exit) {
                System.exit(0);
            }

        }
    }

}
