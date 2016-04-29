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
			if(game.ball.x + game.ball.speed_x <= this.x+this.width){
				if(game.ball.y < (this.y-game.ball.diameter) || (game.ball.y >this.y+this.height)){
					if(game.ball.x < this.x){
						haha = true;
					}
				}
				else {
					game.ball.speed_x = (-1)*game.ball.speed_x;
					if(game.upPressed){
						if(game.ball.speed_y <= -1.20){
						}
						else{
							game.ball.speed_y = game.ball.speed_y - (float)0.1;
						}
					}
					else if(game.downPressed){
						if(game.ball.speed_y >= 1.20){
						}
						else{
							game.ball.speed_y = game.ball.speed_y + (float)0.1;
						}
					}
					else{}
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
						if(game.ball.speed_y <= -1.20){
						}
						else{
							game.ball.speed_y = game.ball.speed_y - (float)0.1;
						}
					}
					else if(game.downPressed){
						if(game.ball.speed_y >= 1.20){
						}
						else{
							game.ball.speed_y = game.ball.speed_y + (float)0.1;
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
	
	//function to return the user name of the current player
	public String getuserName(){
		return this.userName;
	}
}
