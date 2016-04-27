package cyberthieves.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.net.InetAddress;

import cyberthieves.PingPong;

public class paddleM extends paddle2 {
	
	public InetAddress ipAddress;
	public int port;
	
	
	public int count=0;
	public float x;
	public float y;
	public int width=15;
	public int  height=80;
	public int speed=0;
	public int type = 0;
	
//	InputHandler input;
	
	public double length=height;
	
	boolean goingup=false;
	boolean goingdown=false;
	boolean haha = false;	
	public Rectangle margin;	
	public paddleM(String userName,int type,InetAddress ipAddress, int port)
	{	
		super(userName,10,10,1);
		if(type == 0){
			this.x = 10;
			this.y = 10;
		}
		else if(type == 1){
			this.x = 900-10;
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
		if(game.upPressed){
			if(y>=0){
				y--;
			}
			else{
				y=0;
			}
		}
		if(game.downPressed){
			if(y<332){
				y++;
			}
			else{
				y=331;
			}
			
		}
		
		if(type == 0){
			if(game.Ball.x + game.Ball.speed_x <= this.x+this.width){			
				if(game.Ball.y < (this.y-game.Ball.diameter) || (game.Ball.y >this.y+this.height)){
					if(game.Ball.x < this.x){
						haha = true;
					}
				}
				else {
					game.Ball.speed_x = (-1)*game.Ball.speed_x;
					if(game.upPressed){
						if(game.Ball.speed_y <= -1.20){						
						}
						else{
							game.Ball.speed_y = game.Ball.speed_y - (float)0.1;
						}
					}
					else if(game.downPressed){
						if(game.Ball.speed_y >= 1.20){						
						}
						else{
							game.Ball.speed_y = game.Ball.speed_y + (float)0.1;
						}
					}
					else{}
				}
			}	
		}
		else if(type ==1){
			if(game.Ball.x + game.Ball.speed_x >= this.x-this.width){			
				if(game.Ball.y < (this.y-game.Ball.diameter) || (game.Ball.y >this.y+this.height)){
					if(game.Ball.x > this.x+this.width){
						haha = true;
					}
				}
				else {
					game.Ball.speed_x = (-1)*game.Ball.speed_x;
					if(game.upPressed){
						if(game.Ball.speed_y <= -1.20){						
						}
						else{
							game.Ball.speed_y = game.Ball.speed_y - (float)0.1;
						}
					}
					else if(game.downPressed){
						if(game.Ball.speed_y >= 1.20){						
						}
						else{
							game.Ball.speed_y = game.Ball.speed_y + (float)0.1;
						}
					}
					else{}
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
