import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class paddle {

	int count=0;
	int x;
	int y;
	int width=15;
	int height=80;
	int speed=0;
	boolean com;
	
	public double length=height;
	
	boolean goingup=false;
	boolean goingdown=false;
	
	Rectangle margin;
	
	public paddle(int x,int y,boolean isCom)
	{
		this.x=x;
		this.y=y;
		this.com=isCom;
		
		margin=new Rectangle(x,y,width,height);
		margin.setBounds(x,y,width,height);
	}
	public void update(PingPong game)
	{
		
		margin.setBounds(x,y,width,height);

		
		if(this.com==false){
		
		//System.out.println(speed);
		if(goingup==true )
		{
			
			this.speed=-1;
			//if (this.y<0) this.y+=400;
 		}
		if(goingdown==true  )
		{
			
			this.speed=2;
			
		}
		
		this.y+=this.speed;
		
		
		if(y<=0)
		{
			y=0;
			speed=0;
		}
		else if (y+height>game.getHeight())
		{
			y=game.getHeight()-height;
			speed=0;
		}
		
		goingup=false;
		goingdown=false;
		}
		else 
		{
			
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
		
	}
	public void renderPaddle(Graphics g)
	{
		if(this.com==false){
		Graphics2D g1=(Graphics2D)g;
		Rectangle2D rectang=new Rectangle2D.Double(x,y,width,height);
		g1.setPaint(Color.BLACK);
		g1.draw(rectang);
		
		Rectangle2D inner=new Rectangle2D.Double(x,y,width,length);
		g1.setPaint(Color.BLUE);
		g1.fill(inner);
		}
		else
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
		//Rectangle2D inner1=new Rectangle2D.Double(x,y+length,width,height-length);
		//g1.setPaint(Color.RED);
		//g1.fill(inner1);
		//g.setColor(Color.BLUE);
		//g.fillRect(x, y, width, height);
		
	}
	
}
