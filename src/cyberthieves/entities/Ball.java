package cyberthieves.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

import cyberthieves.PingPong;
import cyberthieves.net.packets.Packet03MoveB;
public class Ball extends Mob{
    int p=0;
    public String userName="ball";
    public float x;
    public float y;
    public int diameter=20;
    public float speed_x,speed_y;
    public float vel=5;
    public Rectangle ball_shape;
    boolean paddlecollide=false;
    public Ball(int x,int y)
    {
        super("ball",x,y,(int)(1.0));
        this.x=x;
        this.y=y;
        speed_x=vel;
        speed_y=vel;

        ball_shape=new Rectangle(x,y,diameter,diameter);
        ball_shape.setBounds(x,y,diameter,diameter);
    }

    public void update(PingPong game)
    {
        ball_shape.setBounds((int)x,(int)y,diameter,diameter);
        collision_with_paddle(game);
//		if(game.socketClient != null && game.socketServer != null){
//			speed_x = 2;
//			speed_y = 2;
//		}
//		else{
//			speed_x = 1;
//			speed_y = 1;
//		}

        {
            x+=(speed_x);
            y+=(speed_y);
            if(x<=0){
                speed_x=(-1)*speed_x;
//				System.out.println("touchdown");
            }
            else if (x+diameter>=game.getWidth())
            {
                speed_x=-1*speed_x;

            }
            if (y+speed_y<=0 || y+speed_y+diameter >= game.getHeight()){
                if(speed_y !=0){
                    speed_y=(-1)*speed_y;
                    y = this.y+speed_y;
                }
                else
                    speed_y = (float)0.75;
            }

        }
        paddlecollide=false;


        Packet03MoveB packet = new Packet03MoveB(this.userName, this.x, this.y);
        packet.writeData(game.socketClient);

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

    //What this function does is that it draws the ball
    public void renderball(Graphics g)
    {
        Graphics2D g1=(Graphics2D)g;

        g1.setColor(Color.RED);
        Ellipse2D oval=new Ellipse2D.Double(x,y,diameter,diameter);
        g1.setPaint(Color.RED);
        g1.fill(oval);
    }

    @Override
    public boolean hasCollided(int xa, int ya) {
        return false;
    }


}

