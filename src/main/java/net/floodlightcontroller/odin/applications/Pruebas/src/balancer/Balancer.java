package balancer;

import java.util.HashSet;

import utils.Network;
import utils.OdinClient;

public interface Balancer {	
	
	public void balance();	
	
	public Network getNetwork();
}
