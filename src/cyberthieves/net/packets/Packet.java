package cyberthieves.net.packets;

import cyberthieves.net.GameClient;
import cyberthieves.net.GameServer;

public abstract  class Packet {
	public static enum PacketTypes {
		INVALID(-1), LOGIN(00), DISCONNECT(01), MOVEP(02), MOVEB(03), TYPE(04);
		
		private int packetId;
		private PacketTypes(int packetId){
			this.packetId = packetId;
		}
		
		public int getId(){
			return packetId;
		}	
	}
	
	public byte packetId;
	
	public Packet(int packetId){
		this.packetId = (byte)packetId;
	}
	
	//it will send the data from packet to the server
	public abstract void writeData(GameClient client);	
	
	// it will send the data to every client attached to the particular server
	public abstract void writeData(GameServer server);
	
	// this converts byte to string and takes of the first two letter and then sends back the actual data
	public String readData(byte[] data){
		String message = new String(data).trim();
		return message.substring(2);
	}
	
	//this will be the actual byte array that we will be sending back and forth from the client
	public abstract byte[] getData();
	
	//this function accepts string and then check whether it is a valid or not
	public static PacketTypes lookupPacket(String packetId){
		try{
			return lookupPacket(Integer.parseInt(packetId));
		}
		catch(NumberFormatException e)
		{
			return PacketTypes.INVALID;
		}
	}
	
	//this will take the integer id and then check the type of the packet
	public static PacketTypes lookupPacket(int id){
		for(PacketTypes p : PacketTypes.values()){
			if(p.getId()==id){
				return p;
			}
		}
		return PacketTypes.INVALID;
	}
}
