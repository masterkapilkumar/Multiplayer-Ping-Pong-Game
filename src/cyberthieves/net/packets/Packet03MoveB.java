package cyberthieves.net.packets;

import cyberthieves.net.GameClient;
import cyberthieves.net.GameServer;

public class Packet03MoveB extends Packet{
	String userName;
	private float x,y;
	
	// after the game starts this will help in reterive the data
	public Packet03MoveB(byte[] data) {
		super(03);
		String[] dataArr = readData(data).split(",");
		this.userName = dataArr[0];
		this.x = Float.parseFloat(dataArr[1]);
		this.y = Float.parseFloat(dataArr[2]);		
	}
	
	//when we will be crating the client
	public Packet03MoveB(String userName) {
		super(03);
		this.userName = userName ;
	}
	
	public Packet03MoveB(String username, float x, float y) {
        super(03);
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
		return ("03"+this.userName+","+this.x+","+this.y).getBytes();
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
		return this.y;
	}

}
