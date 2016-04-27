package cyberthieves.entities;

import javax.swing.JFrame;

import cyberthieves.PingPong;

// This is a abstract class to take control of all the entities present in the game
public abstract class Entity extends JFrame {
	public int x,y;
	public Entity(){		
	}
	
	public abstract void update(PingPong game);
	
}
