import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.*;
import java.lang.Math;
public class ball {
	int p=0;
	int x;
	int y;
	int diameter=20;
	int speed_x,speed_y;
	int vel=1;
	Rectangle ball_shape;
	boolean paddlecollide=false;
	public ball(int x,int y)
	{
		this.x=x;
		this.y=y;
		speed_x=vel;
		speed_y=vel;
		
		ball_shape=new Rectangle(x,y,diameter,diameter);
		ball_shape.setBounds(x,y,diameter,diameter);
	}
	
	public void update(PingPong game)
	{
		//System.out.println(speed_y);
		ball_shape.setBounds(x,y,diameter,diameter);
		int distance=(game.player1.y-y);
		
		collision_with_paddle(game);
		
		/*if((x-game.player1.x)<(game.player1.width+diameter/2) || (x-diameter)>game.getWidth())
		{
			
			
		if(distance>-diameter && distance<game.player1.height)
		{
			System.out.println(x);
			speed_x=-1*speed_x;
			speed_y= (speed_y+(game.player1.speed));
		}
		}*/
		
		
		
		
		//if(paddlecollide==false)
		{
		if(x<=0){speed_x=-speed_x; game.player1.length-=5; System.out.println("touchdown");}
		else if (x+diameter>=game.getWidth())
		{
			
			game.player2.length-=5;
			speed_x=-1*speed_x;
			
		}
		
		if (y<=0) speed_y=-speed_y;
		else if (y+diameter>=game.getHeight())
		{
			speed_y=-speed_y;
		} 
		
		
		}
		paddlecollide=false;
		x+=(speed_x);
		y+=(speed_y);
		
		
		
		
		
		
	}
	private void collision_with_paddle(PingPong game)
	{
		paddlecollide=true;
		if (ball_shape.intersects(game.player1.margin))
		{
			
			
			speed_x=-1*speed_x;
			//speed_y= (speed_y+(game.player1.speed));
			speed_y=(speed_y+(game.player1.speed));
			if(speed_y>2) speed_y=2;
			if (speed_y<-2) speed_y=-2;
			//Random bingo=new Random();
			//int bi=bingo.nextInt(2);
			//if(speed_y==0) 
			//{
				//if (bi==0) speed_y=1;
				//else speed_y=-1;
			//}
			
			
			
			
		}
		if (ball_shape.intersects(game.player2.margin))
		{
			speed_x=-1*speed_x;
			//speed_y= (speed_y+(game.complayer.speed));
		
			speed_y=(speed_y+(game.player1.speed));
			if(speed_y>2) speed_y=2;
			if (speed_y<-2) speed_y=-2;
		
		}
		
	}
	public void renderball(Graphics g)
	{
		Graphics2D g1=(Graphics2D)g;
		
		g1.setColor(Color.RED);
		Ellipse2D oval=new Ellipse2D.Double(x,y,diameter,diameter);
		g1.setPaint(Color.RED);
		
		g1.fill(oval);
		
	}
	
}
