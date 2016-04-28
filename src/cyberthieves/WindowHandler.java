package cyberthieves;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import cyberthieves.net.packets.Packet01Disconnect;

public class WindowHandler {
	/*
	private final PingPong pingPong;
	
	public WindowHandler(PingPong pingPong){
		this.pingPong = pingPong;
		this.pingPong.game_window.addWindowListener(this);
	}

	@Override
	public void windowActivated(WindowEvent event) {
		
	}

	@Override
	public void windowClosed(WindowEvent event) {
		
	}

	//This function will get executed when user will be closing the window
	@Override
	public void windowClosing(WindowEvent event) {
		Packet01Disconnect packet = new Packet01Disconnect(PingPong.paddle23.getuserName());
		//send the disconnect packet to the actual server 
		packet.writeData(this.pingPong.socketClient);
	}

	@Override
	public void windowDeactivated(WindowEvent event) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent event) {
		
	}

	@Override
	public void windowIconified(WindowEvent event) {
		
	}

	@Override
	public void windowOpened(WindowEvent event) {
		
	}*/

}
