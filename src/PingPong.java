import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;



public class PingPong extends Canvas implements Runnable {
	
	public boolean isCompu=false;
	ResponseHandle res;
	private Image back;
	private static final long serialVersionUID=1L;
	static boolean gameisrunning=false;
	JFrame game_window;
	public final int window_height=400;
	public final int window_width=900;
	public final Dimension gameSize=new Dimension(window_width,window_height);
	private final String title="Ping Pong Game";
	public static paddle player1;
	public static paddle player2;
	//public static compu complayer;
	public static ball Ball;
	
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
		res=new ResponseHandle(this);
		
		player1=new paddle(10,60,false);
		//player2=new paddle(window_width-10,60);
		
		player2=new paddle(window_width-10,70,false);
		
		Ball=new ball(window_width/2,window_height/2);
		
		   
        
		    
		
		
	}
	
	public void update()
	{
		player1.update(this);
		player2.update(this);
		Ball.update(this);
		//System.out.print(player1.y);
		
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
		g.setColor(Color.WHITE);
		
		g.drawImage(background,0,0,getWidth(),getHeight(),null);
		
		g.fillRect(0, 0, getWidth(),getHeight());
		
		this.player1.renderPaddle(g);
		
		//this.complayer.renderPaddle(g);
		this.player2.renderPaddle(g);
		this.Ball.renderball(g);
		g.dispose();
		game_st.show();
		
		
		
	}
	public synchronized void start(){
		gameisrunning=true;
		new Thread(this).start();
	}
	public static synchronized void stop()
	{
		gameisrunning=false;
		System.exit(0);
	}
	

}
