package cyberthieves;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cyberthieves.entities.Ball;
import cyberthieves.entities.paddleM;
import cyberthieves.net.GameClient;
import cyberthieves.net.GameServer;
import cyberthieves.net.packets.Packet00Login;



public class PingPong extends Canvas implements Runnable,ActionListener, KeyListener {
	
	private Image back;
	private static final long serialVersionUID=1L;
	static boolean gameisrunning=false;
	public JFrame game_window;
	public final int window_height=400;
	public final int window_width=900;
	public final Dimension gameSize=new Dimension(window_width,window_height);
	private final String title="Ping Pong Game";
	public static paddleM paddle23;
	public static compu complayer;
	public static Ball ball;
	public  List<paddleM> allPaddle = new ArrayList<paddleM>();
	public GameClient socketClient;
	public  GameServer socketServer;
	public  boolean upPressed=false;
	public boolean downPressed = false;
	private InetAddress ipAddress;
	public int type;
	public WindowHandler windowHandler;
	private String userName;
	
	
	
	BufferedImage background=new BufferedImage(window_width,window_height,BufferedImage.TYPE_INT_RGB);
	public void run()
	{		
		while(gameisrunning==true)
		{
			update();
			renderGame();			
		}
		
		
	}
	public PingPong()
	{
		
		game_window=new JFrame();
		setMinimumSize(gameSize);
		setPreferredSize(gameSize);
		setMaximumSize(gameSize);
		game_window.add(this,BorderLayout.CENTER);
		//JLabel background=new JLabel(new ImageIcon(
			//	"C:\\Users\\abhijeet anand\\Desktop\\coursera-android-master\\Examples\\PingPong"
				//+ "\\res\\a.jpg"));
	    //game_window.add(background);
		game_window.pack();
		game_window.setTitle(title);
		game_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game_window.setVisible(true);
		game_window.setResizable(false);
		game_window.setLocationRelativeTo(null);
//		res=new ResponseHandle(this);
		
//		player1=new paddle(10,60);
		
		setFocusable(true);
		requestFocusInWindow();
        addKeyListener(this);
        
        //asking the user whether he wants to play with computer or not
//        if(JOptionPane.showConfirmDialog(this, "Do You want to play with Computer?")==0){
//        	complayer=new compu(window_width-10,62);
//        }     		
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
	}
	
	public synchronized void start(){
		if(JOptionPane.showConfirmDialog(this,"Do you want to run server")==0){
			socketServer = new GameServer(this);
			socketServer.start();		
		}	
		try {
			ipAddress = InetAddress.getByName("10.192.57.50");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		socketClient = new GameClient(this,ipAddress);
		socketClient.start();
		windowHandler = new WindowHandler(this);
//		System.out.println(allPaddle.size()+" this is while creating first the server");
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
		
		
		System.out.println(paddle23.getuserName()+"this is the user name while creation");
		Packet00Login loginPacket = new Packet00Login(paddle23.getuserName());
		allPaddle.add((paddleM)paddle23);
		
	    if(socketServer != null){	    	
	    	socketServer.addConnection((paddleM)paddle23,loginPacket);
	    }	    
	    loginPacket.writeData(socketClient);
	       
		
		gameisrunning=true;
		new Thread(this).start();
	}
	public static synchronized void stop()
	{
		gameisrunning=false;
		System.exit(0);
	}
	
	public void renderGame()
	{
		BufferStrategy game_st=getBufferStrategy();
		if(game_st==null) 
		{
			createBufferStrategy(4);
			return ;
		}
		
		Graphics g=game_st.getDrawGraphics();
		g.drawImage(back, 0, 0, getWidth(), getHeight(), this);
		//g.setColor(Color.);
		
		g.drawImage(background,0,0,getWidth(),getHeight(),null);
		
		g.fillRect(0, 0, getWidth(),getHeight());
		
		for(int i =0;i<allPaddle.size();i++){
			this.allPaddle.get(i).renderPaddle(g);
		}		
		if(complayer != null){
			this.complayer.renderPaddle(g);
		}		
		this.ball.renderball(g);
		g.dispose();
		game_st.show();
		
		
		
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
			this.ball.x = x;
			this.ball.y = y;
		}		
	}

}
