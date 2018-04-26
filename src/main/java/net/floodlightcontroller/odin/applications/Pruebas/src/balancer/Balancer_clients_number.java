package balancer;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import utils.MACAddress;
import utils.Network;
import utils.OdinClient;

public class Balancer_clients_number implements Balancer {

	/*
	 * the table has pairs of MAC - IP Address a STA can be heard by more than
	 * one agent so the MAC address of a STA may appear more than once (one per
	 * each agent who has heard it above the threshold)
	 */

	/*
	 * The table is hearing map MAC of the STA IP of the agent 00:00:00:00:00:01
	 * [ 192.168.0.1, 192.168.0.2 ] 00:00:00:00:00:02 [ 192.168.0.1 ]
	 * 00:00:00:00:00:03 [ 192.168.0.3 ]
	 */

	/*
	 * This table will be used for storing the status of the new balance as you
	 * fill the table, you distribute and balance the clients between agents For
	 * each agent, stores the number of associated clients The table is
	 * newHearingMap IP Number of associated clients (in order to allow the load
	 * balancing between agents) 192.168.0.1 3 192.168.0.2 1 192.168.0.3 2
	 */

	private HashSet<OdinClient> clients;

	private Network network;

	public Balancer_clients_number(Network network) {
		this.network = network;
		this.clients = network.getClients();
	}

	private void balance_1() {

		if (clients.size() == 0)
			return;

		/*
		 * Now that the hearing map is populated, we re-assign clients to each
		 * AP in a round robin fashion, constrained by the hearing map.
		 */

		/* for all the clients associated to Odin */
		for (OdinClient client : clients) {

			InetAddress minNode = null;
			int minVal = 0;

			/* if the client does not have an IP address, do nothing */
			if (client.getIpAddress() == null || client.getIpAddress().getHostAddress().equals("0.0.0.0"))
				continue;

			/*
			 * if the MAC of the client is not in the (just built) hearing map,
			 * do nothing
			 */
			/* El cliente no ha sido escuchado en ningun momento por los AP's */
			if (network.getHearingClient(client.getMacAddress()) == null) {
				System.err.println("Skipping for client: " + client.getMacAddress());
				continue;
			}

			/*
			 * por cada agente (AP) en hearing map table que haya o este
			 * escuchando ese cliente
			 */
			for (InetAddress agentAddr : network.getHearingClient(client.getMacAddress())) {

				/* (?) Esto no "deberia" ocurrir */
				/* if the IP of the agent is not in the table, add it */
				if (network.getNewMapping(agentAddr) == 0) {
					network.setNewMapping(agentAddr, 0);
				}

				/*
				 * get the number of clients currently associated to that agent
				 */
				int val = network.getNewMapping(agentAddr);

				/* assign the most suitable agent */
				if (minNode == null || val < minVal) {
					minVal = val;
					minNode = agentAddr;
				}
			}

			if (minNode == null)
				continue;

			/*
			 * I move the client to another AP see the definition of the
			 * function in OdinMaster.java (~/floodlightcontroller/odin/master)
			 * If the client is already associated with the AP, the function
			 * will ignore the request.
			 */

			// TODO handoffClientToAp(client.getMacAddress(), minNode);

			/*
			 * increase the number of clients associated to that agent (the one
			 * with the fewest number of clients)
			 */
			network.setNewMapping(minNode, network.getNewMapping(minNode) + 1);
			System.out.println("he movido " + client.getIpAddress() + " a - " + minNode.toString());
		}

	}

	public void balance() {

		// Construir matriz de clientes con AP's
		// client1[1] client1[0]
		// client2[0] client2[1]
		// client3[1]

		Map<InetAddress, Integer> newMapping = new HashMap<InetAddress, Integer>();

		// para cliente su agente optimo segun quien le escuche
		Map<MACAddress, InetAddress> optimalAgent = new HashMap<MACAddress, InetAddress>();

		// Para cada cliente, se mira que agentes le escuchan y se mueve al que
		// menos clientes tenga
		for (OdinClient client : clients) {

			// Nos saltamos los clientes que no han sido escuchados
			if (network.getHearingClient(client.getMacAddress()) == null) {
				System.err.println("Skipping for client: " + client.getMacAddress());
				continue;
			}

			int min = 1000;

			// Esto ta mal
			for (InetAddress agentAddr : network.getHearingClient(client.getMacAddress())) {

				if (network.getNewMapping(agentAddr) == 0) {
					network.setNewMapping(agentAddr, 0);
				}

				int val = network.getNewMapping(agentAddr);

				if (val < min) {
					min = val;
					optimalAgent.put(client.getMacAddress(), agentAddr);
				}
			}
			System.out.println("Cliente: " + client.getIpAddress() + " ------- Agente : "
					+ optimalAgent.get(client.getMacAddress()));
		}

		// Aplicar algoritmo de balanceo

		// Hacer realidad la tabla

	}

	@Override
	public Network getNetwork() {
		return network;
	}

}
