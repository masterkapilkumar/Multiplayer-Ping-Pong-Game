package cyberthieves.net.packets;

import cyberthieves.net.GameClient;
import cyberthieves.net.GameServer;

public class Packet01Disconnect extends Packet {

	String userName;
	private int x,y;
	
	// after the game starts this will help in reterive the data
	public Packet01Disconnect(byte[] data) {
		super(01);
		this.userName = readData(data);
	}
	
	//when we will be crating the client
	public Packet01Disconnect(String userName) {
		super(01);
		this.userName = userName ;
	}
	
	public Packet01Disconnect(String username, int x, int y) {
        super(01);
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
		return ("01"+this.userName).getBytes();
	}

	//return user name on call
	public String getUserName() {
		return this.userName;
	}

}
