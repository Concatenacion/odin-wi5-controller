package balancer;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import main.SmartApSelection;
import utils.MACAddress;
import utils.OdinClient;

public class Balancer_clients_number implements Balancer {
	
	
	/* the table has pairs of MAC - IP Address
	* a STA can be heard by more than one agent
	* so the MAC address of a STA may appear more than once (one per each agent who has heard it above the threshold)
	*/
	/* The table is
	* MAC of the STA		IP of the agent
	* 00:00:00:00:00:01		192.168.0.1
	* 00:00:00:00:00:01		192.168.0.2 
	* 00:00:00:00:00:02		192.168.0.1
	* 00:00:00:00:00:03		192.168.0.3 
	*/
	Map<MACAddress, Set<InetAddress>> hearingMap = new HashMap<MACAddress, Set<InetAddress>> ();

	/* This table will be used for storing the status of the new balance 
	* as you fill the table, you distribute and balance the clients between agents
	* For each agent, stores the number of associated clients 
	* The table is
	* IP		Number of associated clients (in order to allow the load balancing between agents)
	* 192.168.0.1		3
	* 192.168.0.2		1
	* 192.168.0.3		2
	*/
	Map<InetAddress, Integer> newMapping = new HashMap<InetAddress, Integer> ();
	
	
	private HashSet<OdinClient>clients;
	
	public Balancer_clients_number(HashSet<OdinClient>clients, Map<MACAddress, Set<InetAddress>> hearingMap,
			Map<InetAddress, Integer> newMapping){
		this.hearingMap = hearingMap;
		this.newMapping = newMapping;
		this.clients=clients;
	}

	@Override
	public void balance(HashSet<OdinClient>clientes) {
		
		System.out.println("ordenando");
		
		if (clients.size() == 0)
			return;
		
		/*
		 *  Now that the hearing map is populated, we re-assign
		 *  clients to each AP in a round robin fashion, constrained
		 *  by the hearing map.
		 */
		 
		/* for all the clients associated to Odin */
		/* TODO ¿Purque? Tecnicamente ya esta dentro, doble comprobación.*/
		for (OdinClient client: clients) {

			InetAddress minNode = null;
			int minVal = 0;
			
			/* if the client does not have an IP address, do nothing */
			if ( client.getIpAddress() == null
					|| client.getIpAddress().getHostAddress().equals("0.0.0.0"))
				continue;

			/* if the MAC of the client is not in the (just built) hearing map, do nothing */	
			/* El cliente no ha sido escuchado en ningun momento por los AP's */
			if(hearingMap.get(client.getMacAddress()) == null) {
				System.err.println("Skipping for client: " + client.getMacAddress());
				continue;
			}
				
			/* por cada agente (AP) en hearing map table que haya o este escuchando ese cliente */	
			for (InetAddress agentAddr: hearingMap.get(client.getMacAddress())) {
									
				/* (?) Esto no "deberia" ocurrir  */
				/* if the IP of the agent is not in the table, add it */	
				if (!newMapping.containsKey(agentAddr)) {
					newMapping.put(agentAddr, 0);
				}
				
				/* get the number of clients currently associated to that agent */
				int val = newMapping.get(agentAddr);
				
				/* assign the most suitable agent */
				if (minNode == null || val < minVal) {
					minVal = val;
					minNode = agentAddr;
				}
			}

			if (minNode == null)
				continue;
			
			/* I move the client to another AP
			* see the definition of the function in OdinMaster.java (~/floodlightcontroller/odin/master)
			* If the client is already associated with the AP, the function will ignore
			* the request. */
			
			
			// TODO handoffClientToAp(client.getMacAddress(), minNode);
			
			/* increase the number of clients associated to that agent (the one with the fewest number of clients) */
			newMapping.put (minNode, newMapping.get(minNode) + 1);
			SmartApSelection.newMapping = this.newMapping;
			System.out.println("he movido "+ client.getIpAddress() + " a - " + minNode.toString() );
		}
		
	}

	@Override
	public HashSet<OdinClient> getNetwork() {
		return clients;
	}

}
