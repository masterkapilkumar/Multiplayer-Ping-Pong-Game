package cyberthieves.net.packets;

import cyberthieves.net.GameClient;
import cyberthieves.net.GameServer;

public class Packet00Login extends Packet {

	String userName;
	private int x,y;
	
	// after the game starts this will help in reterive the data
	public Packet00Login(byte[] data) {
		super(00);
		this.userName = readData(data);
	}
	
	//when we will be crating the client
	public Packet00Login(String userName) {
		super(00);
		this.userName = userName ;
	}
	
	public Packet00Login(String username, int x, int y) {
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
		return ("00"+this.userName).getBytes();
	}

	//return user name on call
	public String getUserName() {
		return this.userName;
	}

}
