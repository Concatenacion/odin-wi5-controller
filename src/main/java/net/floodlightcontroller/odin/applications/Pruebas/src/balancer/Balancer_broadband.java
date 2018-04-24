package balancer;


import utils.Network;

public class Balancer_broadband implements Balancer {

	private Network network;
	
	public Balancer_broadband(Network network){
		this.network=network;
	}
	@Override
	public void balance() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Network getNetwork() {
		// TODO Auto-generated method stub
		return network;
	}

}
