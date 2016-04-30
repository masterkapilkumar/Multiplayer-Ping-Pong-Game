package cyberthieves.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import cyberthieves.PingPong;

public class paddle2 extends Mob {
	
	public String userName;
	public float x;
	public float y;
	public int type = 0;
	
	
	boolean haha = false;
	
	public paddle2 (String userName,int x,int y,int type)
	{	
		super(userName,x,y,1);
		this.x = x;
		this.y = y;
		this.type = type;
		this.userName = userName;
	}

	public void update(PingPong game)
	{
	}
	public void renderPaddle(Graphics g)
	{		
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
