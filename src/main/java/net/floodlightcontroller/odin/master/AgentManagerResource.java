package net.floodlightcontroller.odin.master;
import java.util.Map;

import net.floodlightcontroller.storage.IResultSet;
import net.floodlightcontroller.storage.IStorageSourceService;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class AgentManagerResource extends ServerResource {

	@Get("json")
  public Object retreive() { 	
    	
        IStorageSourceService storageSource = 
                (IStorageSourceService)getContext().getAttributes().
                    get(IStorageSourceService.class.getCanonicalName());    	
           	
    	IResultSet res = storageSource.executeQuery("PARAMETERS", null, null, null);
    	if(res.next()){
        Map<String,Object> map = res.getRow();
        return map.get("SMARTAP_PARAMS");
    	}
    	else{
    		return null;
    	}
    }
}