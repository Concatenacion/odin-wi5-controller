package balancer;

import java.util.HashSet;

import utils.OdinClient;

public interface Balancer {	
	
	public void balance(HashSet<OdinClient>clients);	
	
	public HashSet<OdinClient> getNetwork();
}
