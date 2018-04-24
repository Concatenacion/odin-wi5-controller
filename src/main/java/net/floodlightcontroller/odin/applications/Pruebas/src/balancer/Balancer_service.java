package balancer;

import java.util.HashSet;

import utils.OdinClient;

public class Balancer_service implements Balancer {
	
	private HashSet<OdinClient>clients;
	
	public Balancer_service(HashSet<OdinClient>clients){
		this.clients=clients;
	}

	@Override
	public void balance(HashSet<OdinClient>clients) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HashSet<OdinClient> getNetwork() {
		return clients;
	}

}
