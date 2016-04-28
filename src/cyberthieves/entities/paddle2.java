package cyberthieves.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import cyberthieves.PingPong;

public class paddle2 extends Mob {
	
//	public int count=0;
	public String userName;
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
	public paddle2 (String userName,int x,int y,int type)
	{	
		super(userName,x,y,1);
		this.x = x;
		this.y = y;
		this.type = type;
		this.userName = userName;
		margin=new Rectangle(x,y,width,height);
		margin.setBounds(x,y,width,height);
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
			if(y<game.getHeight()){
				y++;
			}
			else{
				y=game.getHeight()-1;
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
	
	//function to return the user name of the current player
	public String getuserName(){
		return this.userName;
	}
}
