package cyberthieves.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.net.InetAddress;

import cyberthieves.Main;
import cyberthieves.PingPong;
import cyberthieves.net.packets.Packet02MoveP;

public class paddleM extends paddle2 {

	private static final long serialVersionUID = 1L;
	public InetAddress ipAddress;
	public int port;


	public int count=0;
	public float x;
	public float y;
	public int width=15;
	public int  height=80;
	public int hWidth=80;
	public int  hHeight=15;
	public int speed=10;
	public int type = 0;
	public String userName;

//	InputHandler input;

	public double length=height;

	boolean goingup=false;
	boolean goingdown=false;
	boolean haha = false;
	public Rectangle margin;
	public paddleM(String userName,int type,InetAddress ipAddress, int port)
	{
		super(userName,10,10,1);

		switch(type){
		case 0: 
			this.x = 10;
			this.y = 10;
			break;
		case 1: 
			this.x = Main.windowWidth-10-width;
			this.y = 10;
			break;
		case 2: 
			this.x = 100;
			this.y = 10;
			break;
		case 3:
			this.x = 100;
			System.out.println(Main.windowHeight+" : window height");
			System.out.println(Main.windowHeight-50-hHeight+" : paddle position");
			this.y = Main.windowHeight-45-hHeight;
		}
		
		this.type = type;
		this.ipAddress = ipAddress;
		this.port = port;
		this.userName = userName;
		if(type == 0 || type ==1){
			margin=new Rectangle((int)this.x,(int)this.y,width,height);
			margin.setBounds((int)this.x,(int)this.y,width,height);
		}
		else if(type ==2 || type==3){
			margin=new Rectangle((int)this.x,(int)this.y,hWidth,hHeight);
			margin.setBounds((int)this.x,(int)this.y,hWidth,hHeight);
		}
		
	}

	public void update(PingPong game)
	{
		boolean moved = false;
		if(type==game.type){
			if(game.upPressed){
				if(y>=0 && y-speed>=0){
					y-=speed;
					moved = true;
				}
				else{
					y=0;
				}

			}
			if(game.downPressed){
				if(y+height<game.getHeight() && y+height+speed<game.getHeight()){
					y+=speed;
					moved = true;
				}
				else{
					y=game.getHeight()-1-height;
				}
			}
		}

		if(moved){

			Packet02MoveP packet = new Packet02MoveP(this.userName,this.x,this.y);
			packet.writeData(PingPong.socketClient);
		}



		if(type == 0){
			if(game.ball.x + game.ball.speed_x <= this.x+this.width){
				if(game.ball.y < (this.y-game.ball.diameter) || (game.ball.y >this.y+this.height)){
					if(game.ball.x < this.x){
						haha = true;
					}
				}
				else {
					game.ball.speed_x = (-1)*game.ball.speed_x;
					if(game.upPressed){
						if(game.ball.speed_y > -1.20){
							game.ball.speed_y = game.ball.speed_y - (float)0.1;
						}
					}
					else if(game.downPressed){
						if(game.ball.speed_y < 1.20){
							game.ball.speed_y = game.ball.speed_y + (float)0.1;
						}
					}
				}
			}
		}
		else if(type ==1){
			if(game.ball.x + game.ball.speed_x >= this.x-this.width){
				if(game.ball.y < (this.y-game.ball.diameter) || (game.ball.y >this.y+this.height)){
					if(game.ball.x > this.x+this.width){
						haha = true;
					}
				}
				else {
					game.ball.speed_x = (-1)*game.ball.speed_x;
					if(game.upPressed){
						if(game.ball.speed_y > -1.20){
							game.ball.speed_y = game.ball.speed_y - (float)0.1;
						}
					}
					else if(game.downPressed){
						if(game.ball.speed_y < 1.20){
							game.ball.speed_y = game.ball.speed_y + (float)0.1;
						}
					}
				}
			}
		}


	}
	public void renderPaddle(Graphics g)
	{
		Graphics2D g1=(Graphics2D)g;
		if(type ==0 || type==1){
			Rectangle2D rectang=new Rectangle2D.Double(x,y,width,height);
			g1.setPaint(Color.BLACK);
			g1.draw(rectang);

			Rectangle2D inner=new Rectangle2D.Double(x,y,width,height);
			g1.setPaint(Color.BLUE);
			g1.fill(inner);
		}
		else{
			Rectangle2D rectang=new Rectangle2D.Double(x,y,hWidth,hHeight);
			g1.setPaint(Color.BLACK);
			g1.draw(rectang);

			Rectangle2D inner=new Rectangle2D.Double(x,y,hWidth,hHeight);
			g1.setPaint(Color.BLUE);
			g1.fill(inner);
		}
		

	}
	@Override
	public boolean hasCollided(int xa, int ya) {

		return false;
	}
}
