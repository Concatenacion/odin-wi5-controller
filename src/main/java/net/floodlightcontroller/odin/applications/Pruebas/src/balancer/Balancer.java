package balancer;

import java.util.HashSet;

import utils.Network;
import net.floodlightcontroller.odin.master.OdinClient;

public interface Balancer {	
	
	public void balance();	
	
	public Network getNetwork();
}
