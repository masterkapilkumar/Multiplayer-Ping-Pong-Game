import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ResponseHandle implements KeyListener,ActionListener {

	
	public ResponseHandle(PingPong game)
	{
		game.addKeyListener(this);
		
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int up_or_down=e.getKeyCode();
		
		
		if(up_or_down==KeyEvent.VK_UP)
		{
			PingPong.player1.goingup=true;
			PingPong.player1.goingdown=false;
			//System.out.print("bingjo");
		}
		if (up_or_down==KeyEvent.VK_DOWN)
		{
			PingPong.player1.goingdown=true;
			PingPong.player1.goingup=false;
			
		}
		if(up_or_down==KeyEvent.VK_W)
		{
			PingPong.player2.goingup=true;
			PingPong.player2.goingdown=false;
			//System.out.print("bingjo");
		}
		if (up_or_down==KeyEvent.VK_S)
		{
			PingPong.player2.goingdown=true;
			PingPong.player2.goingup=false;
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.print("bingjo");
		
		/*int bin=e.getKeyCode();
		
		
		if(bin==KeyEvent.VK_UP)
		{
			PingPong.player1.goingup=false;
			PingPong.player1.goingdown=true;
			//System.out.print("bingjo");
		}
		if (bin==KeyEvent.VK_DOWN)
		{
			PingPong.player1.goingdown=false;
			PingPong.player1.goingup=true;
			
		}*/
		PingPong.player1.speed=0;
		PingPong.player2.speed=0;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.print("bingjo");
			int up_or_down=e.getKeyCode();
		
		
		if(up_or_down==KeyEvent.VK_UP)
		{
			PingPong.player1.goingup=true;
			PingPong.player1.goingdown=false;
			//System.out.print("bingjo");
		}
		if (up_or_down==KeyEvent.VK_DOWN)
		{
			PingPong.player1.goingdown=true;
			PingPong.player1.goingup=false;
			
		}
		if(up_or_down==KeyEvent.VK_W)
		{
			PingPong.player2.goingup=true;
			PingPong.player2.goingdown=false;
			//System.out.print("bingjo");
		}
		if (up_or_down==KeyEvent.VK_S)
		{
			PingPong.player2.goingdown=true;
			PingPong.player2.goingup=false;
			
		}
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
