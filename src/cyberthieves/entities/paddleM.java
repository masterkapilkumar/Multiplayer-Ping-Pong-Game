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

	public InetAddress ipAddress;
	public int port;


	public int count=0;
	public float x;
	public float y;
	public int width;
	public int  height;
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

		if(type == 0){
			this.x = 10;
			this.y = 10;
			this.width=15;
			this.height=80;
		}
		else if (type==1){
			this.x = Main.windowWidth-10-width;
			this.y = 10;
			this.width=15;
			this.height=80;
		}
		else if (type==2)
		{
			this.x=Main.windowWidth/2;
			this.y=10;
			this.width=80;
			this.height=15;
		}
		else if (type==3)
		{this.width=80;
		this.height=15;
			this.x=Main.windowWidth/2;
			this.y=Main.windowHeight-height-35;
			
		}
		this.type = type;
		this.ipAddress = ipAddress;
		this.port = port;
		this.userName = userName;
		//margin=new Rectangle((int)this.x,(int)this.y,width,height);
		//margin.setBounds((int)this.x,(int)this.y,width,height);
	}

	public void update(PingPong game)
	{
		//margin.setBounds((int)this.x,(int)this.y,width,height);

		boolean moved = false;
		if(type==game.type)
		{
			if(type==0 || type==1){
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
			else {
			if(game.rightPressed)
			{
				if(x+width<game.getWidth() && x+width+speed<game.getWidth())
				{
					x+=speed;
					moved=true;
				}
				else 
				{
					x=game.getWidth()-1-width;
				}
			}
			if(game.leftPressed)
			{
				if(x>0 && x+height+speed>0)
				{
					x-=speed;
					moved=true;
				}
				else
				{
					x=1;
				}
			}
			}
			//System.out.println("bingo");
		}

		if(moved){

			Packet02MoveP packet = new Packet02MoveP(this.userName,this.x,this.y);
			packet.writeData(game.socketClient);
		}



		/*if(type == 0){
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
*/
		
		
		if(type==0)
		{
			if(game.ball.x<this.x)
			{
				float dis1=this.y-game.ball.y-game.ball.diameter/2;
				if(dis1>=-1*this.height && dis1<=0)
				{
					System.out.println("Still !!!");
					
					game.ball.speed_y=-game.ball.speed_y;
					game.ball.speed_x=-game.ball.speed_x;
				}
			}
			else if(game.ball.x-this.x==this.width)
			{
				float dis1=this.y-game.ball.y-game.ball.diameter/2;
				if(dis1>=-1*this.height && dis1<=0){
					System.out.println("Inner");
				game.ball.speed_x=-1*game.ball.speed_x;}
				
			}
			else if(game.ball.x+game.ball.speed_x-this.x<this.width)
			{
				
				float dis1=this.y-game.ball.y-game.ball.diameter/2;
				if(dis1>=-1*this.height && dis1<=0){
					System.out.println("ahf");
				game.ball.x=this.x+this.width;
				game.ball.speed_x=-1*game.ball.speed_x;
				game.ball.speed_y=-1*game.ball.speed_y;}
			}
			
		}
		
		
		else if (type==1)
		{
			if(game.ball.x>this.x)
			{
				float dis1=this.y-game.ball.y-game.ball.diameter/2;
				if(dis1>=-1*this.height && dis1<=0)
				{
					System.out.println("Still !!!");
					
					game.ball.speed_y=-game.ball.speed_y;
					game.ball.speed_x=-game.ball.speed_x;
				}
			}
			else if(this.x-game.ball.x==this.width)
			{
				float dis1=this.y-game.ball.y-game.ball.diameter/2;
				if(dis1>=-1*this.height && dis1<=0){
					System.out.println("Inner");
				game.ball.speed_x=-1*game.ball.speed_x;}
				
			}
			else if(game.ball.x+game.ball.speed_x>this.x)
			{
				
				float dis1=this.y-game.ball.y-game.ball.diameter/2;
				if(dis1>=-1*this.height && dis1<=0){
					System.out.println("ahf");
				game.ball.x=this.x+this.width;
				game.ball.speed_x=-1*game.ball.speed_x;
				game.ball.speed_y=-1*game.ball.speed_y;}
			}
		}
		
		else if(type==2)
		{
			if(game.ball.y<this.y)
			{System.out.println(1);
				float dis1=this.x-game.ball.x-game.ball.diameter/2;
				if(dis1>=-1*this.width && dis1<=0)
				{
					System.out.println(2);
					
					game.ball.speed_y=-game.ball.speed_y;
					game.ball.y=this.x+this.height+1;
					//game.ball.speed_x=-game.ball.speed_x;
				}
			}
			else if(game.ball.y-this.y==this.height)
			{
				System.out.println(3);
				float dis1=this.x-game.ball.x-game.ball.diameter/2;
				if(dis1>=-1*this.width && dis1<=0){
					System.out.println("Inner");
				game.ball.speed_y=-1*game.ball.speed_y;
				game.ball.y=this.y+this.height+1;}
				
			}
			else if(game.ball.y+game.ball.speed_y<this.y)
			{
				System.out.println(4);
				float dis1=this.x-game.ball.x-game.ball.diameter/2;
				if(dis1>=-1*this.width && dis1<=0){
					System.out.println("ahf");
				game.ball.y=this.y+this.height+1;
				game.ball.speed_x=-1*game.ball.speed_x;
				game.ball.speed_y=-1*game.ball.speed_y;}
			}
		}
		else if(type==3)
		{
			if (game.ball.y>Main.windowWidth) game.ball.y=Main.windowWidth/2;
			if(game.ball.y>this.y)
			{System.out.println(1);
				float dis1=this.x-game.ball.x-game.ball.diameter/2;
				if(dis1>=-1*this.width && dis1<=0)
				{
					System.out.println(2);
					game.ball.y=this.y-game.ball.diameter/2-1;
					//game.ball.speed_y=-game.ball.speed_y;
					game.ball.speed_x=-game.ball.speed_x;
				}
			}
			else if(this.y-game.ball.y==this.height)
			{
				System.out.println(3);
				float dis1=this.x-game.ball.x-game.ball.diameter/2;
				if(dis1>=-1*this.width && dis1<=0){
					System.out.println("Inner");
				game.ball.speed_y=-1*game.ball.speed_y;}
				

			}
			else if(game.ball.y+game.ball.speed_y+game.ball.diameter>=this.y)
			{
				System.out.println(4);
				float dis1=this.x-game.ball.x-game.ball.diameter/2;
				if(dis1>=-1*this.width && dis1<=0){
					System.out.println("ahf");
				//game.ball.y=this.y+this.height;
					game.ball.y=this.y-game.ball.diameter/2-1;

					game.ball.speed_y=-1*game.ball.speed_y;
				//game.ball.speed_y=-1*game.ball.speed_y;
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

		Rectangle2D inner=new Rectangle2D.Double(x,y,width,height);
		g1.setPaint(Color.BLUE);
		g1.fill(inner);

	}
	@Override
	public boolean hasCollided(int xa, int ya) {

		return false;
	}
}
