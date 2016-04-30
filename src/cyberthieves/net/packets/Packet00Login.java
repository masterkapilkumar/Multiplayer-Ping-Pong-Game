package cyberthieves.net.packets;

import cyberthieves.net.GameClient;
import cyberthieves.net.GameServer;

public class Packet00Login extends Packet {

	String userName;
	private int x,y;
	
	// after the game starts this will help in retrieve the data
	public Packet00Login(byte[] data) {
		super(00);
		String[] dataArr = readData(data).split(",");
		this.userName = dataArr[0];
		this.x = Integer.parseInt(dataArr[1]);
		this.y = Integer.parseInt(dataArr[2]);
	}
	
	public Packet00Login(String userName, int x, int y) {
        super(00);
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

	
	@Override
	public byte[] getData() {
		return ("00"+this.userName+","+this.getX()+","+this.getY()).getBytes();
	}

	//return user name on call
	public String getUserName() {
		return this.userName;
	}
	
	//to get the value of x
	public int getX(){
		return this.x;
	}
	
	//to get the value of y
	public int getY(){
		return this.y;
	}

}
