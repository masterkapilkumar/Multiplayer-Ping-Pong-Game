package cyberthieves;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.*;
import java.lang.Math;
import cyberthieves.entities.*;
public class ball {
	int p=0;
	public float x;
	public float y;
	public int diameter=20;
	public float speed_x,speed_y;
	float vel=(float)1.0;
	public Rectangle ball_shape;
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
		ball_shape.setBounds((int)x,(int)y,diameter,diameter);
		collision_with_paddle(game);
		
		{
		if(x<=0){
			speed_x=(-1)*speed_x;
//			game.paddle22.length-=5;
			System.out.println("touchdown");
		}
		else if (x+diameter>=game.getWidth())
		{
//			game.complayer.length-=5;
			speed_x=-1*speed_x;
			
		}
		
		if (y+speed_y<=0 || y+speed_y+diameter >= 411){
			
//			System.out.println("Before "+speed_y);
			if(speed_y !=0){
				speed_y=(-1)*speed_y;
				y = this.y+speed_y;
			}
			else 
				speed_y = (float)0.75;
			
//			System.out.println("after "+speed_y);
		}	
		
		}
		paddlecollide=false;
		x+=(speed_x);
		y+=(speed_y);
			
	}
	private void collision_with_paddle(PingPong game)
	{
		paddlecollide=true;
//		if (ball_shape.intersects(game.paddle22.margin))
//		{
//			
//			
//			speed_x=-1*speed_x;
//			//speed_y= (speed_y+(game.player1.speed));
//			speed_y=(speed_y+(game.paddle22.speed));
//			if(speed_y>2) speed_y=2;
//			if (speed_y<-2) speed_y=-2;
//			//Random bingo=new Random();
//			//int bi=bingo.nextInt(2);
//			//if(speed_y==0) 
//			//{
//				//if (bi==0) speed_y=1;
//				//else speed_y=-1;
//			//}
//			
//			
//			
//			
//		}
		if(game.complayer !=null){
			if (ball_shape.intersects(game.complayer.margin))
			{
				speed_x=-1*speed_x;
				//speed_y= (speed_y+(game.complayer.speed));
				
//				for(int i =0;i<arrPaddle.size();i++){
//					this.arrPaddle.get(i).renderPaddle(g);
//				}
//				
//				speed_y=(speed_y+(game.paddle22.speed));
//				if(speed_y>2) speed_y=2;
//				if (speed_y<-2) speed_y=-2;
			
			}
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

