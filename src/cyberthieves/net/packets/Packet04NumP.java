package cyberthieves.net.packets;
import cyberthieves.net.GameClient;
import cyberthieves.net.GameServer;

public class Packet04NumP extends Packet {	

	String userName;
	int numPlayers;
	// after the game starts this will help in retrieve the data
	public Packet04NumP(byte[] data) {
		super(04);
		String[] dataArr = this.readData(data).split(",");
		this.userName = dataArr[0];
		this.numPlayers = Integer.parseInt(dataArr[1]);
	}

	//when we will be crating the client
	public Packet04NumP(String userName) {
		super(00);
		this.userName = userName ;
	}

	public Packet04NumP(String userName, int numPlayers) {
		super(00);
		this.userName = userName;
		this.numPlayers = numPlayers;		
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
		return ("04"+this.userName+","+this.numPlayers).getBytes();
	}

	//return user name on call
	public String getUserName() {
		return this.userName;
	}
	
	//return the number of players already connected to the server
	public int getNumPlayers(){
		return this.numPlayers;
	}

}
