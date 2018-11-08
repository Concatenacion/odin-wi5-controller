package net.floodlightcontroller.odin.master.web;

import java.net.InetAddress;
import java.util.Set;

import net.floodlightcontroller.odin.master.*;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class AgentManagerResource extends ServerResource {

	@Get("json")
    public Set<InetAddress> retreive() {
    	OdinMaster oc = (OdinMaster) getContext().getAttributes().
        					get(OdinMaster.class.getCanonicalName());
    	
    	return oc.getAgentAddrs(PoolManager.GLOBAL_POOL);
    }
}