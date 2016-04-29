package cyberthieves;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import cyberthieves.entities.Ball;
import cyberthieves.entities.paddleM;
import cyberthieves.net.GameClient;
import cyberthieves.net.GameServer;
import cyberthieves.net.packets.Packet00Login;



public class PingPong extends JPanel implements Runnable,ActionListener, KeyListener {
	
	private static final long serialVersionUID=1L;
	public final int window_height=500;
	public final int window_width=500;
	public static paddleM paddle23;
	public static compu complayer;
	public Ball ball;
	public  List<paddleM> allPaddle = new ArrayList<>();
	public GameClient socketClient;
	public  GameServer socketServer;
	public  boolean upPressed=false;
	public boolean downPressed = false;
	private InetAddress ipAddress;
	public int type;
//	public WindowHandler windowHandler;
	private Image backgroundImage;
	private String userName;

	public void run()
	{

	}
	public PingPong()
	{
		setFocusable(true);
		requestFocusInWindow();
        addKeyListener(this);
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/background_img.jpg"));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

		//asking the user whether he wants to play with computer or not
		ball = new Ball(window_width/2,window_height/2);
	}
	
	public void update()
	{		
		for(int i =0;i<allPaddle.size();i++){
			this.allPaddle.get(i).update(this);
		}
		 if(complayer != null){
			 complayer.update(this);
		 }		 
		ball.update(this);
		repaint();
	}
	
	public synchronized void start(){
		if(JOptionPane.showConfirmDialog(this,"Do you want to run server")==0){
			socketServer = new GameServer(this);
			socketServer.start();		
		}
		try {
			ipAddress = InetAddress.getByName("localhost");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		socketClient = new GameClient(this,ipAddress);
		socketClient.start();
//		windowHandler = new WindowHandler(this);
		System.out.println(allPaddle.size() + " this is while creating first the server");
		if(socketServer!=null){
			this.type=0;
			userName = JOptionPane.showInputDialog(this,"enter a user name");
			paddle23 = new paddleM(this.userName,allPaddle.size(),null,-1);
		}
		else{
			this.type=1;
			userName = JOptionPane.showInputDialog(this,"enter a user name");
			paddle23 = new paddleM(this.userName,1-allPaddle.size(),ipAddress,1729);
		}
		
		Packet00Login loginPacket = new Packet00Login(paddle23.getuserName());
		allPaddle.add(paddle23);
	    if(socketServer != null){	    	
	    	socketServer.addConnection(paddle23,loginPacket);
	    }
	    loginPacket.writeData(socketClient);

		new Thread(this).start();
		Timer timer = new Timer(1000/60, this);
		timer.start();

		
	}
	public static synchronized void stop()
	{
		System.exit(0);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

		for(int i =0;i<allPaddle.size();i++){
			this.allPaddle.get(i).renderPaddle(g);
		}		
		if(complayer != null){
			complayer.renderPaddle(g);
		}		
		ball.renderball(g);
		
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

	private int getPaddleIndex(String userName){
		int index = 0;
		for(paddleM p: this.allPaddle){
			if(p.userName.equals(userName)){
				break;
			}
			index++;
		}
		return index;
	}

	public void movePaddle(String userName, float x, float y){
		if(!(userName.equals(this.userName))){
			int index = this.getPaddleIndex(userName);
			this.allPaddle.get(index).x = x;
			this.allPaddle.get(index).y = y;
		}

	}
	
	
	
	public static void main(String[] args)
	{
		PingPong game=new PingPong();
		game.start();
		
	}
	@Override
	public void actionPerformed(ActionEvent e){
        this.update();
    }

	public void moveBall(float x, float y) {
		if(this.socketServer == null ){
			ball.x = x;
			ball.y = y;
		}
	}

}
