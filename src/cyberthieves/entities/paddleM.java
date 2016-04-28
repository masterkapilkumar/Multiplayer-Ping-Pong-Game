package cyberthieves.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.net.InetAddress;

import cyberthieves.Main;
import cyberthieves.PingPong;

public class paddleM extends paddle2 {
	
	public InetAddress ipAddress;
	public int port;
	
	
//	public int count=0;
	public float x;
	public float y;
	public int width=15;
	public int  height=80;
//	public int speed=0;
	public int type = 0;
	
//	InputHandler input;
	
	public double length=height;
	
//	boolean goingup=false;
//	boolean goingdown=false;
	boolean haha = false;	
	public Rectangle margin;	
	public paddleM(String userName,int type,InetAddress ipAddress, int port)
	{	
		super(userName,10,10,1);
		
		if(type == 0){
			this.x = 10;
			this.y = 10;
		}
		else{
			this.x = Main.windowWidth-10-width;
			this.y = 10;
		}
		this.type = type;
		this.ipAddress = ipAddress;
		this.port = port;
		margin=new Rectangle((int)this.x,(int)this.y,width,height);
		margin.setBounds((int)this.x,(int)this.y,width,height);
	}
	
	public void update(PingPong game)
	{	
		if(type==game.type){
			if(game.upPressed){
				if(y>=0){
					y--;
				}
				else{
					y=0;
				}
			}
			if(game.downPressed){
				if(y+height<game.getHeight()){
					y++;
				}
				else{
					y=game.getHeight()-1-height;
				}
				
			}
		}
		
		
		if(type == 0){
			if(PingPong.Ball.x + PingPong.Ball.speed_x <= this.x+this.width){
				if(PingPong.Ball.y < (this.y-PingPong.Ball.diameter) || (PingPong.Ball.y >this.y+this.height)){
					if(PingPong.Ball.x < this.x){
						haha = true;
					}
				}
				else {
					PingPong.Ball.speed_x = (-1)*PingPong.Ball.speed_x;
					if(game.upPressed){
						if(PingPong.Ball.speed_y > -1.20){
							PingPong.Ball.speed_y = PingPong.Ball.speed_y - (float)0.1;
						}
					}
					else if(game.downPressed){
						if(PingPong.Ball.speed_y < 1.20){
							PingPong.Ball.speed_y = PingPong.Ball.speed_y + (float)0.1;
						}
					}
				}
			}	
		}
		else if(type ==1){
			if(PingPong.Ball.x + PingPong.Ball.speed_x >= this.x-this.width){
				if(PingPong.Ball.y < (this.y-PingPong.Ball.diameter) || (PingPong.Ball.y >this.y+this.height)){
					if(PingPong.Ball.x > this.x+this.width){
						haha = true;
					}
				}
				else {
					PingPong.Ball.speed_x = (-1)*PingPong.Ball.speed_x;
					if(game.upPressed){
						if(PingPong.Ball.speed_y > -1.20){
							PingPong.Ball.speed_y = PingPong.Ball.speed_y - (float)0.1;
						}
					}
					else if(game.downPressed){
						if(PingPong.Ball.speed_y < 1.20){
							PingPong.Ball.speed_y = PingPong.Ball.speed_y + (float)0.1;
						}
					}
				}
			}
		}
		
				
	}
	public void renderPaddle(Graphics g)
	{		
		Graphics2D g1=(Graphics2D)g;
		Rectangle2D rectang=new Rectangle2D.Double(x,y,width,height);
		g1.setPaint(Color.BLACK);
		g1.draw(rectang);
		
		Rectangle2D inner=new Rectangle2D.Double(x,y,width,length);
		g1.setPaint(Color.BLUE);
		g1.fill(inner);		
		
	}
	@Override
	public boolean hasCollided(int xa, int ya) {
		
		return false;
	}
}
