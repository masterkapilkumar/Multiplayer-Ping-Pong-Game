package cyberthieves.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import cyberthieves.PingPong;
import cyberthieves.entities.paddleM;
import cyberthieves.net.packets.Packet;
import cyberthieves.net.packets.Packet.PacketTypes;
import cyberthieves.net.packets.Packet00Login;

public class GameServer extends Thread{
	private DatagramSocket socket;
	private PingPong pingPong;
	public List<paddleM> connectedPlayers = new ArrayList<paddleM>();
	
	public GameServer(PingPong pingPong){
		this.pingPong = pingPong;
		try {
			this.socket = new DatagramSocket(1729);		
		} catch (SocketException e) {
			e.printStackTrace();
		} 
	}
	
	public void run(){
		while(true){
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data,data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}			
			this.parsePacket(packet.getData(),packet.getAddress(),packet.getPort());
			
//			String message = new String(packet.getData()).trim();
//			System.out.println("CLIENT >"+ new String(packet.getData()).trim());
//			if(message.equals("harish")){
//				this.sendData("pong".getBytes(),packet.getAddress(),packet.getPort());				
//			}			
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
//				if(address.getHostAddress().equalsIgnoreCase("127.0.0.1))
				paddleM paddle33 = new paddleM(((Packet00Login)packet).getUserName(),connectedPlayers.size(),address,port);
				this.addConnection(paddle33,((Packet00Login)packet));
//				if(paddle33 != null){
//					this.connectedPlayers.add(paddle33);
//					pingPong.allPaddle.add(paddle33);
//					pingPong.player11 = paddle33;
//				}
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
			}		
		}

		if(!alreadyConnected){				
			this.connectedPlayers.add(paddle33);
		}
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
