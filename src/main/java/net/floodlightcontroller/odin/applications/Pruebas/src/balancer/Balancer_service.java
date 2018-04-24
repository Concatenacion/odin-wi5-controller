package balancer;

import java.util.HashSet;

import utils.Network;
import utils.OdinClient;

public class Balancer_service implements Balancer {
	
	private Network network;
	
	public Balancer_service(Network network){
		this.network=network;
	}

	@Override
	public void balance() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Network getNetwork() {
		return network;
	}

}
