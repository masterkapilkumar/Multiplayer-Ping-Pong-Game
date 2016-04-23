package cyberthieves;

import java.math.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Random;
import javax.swing.*;

//this panel creates the background screen of the game and a ball on it
public class PingPong extends JPanel implements ActionListener, KeyListener{

    private float ballX = Main.windowWidth/2;
    private float ballY = Main.windowHeight/2;
    private float diameter = 20;
    private float ballDeltaX = -3;
    private float ballDeltaY = 3;

    private boolean upPressed = false;
    private boolean downPressed = false;

    private float paddleOneX = 20;
    private float paddleOneY = Main.windowHeight/2;
    private float paddleOneWidth = 15;
    private float paddleOneHeight = 80;

    private float paddleSpeed = 5;

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
    	
    	
    	//moving the paddle upward
        if (upPressed) {
            if (paddleOneY-paddleSpeed > 0) {
                paddleOneY -= 1.1*paddleSpeed;
            }
        }
        
        //moving the ball downward
        if (downPressed) {
            if (paddleOneY + paddleSpeed + paddleOneHeight < getHeight()) {
                paddleOneY += 1.1*paddleSpeed;
            }
        }

        float playerOneRight = paddleOneX + paddleOneWidth;
        float playerOneLeft = paddleOneX;
        float playerOneTop = paddleOneY;
        float playerOneBottom = paddleOneY + paddleOneHeight;

        //where will the ball be after it moves
        float nextBallLeft = ballX + ballDeltaX;
        float nextBallRight = ballX + diameter + ballDeltaX;
        float nextBallTop = ballY + ballDeltaY;
        float nextBallBottom = ballY + diameter + ballDeltaY;
        Boolean haha=false;
        
        
        


        //ball bounces off top and bottom of screen
        if (nextBallTop < 0 || nextBallBottom > getHeight()) {
            ballDeltaY *= -1;
        }       
        
        if(nextBallLeft < playerOneLeft+10){
        	System.out.println(ballY);
        	System.out.println(playerOneTop);
        	if(ballY < (playerOneTop - diameter) || (ballY >playerOneBottom)){
        		if( ballX < playerOneRight-27.5){
        			haha=true;        		}
        	}
        	else{
        		ballDeltaX=(-1)*ballDeltaX;
        		if(upPressed){        		
            		if(ballDeltaY <= -6){
            		}
            		else{
            			ballDeltaY = ballDeltaY - paddleSpeed/2;
            		}       		 
            		       		
            	}
            	else if(downPressed){
            		if(ballDeltaY>= 6){
            		}
            		else{
            			ballDeltaY = ballDeltaY + paddleSpeed/2;
            		}
            		
            	} 
            	else{}
        		
        	}
        	
        }
        
        if(haha){
        	haha = false;
        	System.out.println("PLAYER TWO SCORED");
            ballY=250;
            ballX=250;
            ballDeltaX =((float)0.5+(float)Math.random())*(-1)*(ballDeltaX/Math.abs(ballDeltaX))*3;
            ballDeltaY =((float)0.5+(float)Math.random())*(-1)*(ballDeltaY/Math.abs(ballDeltaY))*3;
        }      


        
        
        
      //ball bounces off left of the screen or collides with the paddle
//        if (nextBallLeft < playerOneRight) {
//            //is it going to miss the paddle?
//            if (ballY > playerOneBottom || ballY < playerOneTop+diameter) {
//                ;
//            }
//            else {
//                ballDeltaX *= -1;
//            }
//        }
        
//        if (nextBallLeft < playerOneRight) {
//        	if ((nextBallLeft < playerOneLeft) && (ballY>=playerOneTop+20) &&(nextBallBottom >= playerOneTop-3) || ((nextBallLeft < playerOneLeft)&&(ballY<=playerOneBottom)&& (nextBallTop >= playerOneBottom-3))){
//            	System.out.println("corner Encountered");
//            	System.out.println("initial "+ballDeltaY);
//        		ballDeltaY =(-1)*(ballDeltaY/Math.abs(ballDeltaY))*3;
//            	System.out.println("final "+ballDeltaY);
//
//            }
//        	       	
//        	else if (nextBallTop < playerOneBottom || nextBallBottom > playerOneTop) {
//                ballDeltaX *= -1;
//            }    	
//        	else if(upPressed){        		
//        		if(ballDeltaY <= -6){
//        		}
//        		else{
//        			ballDeltaY = ballDeltaY - paddleSpeed/2;
//        		}       		 
//        		       		
//        	}
//        	else if(downPressed){
//        		if(ballDeltaY>= 6){
//        		}
//        		else{
//        			ballDeltaY = ballDeltaY + paddleSpeed/2;
//        		}
//        		
//        	} 

//        }
        
        

       

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

        g.fillOval((int)ballX, (int)ballY, (int)diameter, (int)diameter);

        g.fillRect((int)paddleOneX, (int)paddleOneY, (int)paddleOneWidth, (int)paddleOneHeight);
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