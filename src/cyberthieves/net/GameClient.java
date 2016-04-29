package cyberthieves.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import cyberthieves.PingPong;
import cyberthieves.entities.paddleM;
import cyberthieves.net.packets.Packet;
import cyberthieves.net.packets.Packet.PacketTypes;
import cyberthieves.net.packets.Packet00Login;
import cyberthieves.net.packets.Packet01Disconnect;
import cyberthieves.net.packets.Packet02MoveP;
import cyberthieves.net.packets.Packet03MoveB;
import cyberthieves.net.packets.Packet04NumP;

public class GameClient extends Thread {
	public InetAddress ipAddress;
	private DatagramSocket socket;
	private PingPong pingPong;
	public int newType;
	
	public GameClient(PingPong pingPong,InetAddress ipAddress){
		this.pingPong = pingPong;
		try {
			this.socket = new DatagramSocket();		
			this.ipAddress = ipAddress;
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		while(true){
			
			byte[] data = new byte[100];
			DatagramPacket packet = new DatagramPacket(data,data.length);			
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}		
			this.parsePacket(packet.getData(),packet.getAddress(),packet.getPort());
		}
	}
	
	private void parsePacket(byte[] data, InetAddress address, int port) {
		String message = new String(data).trim();
		PacketTypes type = Packet.lookupPacket(message.substring(0,2));
		Packet packet = null;
		switch (type){
			default: 
			case INVALID:
				break;
			case LOGIN:	
				packet = new Packet00Login(data);
				handleLogin((Packet00Login)packet, address,port);			
				break;
			case DISCONNECT:
				packet = new Packet01Disconnect(data);
				System.out.println("You left the game...");
				PingPong.allPaddle.remove(1);
				break;
			case MOVEB:
				packet = new Packet03MoveB(data);
				this.handleBallMove((Packet03MoveB)packet);
				break;
			case MOVEP:
				packet = new Packet02MoveP(data);
				this.handlePlayerMove((Packet02MoveP)packet);
				break;
			case TYPE:
				packet = new Packet04NumP(data);
				newType = Integer.parseInt(new String(packet.getData()).trim());
				break;
		}
	}
	
	private void handleLogin(Packet00Login packet, InetAddress address, int port) {
		
		System.out.println("["+ address.getHostAddress()+ ":"+port+"] "+ ((Packet00Login)packet).getUserName()+" has joined the game...");
		paddleM paddle33= null;
		if(PingPong.socketServer!=null){
			paddle33 = new paddleM(((Packet00Login)packet).getUserName(),pingPong.allPaddle.size(),address,port);
		}
		else{
			paddle33 = new paddleM(((Packet00Login)packet).getUserName(),1-pingPong.allPaddle.size(),address,port);
		}				
		PingPong.allPaddle.add(paddle33);
	}
	

	//basic function to send data
	public void sendData(byte[] data){
		DatagramPacket packet = new DatagramPacket(data,data.length,ipAddress,1729);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//has to update in the client about the move of others
	private void handlePlayerMove(Packet02MoveP packet) {
		this.pingPong.movePaddle(packet.getUserName(),packet.getX(), packet.getY());
	}
	
	//this function is to handle the move of the ball
	private void handleBallMove(Packet03MoveB packet) {		
		this.pingPong.moveBall(packet.getX(),packet.getY());
	}

}
