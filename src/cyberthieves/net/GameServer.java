package cyberthieves.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import cyberthieves.PingPong;
import cyberthieves.entities.Ball;
import cyberthieves.entities.paddleM;
import cyberthieves.net.packets.Packet;
import cyberthieves.net.packets.Packet.PacketTypes;
import cyberthieves.net.packets.Packet00Login;
import cyberthieves.net.packets.Packet01Disconnect;
import cyberthieves.net.packets.Packet02MoveP;
import cyberthieves.net.packets.Packet03MoveB;

public class GameServer extends Thread{
	private DatagramSocket socket;
	public List<paddleM> connectedPlayers = new ArrayList<paddleM>();
	public Ball ball = new Ball(100,100);
		
	public GameServer(PingPong pingPong){
		try {
			this.socket = new DatagramSocket(1729);		
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
	
	//This function will parse the data sent by the client
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
				System.out.println("["+ address.getHostAddress()+ ":"+port+"] "+ ((Packet00Login)packet).getUserName()+" has connected2...");
				paddleM paddle33 = new paddleM(((Packet00Login)packet).getUserName().trim(),0,address,port);
				this.addConnection(paddle33,((Packet00Login)packet));
				break;
			case MOVEB:
				packet = new Packet03MoveB(data);
//				System.out.println(((Packet03MoveB)packet).getUserName()+"has moved to "
//						+((Packet03MoveB)packet).getX()+","+((Packet03MoveB)packet).getY());
				this.handleBallMove(((Packet03MoveB)packet));
				break;
			case MOVEP:
				packet = new Packet02MoveP(data);
//				System.out.println(((Packet02MoveP)packet).getUserName()+"has moved to "
//						+((Packet02MoveP)packet).getX()+","+((Packet02MoveP)packet).getY());
				this.handlePlayerMove(((Packet02MoveP)packet));				
				break;
			case DISCONNECT:
				packet = new Packet01Disconnect(data);
				System.out.println("["+ address.getHostAddress()+ ":"+port+"] "+ ((Packet01Disconnect)packet).getUserName()+" has left the game...");
				this.removeConnection((Packet01Disconnect)packet);
				break;
		}
	}	

	//this is to build connection among the players
	public void addConnection(paddleM paddle33, Packet00Login packet) {
		boolean alreadyConnected = false;
		//check whether a particular player is already connected or not
		for(paddleM p: this.connectedPlayers){
			if(paddle33.getuserName().equalsIgnoreCase(p.getuserName())){
				if(p.ipAddress == null){
					p.ipAddress = paddle33.ipAddress;
				}
				if(p.port==-1){
					p.port = paddle33.port;
				}
				alreadyConnected = true;
			}
			else{
				sendData(packet.getData(),p.ipAddress,p.port);
				packet = new Packet00Login(p.getuserName(), (int)p.x, (int)p.y);
                sendData(packet.getData(), paddle33.ipAddress, paddle33.port);				
			}		
		}

		if(!alreadyConnected){				
			this.connectedPlayers.add(paddle33);			
		}		
            
		
	}
	
	//this function actually removes the user once disconnected from the game
	public void removeConnection(Packet01Disconnect packet) {
			this.connectedPlayers.remove(this.getPaddleIndex(packet.getUserName()));
			packet.writeData(this);
			
	}
	
	//this function is to handle the movement of the ball
	private void handleBallMove(Packet03MoveB packet) {
		if(packet.getUserName()!=null){
			this.ball.x = packet.getX();
			this.ball.y = packet.getY();
			packet.writeData(this);
		}
	}
	
	//this function handles the movement of the players and helps in setting communication between them
	private void handlePlayerMove(Packet02MoveP packet) {
		
		//check if that player exists
		if(getPaddle(packet.getUserName())!=null){
			int index = getPaddleIndex(packet.getUserName());
			this.connectedPlayers.get(index).x = packet.getX();
			this.connectedPlayers.get(index).y = packet.getY();
			packet.writeData(this);
		}
	}
	
	
	//loop through all the connected players and send the player with the given user name
	public paddleM getPaddle(String userName){
		for(paddleM p: this.connectedPlayers){
			if(p.userName.equals(userName)){
				return p;
			}
		}
		return null;
	}
	
	//loop through all the connected players and return the index of the player with the given user name
	public int getPaddleIndex(String userName){
		int index = 0;
		for(paddleM p: this.connectedPlayers){
			if(p.userName.equals(userName)){
				break;
			}
			index++;
		}
		return index;
	}

	public void sendData(byte[] data , InetAddress ipAddress, int port){
		DatagramPacket packet = new DatagramPacket(data,data.length,ipAddress,port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// this function will loop through all the members currently connected to the server and will send the data to all
	public void sendDatatoAllClients(byte[] data) {
		for(paddleM p: connectedPlayers){
			sendData(data,p.ipAddress,p.port);
		}
	}
}
