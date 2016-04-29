package cyberthieves.net.packets;

import cyberthieves.net.GameClient;
import cyberthieves.net.GameServer;

public class Packet02MoveP extends Packet {

	String userName;
	private float x,y;
	
	// after the game starts this will help in retrieve the data
	public Packet02MoveP(byte[] data) {
		super(02);
		String[] dataArr = readData(data).trim().split(",");
//		System.out.println(dataArr.length);
		this.userName = dataArr[0];
		this.x = Float.parseFloat(dataArr[1]);
		this.y = Float.parseFloat(dataArr[2]);		
	}
	
	//when we will be crating the client
	public Packet02MoveP(String userName) {
		super(02);
		this.userName = userName ;
	}
	
	public Packet02MoveP(String userName, float x, float y) {
        super(02);
        this.userName = userName;
        this.x = x;
        this.y = y;
    }
	
	@Override
	public void writeData(GameClient client) {
		client.sendData(getData());
	}
	@Override
	public void writeData(GameServer server) {
		server.sendDatatoAllClients(getData());
	}

	//this function will add the data with comma as delimiter and then send it 
	@Override
	public byte[] getData() {
		return ("02"+this.userName+","+this.x+","+this.y).getBytes();
	}

	//return user name on call
	public String getUserName() {
		return this.userName;
	}
	
	//this is to return the y value
	public float getX(){
		return this.x;
	}
	
	//this is to return the y value
	public float getY(){
//		System.out.println("getY is callec");
		return this.y;
	}

}
