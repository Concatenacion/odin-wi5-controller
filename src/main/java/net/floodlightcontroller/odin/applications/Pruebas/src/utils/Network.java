package utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** TODO **/
public class Network {

	private static Map<MACAddress, Set<InetAddress>> hearingMap = new HashMap<MACAddress, Set<InetAddress>>();

	private static Map<InetAddress, Integer> newMapping = new HashMap<InetAddress, Integer>();

	private static HashSet<OdinClient> clients = new HashSet<OdinClient>();

	private static int numberAgents;

	// Map to store RSSI for each STA in all APs
	private Map<MACAddress, Double[]> rssiData = new HashMap<MACAddress, Double[]>();

	public Network(HashSet<OdinClient> clients) {
		this.clients = clients;
		inicializacionVariables();
	}

	// METODOS DE ACCESO A LOS MAPAS

	// Clientes

	public int numClients() {
		return clients.size();
	}

	public int numAgents() {
		return numberAgents;
	}

	public boolean addClient(OdinClient client) {
		return clients.add(client);
	}

	public HashSet<OdinClient> getClients() {
		return clients;
	}

	public Set<InetAddress> getHearingClient(MACAddress address) {
		return hearingMap.get(address);
	}

	public Set<InetAddress> setHearingClient(Set<InetAddress> ip, MACAddress mac) {
		return hearingMap.put(mac, (Set<InetAddress>) ip);
	}

	public Integer setNewMapping(InetAddress ip, int i) {
		return newMapping.put(ip, i);
	}

	public int getNewMapping(InetAddress ip) {

		if (!newMapping.containsKey(ip)) {
			return -1;
		}
		return newMapping.get(ip);
	}

	// TODO: para pruebas y tal
	// 4 clientes en la red 192.168.1.1 --> 192.168.1.9
	// 3 ap's, en la red 192.168.0.1
	private static void inicializacionVariables() {

		try {
			// Agentes - en la red 0
			OdinClient agent1 = new OdinClient(new MACAddress(new String("00:00:00:00:00:01").getBytes()),
					InetAddress.getByName("192.168.0.1"), "Cosa");
			OdinClient agent2 = new OdinClient(new MACAddress(new String("00:00:00:00:00:02").getBytes()),
					InetAddress.getByName("192.168.0.2"), "Cosa");
			OdinClient agent3 = new OdinClient(new MACAddress(new String("00:00:00:00:00:03").getBytes()),
					InetAddress.getByName("192.168.0.3"), "Cosa");
			
			numberAgents=3;
			
			
			newMapping.put(agent1.getIpAddress(), 4);
			newMapping.put(agent2.getIpAddress(), 1);
			newMapping.put(agent3.getIpAddress(), 1);

			
			
			// Clientes
			OdinClient cliente1 = new OdinClient(new MACAddress(new String("9D-6F-2C-CB-C1-6A").getBytes()),
					InetAddress.getByName("192.168.1.1"), "Cosa");
			clients.add(cliente1);
			OdinClient cliente2 = new OdinClient(new MACAddress(new String("2C-11-4F-1B-4C-3A").getBytes()),
					InetAddress.getByName("192.168.1.2"), "Cosa");
			clients.add(cliente2);
			OdinClient cliente3 = new OdinClient(new MACAddress(new String("8F-61-8C-87-D6-04").getBytes()),
					InetAddress.getByName("192.168.1.3"), "Cosa");
			clients.add(cliente3);
			OdinClient cliente4 = new OdinClient(new MACAddress(new String("A9-CE-ED-DF-B4-02").getBytes()),
					InetAddress.getByName("192.168.1.4"), "Cosa");
			clients.add(cliente4);
			
			
			//CLIENTE 1
			Set<InetAddress> cliente1set = new HashSet<InetAddress>();
			cliente1set.add(agent1.getIpAddress());
			cliente1set.add(agent3.getIpAddress());	

			hearingMap.put(cliente1.getMacAddress(), cliente1set);

			
			//CLIENTE 2
			Set<InetAddress> cliente2set = new HashSet<InetAddress>();
			cliente2set.add(agent1.getIpAddress());

			hearingMap.put(cliente2.getMacAddress(), cliente2set);

			//CLIENTE 3
			
			Set<InetAddress> cliente3set = new HashSet<InetAddress>();
			cliente3set.add(agent1.getIpAddress());

			hearingMap.put(cliente3.getMacAddress(), cliente3set);
			
			//CLIENTE 4
			
			Set<InetAddress> cliente4set = new HashSet<InetAddress>();
			cliente4set.add(agent1.getIpAddress());
			cliente4set.add(agent2.getIpAddress());
			
			hearingMap.put(cliente4.getMacAddress(), cliente4set);
			

//			System.out.println("Cliente " + cliente1.getIpAddress() + " esta en: "
//					+ hearingMap.get(cliente1.getMacAddress()).toString());
//			System.out.println("Cliente " + cliente2.getIpAddress() + " esta en: "
//					+ hearingMap.get(cliente2.getMacAddress()).toString());
//			System.out.println("Cliente " + cliente3.getIpAddress() + " esta en: "
//					+ hearingMap.get(cliente3.getMacAddress()).toString());
//			System.out.println("Cliente " + cliente4.getIpAddress() + " esta en: "
//					+ hearingMap.get(cliente4.getMacAddress()).toString());
//
//			System.out.println("creados");

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

}
