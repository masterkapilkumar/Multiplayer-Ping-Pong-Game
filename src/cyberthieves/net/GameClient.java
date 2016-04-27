package cyberthieves.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import cyberthieves.PingPong;
import cyberthieves.entities.paddleM;
import cyberthieves.net.packets.Packet;
import cyberthieves.net.packets.Packet00Login;
import cyberthieves.net.packets.Packet.PacketTypes;

public class GameClient extends Thread {
	private InetAddress ipAddress;
	private DatagramSocket socket;
	private PingPong pingPong;
	
	public GameClient(PingPong pingPong,String ipAddress){
		this.pingPong = pingPong;
		try {
			this.socket = new DatagramSocket();		
			this.ipAddress = InetAddress.getByName(ipAddress);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		while(true){
			
			byte[] data = new byte[10];
			DatagramPacket packet = new DatagramPacket(data,data.length);
			
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}	
			
			this.parsePacket(packet.getData(),packet.getAddress(),packet.getPort());
//			System.out.println("SERVER >"+ new String(packet.getData()).trim());
		}
	}
	
	private void parsePacket(byte[] data, InetAddress address, int port) {
		String message = new String(data).trim();
//		System.out.println(message+" this is the message");
		PacketTypes type = Packet.lookupPacket(message.substring(0,2));
		Packet packet = null;
		switch (type){
			default: 
			case INVALID:
				break;
			case LOGIN:	
				packet = new Packet00Login(data);
				System.out.println("["+ address.getHostAddress()+ ":"+port+"] "+ ((Packet00Login)packet).getUserName()+" has joined the game...");				
//				if(address.getHostAddress().equalsIgnoreCase("127.0.0.1))
				paddleM paddle33 = new paddleM(((Packet00Login)packet).getUserName(),pingPong.socketServer.connectedPlayers.size(),address,port);
				pingPong.allPaddle.add(paddle33);
//				if(paddle33 != null){
//					this.connectedPlayers.add(paddle33);
//					pingPong.allPaddle.add(paddle33);
//					pingPong.player11 = paddle33;
//				}
				break;
			case DISCONNECT:
				break;
		}
	}
	
	public void sendData(byte[] data){
		DatagramPacket packet = new DatagramPacket(data,data.length,ipAddress,1729);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
