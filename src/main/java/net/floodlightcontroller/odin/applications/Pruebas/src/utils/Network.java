package utils;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Network {

	private Map<MACAddress, Set<InetAddress>> hearingMap = new HashMap<MACAddress, Set<InetAddress>> ();
	
	private Map<InetAddress, Integer> newMapping = new HashMap<InetAddress, Integer> ();

	private HashSet<OdinClient> clients = new HashSet<OdinClient>();

	
	public Network(HashSet<OdinClient> clients ) {
		this.clients=clients;
	}

}
