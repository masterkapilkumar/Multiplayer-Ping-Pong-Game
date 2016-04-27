package cyberthieves;
import java.awt.*;
import java.util.*;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
public class compu {

	int count=0;
	int x;
	int y;
	int width=15;
	int height=80;
	int speed=0;
	public double length=height;

	boolean goingup=false;
	boolean goingdown=false;
	
	Rectangle margin;
	
	public compu(int x,int y)
	{
		this.x=x;
		this.y=y;
		
		margin=new Rectangle(x,y,width,height);
		margin.setBounds(x,y,width,height);
	}
	public void update(PingPong game)
	{
		
		margin.setBounds(x,y,width,height);
		Random random=new Random();
		int ra=random.nextInt(10);
		if (game.Ball.y>y)
		{
			if(ra<5&& y+ra+height<game.getHeight())
			y+=ra;
				
		}
		else if (game.Ball.y<y)
		{
			
			if(ra>5 && y-ra+5<game.getHeight())
			y-=(ra-5);
			
			
		}	
	}
	public void renderPaddle(Graphics g)
	{
		Graphics2D g1=(Graphics2D)g;
		Rectangle2D rectang=new Rectangle2D.Double(x,y,width,height);
		g1.setPaint(Color.BLACK);
		g1.draw(rectang);
		Rectangle2D rectangl=new Rectangle2D.Double(x,y,width,length);
		g1.setPaint(Color.GREEN);
		g1.fill(rectangl);
		//g.setColor(Color.BLUE);
		//g.fillRect(x, y, width, height);
		
	}
	
}
