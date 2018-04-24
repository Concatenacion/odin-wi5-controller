package utils;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** TODO **/
public class Network {

	
	
	
	private Map<MACAddress, Set<InetAddress>> hearingMap = new HashMap<MACAddress, Set<InetAddress>> ();
	
	
	
	private Map<InetAddress, Integer> newMapping = new HashMap<InetAddress, Integer> ();
	
	

	private HashSet<OdinClient> clients = new HashSet<OdinClient>();
	
	
	// Map to store RSSI for each STA in all APs
    private Map<MACAddress, Double[]> rssiData = new HashMap<MACAddress, Double[]> (); 

	
	public Network(HashSet<OdinClient> clients) {
		this.clients=clients;
	}
	
	
	//METODOS DE ACCESO A LOS MAPAS
	
	
	//Clientes
	
	public int numClients(){
		return clients.size();
	}
	
	public boolean addClient(OdinClient client){
		return clients.add(client);		
	}
	
	
	public HashSet<OdinClient> getClients(){
		return clients;
	}
	
	
	
	public Set<InetAddress> getHearingClient (MACAddress address){
		return hearingMap.get(address);
	}
	
	public Set<InetAddress> setHearingClient (Set<InetAddress> ip, MACAddress mac){
		return hearingMap.put(mac, (Set<InetAddress>) ip);
	}
		
	public Integer setNewMapping(InetAddress ip, int i){
		return newMapping.put(ip, i);
	}
	
	public int getNewMapping (InetAddress ip){
		return newMapping.get(ip);				
	}
	
	
}
