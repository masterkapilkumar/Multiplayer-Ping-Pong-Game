package cyberthieves;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

//this panel creates the background screen of the game and a ball on it
public class PingPong extends JPanel implements ActionListener, KeyListener{

    private int ballX = Main.windowWidth/2;
    private int ballY = Main.windowHeight/2;
    private int diameter = 20;
    private int ballDeltaX = -3;
    private int ballDeltaY = 3;

    private boolean upPressed = false;
    private boolean downPressed = false;

    private int paddleOneX = 20;
    private int paddleOneY = Main.windowHeight/2;
    private int paddleOneWidth = 10;
    private int paddleOneHeight = 50;

    private int paddleSpeed = 5;

    private Image backgroundImage;

    //construct a PongPanel
    public PingPong(){

        try {
//        	System.out.println(getClass().getResource("/"));
            backgroundImage = ImageIO.read(getClass().getResource("/background_img.jpg"));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        //listen to key presses
        setFocusable(true);
        addKeyListener(this);

        //call step() 60 fps
        Timer timer = new Timer(1000/60, this);
        timer.start();
    }


    public void actionPerformed(ActionEvent e){
        update();
    }

    public void update(){

        if (upPressed) {
            if (paddleOneY-paddleSpeed > 0) {
                paddleOneY -= paddleSpeed;
            }
        }
        if (downPressed) {
            if (paddleOneY + paddleSpeed + paddleOneHeight < getHeight()) {
                paddleOneY += paddleSpeed;
            }
        }

        int playerOneRight = paddleOneX + paddleOneWidth;
        int playerOneLeft = paddleOneX;
        int playerOneTop = paddleOneY;
        int playerOneBottom = paddleOneY + paddleOneHeight;

        //where will the ball be after it moves
        int nextBallLeft = ballX + ballDeltaX;
        int nextBallRight = ballX + diameter + ballDeltaX;
        int nextBallTop = ballY + ballDeltaY;
        int nextBallBottom = ballY + diameter + ballDeltaY;


        //ball bounces off top and bottom of screen
        if (nextBallTop < 0 || nextBallBottom > getHeight()) {
            ballDeltaY *= -1;
        }

        //ball bounces off left of the screen or collides with the paddle
        if (nextBallLeft < playerOneLeft) {
            //is it going to miss the paddle?
            if (nextBallTop > playerOneBottom || nextBallBottom < playerOneTop) {

                System.out.println("PLAYER TWO SCORED");
                ballY=250;
                ballX=250;
            }
            else {
                ballDeltaX *= -1;
            }
        }

        if (nextBallLeft < playerOneRight) {
            if ((nextBallTop < playerOneBottom) && (ballY > playerOneBottom) || (nextBallBottom > playerOneTop) && (ballY < playerOneTop)) {
                ballDeltaY *= -1;
            }
            else if (nextBallTop < playerOneBottom || nextBallBottom > playerOneTop) {
                ballDeltaX *= -1;
            }

        }

        //ball bounces off right of the screen
        if (nextBallRight > getWidth()) {
            ballDeltaX *= -1;
        }

        //move the ball
        ballX += ballDeltaX;
        ballY += ballDeltaY;

        //stuff has moved, tell this JPanel to repaint itself
        repaint();
    }

    //paint the ball and the paddle
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        g.setColor(Color.WHITE);

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        g.fillOval(ballX, ballY, diameter, diameter);

        g.fillRect(paddleOneX, paddleOneY, paddleOneWidth, paddleOneHeight);
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
    }


    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
    }


}