package com.cyberthieves;

import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {

    static final int windowWidth = 500;
    static final int windowHeight = 500;

    public static void main(String[] args){
        Main game = new Main();
        game.initialize();
    }

    /**
     * This method starts the game and runs it in a loop
     */
    public void run()
    {
        /*
        initialize();

        while(isRunning)
        {
            long time = System.currentTimeMillis();

            update();
            draw();

            //  delay for each frame  -   time it took for one frame
            time = (1000 / 100) - (System.currentTimeMillis() - time);

            if (time > 0)
            {
                try
                {
                    Thread.sleep(time);
                }
                catch(Exception e){}
            }
        }

        setVisible(false);
        */
    }

    /**
     * This method will set up everything need for the game to run
     */
    void initialize()
    {
        setTitle("Ping Pong");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        PingPong panel = new PingPong();
        add(panel, BorderLayout.CENTER);

        setSize(windowWidth, windowHeight);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);               //full screen
        //setUndecorated(true);                        //make the title bar invisible
        setVisible(true);

        /*
        input = new InputHandler(this);
        setTitle("Ping Pong");
        //setExtendedState(JFrame.MAXIMIZED_BOTH);               //full screen
        //setUndecorated(true);                        //make the title bar invisible
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        backBuffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
        setVisible(true);
        isRunning = true;

        insets = getInsets();
        setSize(insets.left + windowWidth + insets.right,
                insets.top + windowHeight + insets.bottom);
         */
    }

    void draw() {
        /*
        Graphics g = getGraphics();

        Graphics bbg = backBuffer.getGraphics();

        bbg.setColor(Color.WHITE);
        bbg.fillRect(0, 0, 500, 500);

        bbg.setColor(Color.BLACK);
        bbg.drawOval(x, y, 20, 20);

        g.drawImage(backBuffer, insets.left, insets.top, this);
        */
    }

    void update() {
        /*
        if (input.isKeyDown(KeyEvent.VK_RIGHT))
        {
            x += 5;
        }
        if (input.isKeyDown(KeyEvent.VK_LEFT))
        {
            x -= 5;
        }
        if (input.isKeyDown(KeyEvent.VK_DOWN))
        {
            y += 5;
        }
        if (input.isKeyDown(KeyEvent.VK_UP))
        {
            y -= 5;
        }
        */
    }
}