package cyberthieves;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
public class SplashScreen extends JPanel {

    private Image backgroundImage;
//    static final int windowWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
//    static final int windowHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    static final int windowWidth = 500;
    static final int windowHeight = 500;

    public SplashScreen() {
        try {
//        	System.out.println(getClass().getResource("/"));
            BufferedImage img = ImageIO.read(getClass().getResource("/pingpong.png"));
            backgroundImage = img.getScaledInstance(windowWidth, windowHeight, Image.SCALE_SMOOTH);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

    }

}